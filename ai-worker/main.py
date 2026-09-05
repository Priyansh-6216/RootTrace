import os
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List, Optional
import uvicorn
from langchain_openai import ChatOpenAI
from langchain_core.prompts import ChatPromptTemplate
from langchain_community.callbacks import get_openai_callback
import json

app = FastAPI(title="RootTrace AI Worker - V2")

class EvidenceModel(BaseModel):
    id: str
    type: str
    description: str
    sourceId: str
    strength: float

class RootCauseCandidateModel(BaseModel):
    id: str
    hypothesis: str
    confidenceLevel: str

class IncidentData(BaseModel):
    incidentId: str
    top_candidates: List[RootCauseCandidateModel]
    evidence_graph: List[EvidenceModel]

class AiAnalysisResult(BaseModel):
    incidentId: str
    human_narrative: str
    remediation_steps: List[str]
    estimated_cost_usd: float
    human_approval_required: bool

@app.get("/")
async def root():
    return {"message": "RootTrace AI Worker is running"}

@app.post("/analyze", response_model=AiAnalysisResult)
async def analyze_incident(data: IncidentData):
    try:
        estimated_cost = 0.0
        human_narrative = ""
        remediation_steps = []

        if os.getenv("OPENAI_API_KEY"):
            llm = ChatOpenAI(model="gpt-4-turbo", temperature=0)
            prompt = ChatPromptTemplate.from_messages([
                ("system", "You are an expert Site Reliability Engineer. Explain the root cause of this incident to a human operator. You will be provided with the top deterministic candidates and the evidence graph. Provide a clear narrative explaining WHY the top candidate is the most likely root cause based ONLY on the evidence. Then, provide 2-3 bullet points for remediation. Format the output as JSON with keys: 'narrative' and 'remediation'."),
                ("user", "Top Candidates: {candidates}\n\nEvidence Graph: {evidence}")
            ])
            chain = prompt | llm

            candidates_json = json.dumps([c.model_dump() for c in data.top_candidates])
            evidence_json = json.dumps([e.model_dump() for e in data.evidence_graph])

            with get_openai_callback() as cb:
                response = chain.invoke({"candidates": candidates_json, "evidence": evidence_json})
                estimated_cost = cb.total_cost
                
                try:
                    # Clean up JSON formatting if LLM added markdown blocks
                    content = response.content.replace("```json", "").replace("```", "").strip()
                    parsed = json.loads(content)
                    human_narrative = parsed.get("narrative", "Failed to parse narrative.")
                    remediation_steps = parsed.get("remediation", ["Manual investigation required."])
                except Exception as parse_ex:
                    print(f"JSON Parsing failed: {parse_ex}")
                    human_narrative = response.content
                    remediation_steps = ["Review narrative for remediation steps."]
        else:
            print("OPENAI_API_KEY not found. Using fallback mock.")
            # Fallback if no API key is provided
            top_hypothesis = data.top_candidates[0].hypothesis if data.top_candidates else "Unknown Issue"
            human_narrative = f"[MOCK] The root cause appears to be: {top_hypothesis}. Evidence indicates a strong correlation."
            remediation_steps = ["Investigate recent deployments.", "Check database connection pool limits."]

        return AiAnalysisResult(
            incidentId=data.incidentId,
            human_narrative=human_narrative,
            remediation_steps=remediation_steps,
            estimated_cost_usd=estimated_cost,
            human_approval_required=True
        )
            
    except Exception as e:
        print(f"LLM analysis failed: {e}")
        raise HTTPException(status_code=500, detail=str(e))

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
