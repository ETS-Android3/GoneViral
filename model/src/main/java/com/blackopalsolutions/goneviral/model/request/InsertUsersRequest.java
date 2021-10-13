package com.blackopalsolutions.goneviral.model.request;

import com.blackopalsolutions.goneviral.model.domain.User;

import java.util.List;

public class InsertUsersRequest {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
