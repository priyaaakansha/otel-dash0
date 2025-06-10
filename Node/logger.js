import winston from 'winston';
import { trace, context } from '@opentelemetry/api';

const traceFormat = winston.format((info) => {
  const span = trace.getSpan(context.active());
  if (span) {
    const spanContext = span.spanContext();
    info.trace_id = spanContext.traceId;
    info.span_id = spanContext.spanId;
    info.trace_flags = spanContext.traceFlags?.toString(16).padStart(2, '0');
  }
  return info;
});

const logger = winston.createLogger({
  level: 'info',
  format: winston.format.combine(
    traceFormat(),                        
    winston.format.timestamp(),
    winston.format.json()
  ),
  transports: [new winston.transports.Console()],
});

export default logger;
