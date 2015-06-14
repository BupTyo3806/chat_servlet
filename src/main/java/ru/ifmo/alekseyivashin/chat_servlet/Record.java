package ru.ifmo.alekseyivashin.chat_servlet;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

/**
 * Created by Alexey on 14.06.2015.
 */
public class Record implements JSONAware {
    private String login;
    private String text;
    private String date;

    /**
     * Конструктор записи.
     * @param login логин пользователя.
     * @param text текст записи.
     * @param date время записи
     */
    public Record(String login, String text, String date) {
        this.login= login;
        this.text = text;
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public String toJSONString() {
        JSONObject obj = new JSONObject();
        obj.put("login", login);
        obj.put("text", text);
        obj.put("date", date);
        return obj.toJSONString();
    }
}
