package ru.ifmo.alekseyivashin.chat_servlet;

import com.mongodb.*;
import com.mongodb.util.JSON;

import java.net.UnknownHostException;


/**
 * Created by Alexey on 14.06.2015.
 */
public class MongoDB {

    public DB db;
    public DBCollection userTable;
    public DBCollection recordTable;

    public void MongoDB() throws UnknownHostException {
        init();
    }

    public void init() throws UnknownHostException {
        Mongo mongo = new Mongo();
        this.db = mongo.getDB("java");
        this.userTable = db.getCollection("users");
        this.recordTable = db.getCollection("records");
    }

    public void addUser(User user) throws UnknownHostException {
        init();
        DBObject document = (DBObject) JSON.parse(user.toJSONString());
        userTable.insert(document);
    }

    public void addRecord(Record record) throws UnknownHostException {
        init();
        DBObject document = (DBObject) JSON.parse(record.toJSONString());
        recordTable.insert(document);
    }

    public User findUser(String login) throws UnknownHostException {
        init();
        BasicDBObject query = new BasicDBObject();
        query.put("login", login);
        DBCursor cursor = userTable.find(query);
        if (cursor.count() == 0) {
            return null;
        } else {
            DBObject obj = cursor.next();
            User user = new User(obj.get("login").toString(), obj.get("password").toString());
            return user;
        }
    }

    public Record findRecord() throws UnknownHostException {
        init();
        DBObject query = recordTable.findOne();
        Record record = new Record(query.get("login").toString(), query.get("text").toString(), query.get("date").toString());
        return record;
    }
}
