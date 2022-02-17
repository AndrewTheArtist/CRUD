import * as mongoDB from "mongodb";
import * as dotenv from "dotenv";

export const collections: { 
    member?: mongoDB.Collection,
    group?: mongoDB.Collection
} = {}

export async function connectToDatabase () {
    dotenv.config();
 
    const client: mongoDB.MongoClient = new mongoDB.MongoClient(process.env.DB_CONN_STRING);
            
    await client.connect();
        
    const db: mongoDB.Db = client.db(process.env.DB_NAME);
   
    const groupCollection: mongoDB.Collection = db.collection("Group");
    const memberCollection: mongoDB.Collection = db.collection("Member");
 
    collections.group = groupCollection;
    collections.member = memberCollection;
    
       
         console.log(`Successfully connected to database: ${db.databaseName}`);
 }