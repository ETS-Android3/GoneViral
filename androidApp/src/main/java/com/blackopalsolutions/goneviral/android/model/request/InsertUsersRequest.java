package com.blackopalsolutions.goneviral.android.model.request;

import com.blackopalsolutions.goneviral.android.model.domain.User;

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
