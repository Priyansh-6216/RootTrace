# RootTrace 🚀

AI-powered production debugging platform for microservices, logs, traces, metrics, and incident root-cause analysis.

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
- **Day 4: Distributed Trace Graph with Neo4j** (Next)
- **Day 5: AI Root Cause Analysis Engine**
- **Day 6: Incident Report + GitHub Fix Suggestion**
- **Day 7: Polish, Demo, Deployment**

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
