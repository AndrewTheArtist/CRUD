import { Request, Response } from 'express';
import { CrudController } from '../CrudController';
import { collections } from "../../database.service";
import Member from '../../models/Member';

export class MemberController extends CrudController {
    public async create(req: Request<import("express-serve-static-core").ParamsDictionary>, res: Response) {
        const newMember = req.body as Member;
        const result = await collections.member.insertOne(newMember);
        result ? res.status(201).send("Successfully created") : res.status(500).send("Failed creating");
    }

    public async read(req: Request<import("express-serve-static-core").ParamsDictionary>, res: Response) {
        const result = await collections.member.find({}).toArray();
        res.json(result);
    }

    public async readOne(req: Request<import("express-serve-static-core").ParamsDictionary>, res: Response) {
        const id = req.path.replace("/","");
        const result = await collections.member.findOne({id: id});
        result===null ? res.status(404).send("No such member") : res.status(200).json(result);
    }

    public async groups(req: Request<import("express-serve-static-core").ParamsDictionary>, res: Response) {
        const id = req.path.match(/\d+/)[0];
        const member = await collections.member.findOne({id: id});
        if(!member) {return res.status(404).send("No such member")}
        const groups = JSON.parse(member.groups);
        groups.forEach(function(e:any, i:number){groups[i]=e.toString()});
        const result = await collections.group.find({id: {$in: groups}}).toArray();
        res.status(200).json(result);
    }

    public async update(req: Request<import("express-serve-static-core").ParamsDictionary>, res: Response) {
        const id = { id: req.path.replace("/","") };
        const body = req.body;
        const result = await collections.member.updateOne(id, {$set: body}, {upsert: false});
        if(result.matchedCount===0) {return res.status(404).send("No such update")};
        result.modifiedCount ? res.status(200).send("Successfully updated") : res.status(500).send("Failed updating");
    }

    public async delete(req: Request<import("express-serve-static-core").ParamsDictionary>, res: Response) {
        const query = { id: req.path.replace("/","") };
        const result = await collections.member.findOneAndDelete(query);
        result.value ? res.status(200).send("Successfully deleted") : res.status(500).send("Failed to delete");
    }
}