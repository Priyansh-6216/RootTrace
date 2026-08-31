# API Documentation

All API routes are prefixed with `/api/v1/`.

## Incidents
- `GET /api/v1/incidents` - List incidents.
- `GET /api/v1/incidents/{id}` - Get incident details.
- `POST /api/v1/incidents` - Create an incident manually.
- `GET /api/v1/incidents/{id}/timeline` - Get incident timeline events.
- `GET /api/v1/incidents/{id}/evidence` - Get evidence graph for the incident.
- `GET /api/v1/incidents/{id}/rca` - Get the AI-generated RCA.

## Services
- `GET /api/v1/services` - List services in the service catalog.
- `GET /api/v1/services/{id}/dependencies` - Get service dependencies.

## Telemetry
- `POST /api/v1/telemetry/ingest` - Ingest raw telemetry data (fallback for OTLP).
