package com.blackopalsolutions.goneviral.backend.service;

import com.blackopalsolutions.goneviral.backend.dao.DatabaseAccessException;
import com.blackopalsolutions.goneviral.backend.dao.RoleDao;
import com.blackopalsolutions.goneviral.backend.dao.RoleGoalDao;
import com.blackopalsolutions.goneviral.model.domain.Role;
import com.blackopalsolutions.goneviral.model.request.IdRequest;
import com.blackopalsolutions.goneviral.model.response.GetRoleResponse;

public class RoleService {
    private final RoleDao roleDao = getRoleDao();
    private final RoleGoalDao rgDao = getRoleGoalDao();

    RoleDao getRoleDao() {
        return new RoleDao();
    }

    RoleGoalDao getRoleGoalDao() {
        return new RoleGoalDao();
    }

    /**
     * Retrieves a role from the database.
     * @param request the request for retrieval.
     * @return whether or not the retrieval was a success.
     */
    public GetRoleResponse getRole(IdRequest request) {
        try {
            Role role  = roleDao.getRole(request.getId());
            return new GetRoleResponse(role);
        } catch (DatabaseAccessException e) {
            return new GetRoleResponse(e.getMessage());
        }
    }

    /**
     * Retrieves a role from the database, given a goal id.
     * @param request the request for retrieval.
     * @return whether or not the retrieval was a success.
     */
    public GetRoleResponse getRoleFromGoal(IdRequest request) {
        try {
            Role role = rgDao.getRoleFromGoal(request.getId());
            return new GetRoleResponse(role);
        } catch (DatabaseAccessException e) {
            return new GetRoleResponse(e.getMessage());
        }
    }
}
