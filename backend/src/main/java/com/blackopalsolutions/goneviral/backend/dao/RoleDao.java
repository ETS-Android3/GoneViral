package com.blackopalsolutions.goneviral.backend.dao;

import com.blackopalsolutions.goneviral.backend.model.domain.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao extends Dao {

    /**
     * Retrieves a role from the database.
     * @param id the id of the role to retrieve.
     * @return the role if it exists, otherwise null.
     * @throws DatabaseAccessException if error accessing the database.
     */
    public Role getRole(int id) throws DatabaseAccessException {
        Role role = null;

        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM roles WHERE id = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                // begin transaction
                con.setAutoCommit(false);

                // find role
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    int roleId = rs.getInt("id");
                    String ability = rs.getString("ability");
                    String title = rs.getString("title");
                    String backTexture = rs.getString("back_texture");
                    String frontTexture = rs.getString("front_texture");
                    role = new Role(roleId, ability, title, backTexture, frontTexture);
                }

                // end transaction
                con.commit();
            } catch (SQLException e) {
                // undo transaction
                try {
                    con.rollback();
                } catch (SQLException ignored) {}

                throw new DatabaseAccessException("Couldn't retrieve role.");
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access the database.");
        }

        return role;
    }
}
