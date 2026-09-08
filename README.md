# RootTrace V2 (Production Sprint)

RootTrace is an AI-powered production incident investigation platform that ingests telemetry (logs, metrics, traces), builds a dependency graph, detects incidents, performs evidence-backed root-cause analysis, and automatically generates code fixes with human-in-the-loop approval.

## 🚀 7-Day Production Sprint Complete

We have successfully transformed the MVP into a production-hardened platform:

- **Day 1**: Architecture & Refactoring (Bounded Contexts, SOLID, Profiles).
- **Day 2**: Telemetry Ingestion Pipeline (Kafka, Idempotency, Secret Redaction).
- **Day 3**: Incident Detection (Z-score Anomaly Detection) & Service Catalog (Neo4j Graph).
- **Day 4**: Evidence-Based RCA (Deterministic Scoring Engine).
- **Day 5**: AI Agent (LangChain, GPT-4, Cost Tracking).
- **Day 6**: Remediation & GitHub Integration (Approval Webhooks).
- **Day 7**: UI Polish (Glassmorphism, Real-time WebSockets, React Flow Dependency Map).

## Stack
* **Backend**: Java 17, Spring Boot, Kafka, PostgreSQL, Neo4j, Testcontainers
* **AI Worker**: Python, FastAPI, LangChain, OpenAI
* **Frontend**: React 19, TypeScript, TailwindCSS, React Flow, Framer Motion

## Getting Started
Ensure Docker is running, then use `docker-compose up` (if configured) or run the individual services from their respective directories.-cause analysis.

## 🏗️ Architecture

RootTrace is built with a distributed architecture to handle large-scale observability data and provide AI-driven insights.

- **Backend:** Java 17, Spring Boot, Spring Security, Spring Data JPA, Kafka, Redis, Neo4j, OpenTelemetry.
- **AI Worker:** Python, FastAPI, LangChain, FAISS/ChromaDB.
- **Frontend:** React, TypeScript, TailwindCSS, React Flow, Recharts.
- **Infrastructure:** Docker Compose, PostgreSQL, Kafka, Redis, Neo4j.

## 📅 7-Day Build Roadmap

- **Day 1: Project Setup + Microservices Foundation** (Done)
- **Day 2: Log Ingestion + Search System** (Done)
- **Day 3: Kafka Event Replay + Streaming Pipeline** (Done)
- **Day 4: Distributed Trace Graph with Neo4j** (Done)
- **Day 5: AI Root Cause Analysis Engine** (Done)
- **Day 6: Incident Report + GitHub Fix Suggestion** (Done)
- **Day 7: Polish, Demo, Deployment** (Done)

## 🚀 Getting Started

### Prerequisites

- Docker & Docker Compose
- Java 17+
- Node.js & npm
- Python 3.9+

### Running the Infrastructure

```bash
cd infra
docker-compose up -d
```

### Running the Backend

```bash
cd backend
./mvnw spring-boot:run
```

### Running the Frontend

```bash
cd frontend
npm install
npm run dev
```

### Running the AI Worker

```bash
cd ai-worker
pip install -r requirements.txt
python main.py
```

## 🛠️ Features

- **Log Analysis:** Upload and parse complex microservice logs.
- **Distributed Tracing:** Visualize request flows across services using Neo4j.
- **Real-time Streaming:** Kafka-powered event pipeline for live incident detection.
- **AI RCA:** Automatically identify root causes and suggest fixes.
- **GitHub Integration:** Generate PRs for suggested fixes.
