# Security

## Authentication & Authorization
- RBAC (Role-Based Access Control) with roles: OWNER, ADMIN, ENGINEER, VIEWER.
- OAuth2 and JWT with refresh tokens.

## Tenant Isolation
- Every entity must include `tenantId` and `environmentId`.
- No cross-tenant data access is allowed.

## Secrets & Data
- Never commit secrets (API keys, passwords, GitHub tokens).
- Use environment variables for sensitive configurations.
- PII/secret redaction is enforced on all incoming logs.

## Audit & Rate Limiting
- All significant actions (e.g., viewing an incident, generating RCA, approving a PR) are audit-logged.
- Rate limits on authentication, telemetry ingestion, and AI endpoints.
