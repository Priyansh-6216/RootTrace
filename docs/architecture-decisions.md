# Architecture Decisions

## ADR 1: AI Reasoner, Not Root Cause Detector
**Date:** Day 1
**Status:** Accepted
**Context:** We need a way to determine the root cause of an incident.
**Decision:** We will use deterministic correlation (logs, metrics, traces) and graph traversal (Neo4j) to generate candidate root causes and score them. The LLM will only be used to reason over this evidence, not to invent it.
**Consequences:** Prevents hallucinations and ensures every RCA claim is backed by telemetry evidence.

## ADR 2: Monolithic Backend First
**Date:** Day 1
**Status:** Accepted
**Context:** The system has multiple distinct bounded contexts (Identity, Telemetry, RCA, etc.).
**Decision:** We will organize the Spring Boot backend into modular bounded contexts within a single deployment artifact rather than exploding into 12 microservices prematurely.
**Consequences:** Easier refactoring and local development while maintaining clean domain boundaries.
