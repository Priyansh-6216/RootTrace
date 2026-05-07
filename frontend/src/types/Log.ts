export interface LogEntry {
  id?: number;
  timestamp: string;
  serviceName: string;
  logLevel: string;
  traceId?: string;
  spanId?: string;
  message: string;
  exceptionType?: string;
  stackTrace?: string;
  environment: string;
}
