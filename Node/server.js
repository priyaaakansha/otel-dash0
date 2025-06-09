import express from 'express';

const app = express();
const port = 3000;

app.use(express.json());

// POST route
app.post('/api/data', (req, res) => {
    console.log('Received data from Java client:', req.body);
    res.status(200).json({ message: 'Received successfully from Node js server' });
});

app.listen(port, () => {
    console.log(`Server is listening on http://localhost:${port}`);
});
