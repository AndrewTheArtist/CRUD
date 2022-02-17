import express, { Request, Response } from "express";
import { memberController } from '../../controllers';

export const router = express.Router({
    strict: true
});

router.post('/', (req: Request, res: Response) => {
    memberController.create(req, res);
});

router.get('/', (req: Request, res: Response) => {
    memberController.read(req, res);
});

router.get('/:id', (req: Request, res: Response) => {
    memberController.readOne(req, res);
});

router.get('/:id/groups', (req: Request, res: Response) => {
    memberController.groups(req, res);
});

router.patch('/:id', (req: Request, res: Response) => {
    memberController.update(req, res);
});

router.delete('/:id', (req: Request, res: Response) => {
    memberController.delete(req, res);
});