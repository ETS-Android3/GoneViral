package com.blackopalsolutions.goneviral.model.response;

import com.blackopalsolutions.goneviral.model.domain.User;

public class GetUserResponse extends Response {
    private final User user;

    public GetUserResponse(User user) {
        super(true);
        this.user = user;
    }

    public GetUserResponse(String message) {
        super(message);
        this.user = null;
    }

    public User getUser() {
        return user;
    }
}
