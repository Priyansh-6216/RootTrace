# Production Readiness

## Configuration
- No hardcoded configuration or secrets.
- Use `application-{env}.yml` and environment variables.

## Health and Observability
- Spring Boot Actuator exposes `/actuator/health/readiness` and `/actuator/health/liveness`.
- Centralized logging with correlation IDs.

## Error Handling
- Consistent JSON error formats via `@ControllerAdvice`.
- `ApiError` class with standard `timestamp`, `status`, `code`, `message`, and `traceId`.

## Scale and Reliability
- Kafka topics for telemetry with DLQ (Dead Letter Queue) and backpressure.
- Idempotent processing of messages.
- Retries and fallbacks for AI/LLM calls.
