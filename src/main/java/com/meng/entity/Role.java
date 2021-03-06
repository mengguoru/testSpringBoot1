package com.meng.entity;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
    private int id;
    private String name;
    private String description;

    private List<User3> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public List<User3> getUsers() {
        return users;
    }

    public void setUsers(List<User3> users) {
        this.users = users;
    }
}
