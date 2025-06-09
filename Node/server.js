import express from 'express';
import winston from 'winston';

const app = express();
const port = 3000;

// Basic logger setup
const logger = winston.createLogger({
  level: 'info',
  format: winston.format.simple(), 
  transports: [new winston.transports.Console()],
});

app.use(express.json());

app.post('/api/data', (req, res) => {
  logger.info(`Received: ${JSON.stringify(req.body)}`);
  res.status(200).json({ message: 'Received successfully from Node.js server' });
});

app.listen(port, () => {
  logger.info(`Server running at http://localhost:${port}`);
});
