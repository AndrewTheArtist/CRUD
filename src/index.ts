import express from 'express';
const PORT: number = 8080;
import { groupRouter } from './routes';
import { memberRouter } from './routes';
import { connectToDatabase } from './database.service';

const app = express();
app.use(express.json());

app.use('/groups', groupRouter);
app.use('/members', memberRouter);

connectToDatabase();

app.listen(PORT, () => {
    console.log(`Server is listening on port ${PORT}`);
});