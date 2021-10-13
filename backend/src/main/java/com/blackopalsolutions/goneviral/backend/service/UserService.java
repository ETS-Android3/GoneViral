package com.blackopalsolutions.goneviral.backend.service;

import com.blackopalsolutions.goneviral.backend.dao.DatabaseAccessException;
import com.blackopalsolutions.goneviral.backend.dao.UserDao;
import com.blackopalsolutions.goneviral.model.domain.User;
import com.blackopalsolutions.goneviral.model.request.IdRequest;
import com.blackopalsolutions.goneviral.model.request.InsertUsersRequest;
import com.blackopalsolutions.goneviral.model.request.UpdateUserRequest;
import com.blackopalsolutions.goneviral.model.response.GetUserResponse;
import com.blackopalsolutions.goneviral.model.response.Response;

public class UserService {
    private final UserDao userDao = getUserDao();

    UserDao getUserDao() {
        return new UserDao();
    }

    /**
     * Insert users into the database.
     * @param request the request for insertion.
     * @return whether or not it was successful.
     */
    public Response insertUsers(InsertUsersRequest request) {
        try {
            userDao.createUsers(request.getUsers().toArray(new User[0]));
            return new Response(true);
        } catch (DatabaseAccessException e) {
            return new Response(e.getMessage());
        }
    }

    /**
     * Retrieve a user from the database.
     * @param request the request for retrieval.
     * @return whether or not it was successful.
     */
    public GetUserResponse getUser(IdRequest request) {
        try {
            User user = userDao.getUser(request.getId());
            return new GetUserResponse(user);
        } catch (DatabaseAccessException e) {
            return new GetUserResponse(e.getMessage());
        }
    }

    /**
     * Update a user in the database.
     * @param request the request to update a user.
     * @return whether or not it was successful.
     */
    public Response updateUser(UpdateUserRequest request) {
        try {
            userDao.updateUser(request.getUser());
            return new Response(true);
        } catch (DatabaseAccessException e) {
            return new Response(e.getMessage());
        }
    }

    /**
     * Remove a user from the database.
     * @param request the request for removal.
     * @return whether or not it was a success.
     */
    public Response removeUser(IdRequest request) {
        try {
            userDao.removeUser(request.getId());
            return new Response(true);
        } catch (DatabaseAccessException e) {
            return new Response(e.getMessage());
        }
    }
}
