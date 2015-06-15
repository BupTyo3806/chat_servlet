package ru.ifmo.alekseyivashin.chat_servlet;

import com.mongodb.*;
import com.mongodb.util.JSON;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


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

    public Record[] findAllRecords() throws UnknownHostException {
        init();
        DBCursor cursor = recordTable.find();
        if (cursor.count() == 0) {
            return null;
        }
        List<Record> recordList = new ArrayList<Record>();
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            String login = obj.get("login").toString();
            String text = obj.get("text").toString();
            String date = obj.get("date").toString();
            recordList.add(new Record(login, text, date));
        }
        Record[] records = recordList.toArray(new Record[recordList.size()]);
        return records;
    }
}
