from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List, Optional
import uvicorn

app = FastAPI(title="RootTrace AI Worker")

class IncidentData(BaseModel):
    incidentId: str
    logs: List[str]
    traceGraph: Optional[dict] = None
    metrics: Optional[List[dict]] = None

@app.get("/")
async def root():
    return {"message": "RootTrace AI Worker is running"}

@app.post("/analyze")
async def analyze_incident(data: IncidentData):
    # This will be implemented in Day 5
    return {
        "incidentId": data.incidentId,
        "rootCause": "Analysis pending implementation (Day 5)",
        "confidence": 0.0,
        "evidence": [],
        "recommendedActions": []
    }

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
