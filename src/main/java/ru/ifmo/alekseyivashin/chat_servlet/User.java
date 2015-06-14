package ru.ifmo.alekseyivashin.chat_servlet;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

/**
 * Created by Alexey on 14.06.2015.
 */
public class User implements JSONAware  {
    private String login;
    private String password;

    /**
     * Конструктор пользователя.
     * @param login Логин.
     * @param password пароль.
     */
    public User(String login, String password) {
        this.login= login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String toJSONString() {
        JSONObject obj = new JSONObject();
        obj.put("password", password);
        obj.put("login", login);
        return obj.toJSONString();
    }
}
