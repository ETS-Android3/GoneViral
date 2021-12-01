package com.blackopalsolutions.goneviral.backend.dao;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.blackopalsolutions.goneviral.model.domain.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao extends Dao {
    private static final String tableName = "Roles";
    private static final String partitionKey = "title";
    private static final String goalAttr = "goal_name";
    private static final String abilityAttr = "ability";
    private static final String frontTextureAttr = "front_texture";
    private static final String backTextureAttr = "back_texture";

    /**
     * Retrieve a role from the database.
     * @param title the title of the role to retrieve.
     * @return the retrieved role.
     * @throws DatabaseAccessException if there was an error accessing the database.
     * @throws ServerException if there was an error processing the request.
     */
    public Role getRole(String title) throws DatabaseAccessException, ServerException {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey(partitionKey, title);
        Item outcome;
        try {
            outcome = getTable(tableName).getItem(spec);
            if (outcome == null) {
                throw new ServerException("Role with given title does not exist.");
            }
        } catch (ServerException e) {
            throw e;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DatabaseAccessException("Could not retrieve role from database.");
        }

        String goal = outcome.getString(goalAttr);
        String ability = outcome.getString(abilityAttr);
        String frontTexture = outcome.getString(frontTextureAttr);
        String backTexture = outcome.getString(backTextureAttr);

        // TODO: remove ID, add goal
        return new Role(0, ability, title, backTexture, frontTexture);
    }

    /**
     * Retrieves a role from the database.
     * @param id the id of the role to retrieve.
     * @return the role if it exists, otherwise null.
     * @throws DatabaseAccessException if error accessing the database.
     * @deprecated
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
