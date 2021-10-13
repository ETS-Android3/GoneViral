package com.blackopalsolutions.goneviral.model.request;

import com.blackopalsolutions.goneviral.model.domain.User;

public class UpdateUserRequest {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
