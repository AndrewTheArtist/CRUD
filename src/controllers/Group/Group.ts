import { Request, Response } from 'express';
import { CrudController } from '../CrudController';
import { collections } from "../../database.service";
import Group from '../../models/Group';

export class GroupController extends CrudController {
    public async create(req: Request<import("express-serve-static-core").ParamsDictionary>, res: Response) {
        const newGroup = req.body as Group;
        const result = await collections.group.insertOne(newGroup);
        result ? res.status(201).send("Successfully created") : res.status(500).send("Failed creating");
    }

    public async read(req: Request<import("express-serve-static-core").ParamsDictionary>, res: Response) {
        const result = await collections.group.find({}).toArray();
        res.json(result);
    }

    public async readOne(req: Request<import("express-serve-static-core").ParamsDictionary>, res: Response) {
        const id = req.path.replace("/","");
        const result = await collections.group.findOne({id: id});
        result===null ? res.status(400).send("Not found") : res.status(200).json(result);
    }

    public async update(req: Request<import("express-serve-static-core").ParamsDictionary>, res: Response) {
        const id = { id: req.path.replace("/","") };
        const body = req.body;
        const result = await collections.group.updateOne(id, {$set: body}, {upsert: false});
        if(result.matchedCount===0) {return res.status(500).send("Not found")};
        result.modifiedCount ? res.status(201).send("Successfully updated") : res.status(500).send("Failed updating");
    }

    public async delete(req: Request<import("express-serve-static-core").ParamsDictionary>, res: Response) {
        const query = { id: req.path.replace("/","") };
        const result = await collections.group.findOneAndDelete(query);
        result.value ? res.status(202).send("Successfully deleted") : res.status(400).send("Failed to delete");
    }
}