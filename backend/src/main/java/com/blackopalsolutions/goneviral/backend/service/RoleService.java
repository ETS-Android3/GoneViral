package com.blackopalsolutions.goneviral.backend.service;

import com.blackopalsolutions.goneviral.backend.dao.DatabaseAccessException;
import com.blackopalsolutions.goneviral.backend.dao.RoleDao;
import com.blackopalsolutions.goneviral.backend.model.domain.Role;
import com.blackopalsolutions.goneviral.backend.model.request.IdRequest;
import com.blackopalsolutions.goneviral.backend.model.response.GetRoleResponse;

public class RoleService {
    private final RoleDao dao = getRoleDao();

    RoleDao getRoleDao() {
        return new RoleDao();
    }

    /**
     * Retrieves a role from the database.
     * @param request the request for retrieval.
     * @return whether or not the retrieval was a success.
     */
    public GetRoleResponse getRole(IdRequest request) {
        try {
            Role role  = dao.getRole(request.getId());
            return new GetRoleResponse(role);
        } catch (DatabaseAccessException e) {
            return new GetRoleResponse(e.getMessage());
        }
    }
}
