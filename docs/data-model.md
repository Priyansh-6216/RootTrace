# Data Model

## Relational (PostgreSQL)
- **Incident**: Core incident metadata, status, severity, timestamps.
- **RootCauseCandidate**: Hypotheses for an incident and their scores.
- **Evidence**: Links telemetry events to an incident.
- **Service**: Service catalog metadata (owner, repository, version).

## Graph (Neo4j)
- **Nodes**: Service, Database, KafkaTopic, Redis.
- **Edges**: `CALLS`, `DEPENDS_ON`, `USES`, `PRODUCES`.

## Telemetry (Kafka / Logs)
- **TelemetryEvent**: Canonical schema for all telemetry (Logs, Metrics, Traces, Deployments). Contains:
  - `eventId`
  - `tenantId`
  - `service`
  - `environment`
  - `timestamp`
  - `traceId`, `spanId`
  - `eventType`
  - `severity`
  - `message`
  - `attributes` (JSON)
