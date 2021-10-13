package com.blackopalsolutions.goneviral.backend.dao;

import com.blackopalsolutions.goneviral.model.domain.Goal;
import com.blackopalsolutions.goneviral.model.domain.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleGoalDao extends Dao {

    /**
     * Retrieves a goal from the database, given the role id it is paired to.
     * @param id the id of the role whose goal should be retrieved.
     * @return the goal pertaining to the role, or null if it does not exist.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public Goal getGoalFromRole(int id) throws DatabaseAccessException {
        Goal goal = null;

        try (Connection con = getConnection()) {
            String sql = "SELECT goal_id FROM role_goals WHERE role_id = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                // begin transaction
                con.setAutoCommit(false);

                // initialize query and retrieve goal
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    int goalId = rs.getInt(1);
                    goal = (new GoalDao()).getGoal(goalId);
                }

                // end transaction
                con.commit();
            } catch (SQLException e) {
                // undo transaction
                try {
                    con.rollback();
                } catch (SQLException ignored) {}

                throw new DatabaseAccessException("Couldn't retrieve goal.");
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access database.");
        }

        return goal;
    }

    /**
     * Retrieves a role from the database, given the goal id it is paired to.
     * @param id the id of the goal whose role should be retrieved.
     * @return the role pertaining to the goal, or null if it does not exist.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public Role getRoleFromGoal(int id) throws DatabaseAccessException {
        Role role = null;

        try (Connection con = getConnection()) {
            String sql = "SELECT role_id FROM role_goals WHERE goal_id = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                // begin transaction
                con.setAutoCommit(false);

                // initialize query and retrieve role
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    int roleId = rs.getInt(1);
                    role = (new RoleDao()).getRole(roleId);
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
            throw new DatabaseAccessException("Couldn't access database.");
        }

        return role;
    }
}
