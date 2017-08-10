package com.epam.models;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Users")
public class Users {

    private List<UserModel> users;

    public List<UserModel> getUsers() {
        return users;
    }

    @XmlElement(name = "User")
    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
