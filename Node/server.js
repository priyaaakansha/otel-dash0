import express from 'express';
import logger from './logger.js';

const app = express();
const port = 3000;

app.use(express.json());

app.post('/api/data', (req, res) => {
  logger.info('Payload received', { body: req.body });
  res.status(200).json({ message: 'Received successfully from NodeJS server' });
});

app.listen(port, () => {
  logger.info(`Server running at http://localhost:${port}`);
});
