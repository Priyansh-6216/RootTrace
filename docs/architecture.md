# RootTrace V2 Architecture

## Overview
RootTrace is an AI-powered production incident investigation platform. It ingests logs, metrics, traces, deployments, and code changes to build a service dependency graph, detect incidents, and perform evidence-backed root-cause analysis (RCA).

## Core Pipeline
1. **Telemetry**: OpenTelemetry gateway ingesting Logs, Metrics, Traces into Kafka.
2. **Normalization**: Transform raw telemetry into a canonical internal format.
3. **Correlation**: Connect related events using correlation IDs.
4. **Dependency Graph**: Maintain a real-time service map in Neo4j.
5. **Incident Detection**: Anomaly detection identifies SEV-level incidents.
6. **RCA Candidates**: Determine potential root causes using deterministic correlation.
7. **Evidence Scoring**: Rank candidates based on temporal, structural, and historical evidence.
8. **AI Reasoning**: Provide top hypotheses to an LLM for summarization and explanation.
9. **RCA Report**: Present findings to engineers.
10. **Human Approval & Remediation**: Allow engineers to review code patches and approve fixes.

## System Components
- **Backend (Spring Boot/Java 17)**: Core incident logic, RCA scoring, and APIs.
- **Frontend (React/TypeScript)**: Incident Command Center.
- **AI Worker (Python/FastAPI/LangChain)**: LLM orchestration and RAG over runbooks/historical incidents.
- **Infrastructure**: Kafka, PostgreSQL, Neo4j, Redis, OpenTelemetry.
