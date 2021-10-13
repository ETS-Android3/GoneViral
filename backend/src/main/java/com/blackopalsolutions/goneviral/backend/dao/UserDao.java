package com.blackopalsolutions.goneviral.backend.dao;

import com.blackopalsolutions.goneviral.model.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao {

    /**
     * Adds users to the database.
     * @param users the users to add to the database.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public void createUsers(User... users) throws DatabaseAccessException {
        if (users == null || users.length == 0) {
            throw new IllegalArgumentException("Users cannot be empty");
        }

        try (Connection con = getConnection()) {
            String sql = "INSERT INTO users (id, username, password, games_won, games_lost)"
                    + " VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                // begin transaction
                con.setAutoCommit(false);

                // prepare query
                for (User user : users) {
                    st.setInt(1, user.getId());
                    st.setString(2, user.getUsername());
                    st.setString(3, user.getPassword());
                    st.setInt(4, user.getGamesWon());
                    st.setInt(5, user.getGamesLost());
                    st.addBatch();
                }

                // execute and end transaction
                st.executeBatch();
                con.commit();
            } catch (SQLException e) {
                // undo tranasaction
                try {
                    con.rollback();
                } catch (SQLException ignored) {}

                throw new DatabaseAccessException("Couldn't create users.");
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access database.");
        }
    }

    /**
     * Retrieves a user from the database.
     * @param id the id of the user to retrieve.
     * @return the user pertaining to the id, or null if doesn't exist.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public User getUser(int id) throws DatabaseAccessException {
        User user = null;

        try (Connection con = getConnection()) {
            String sql = "SELECT username, password, games_won, games_lost FROM users WHERE id = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                // begin transaction
                con.setAutoCommit(false);

                // prepare and execute query
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    String username = rs.getString(1);
                    String password = rs.getString(2);
                    int gamesWon = rs.getInt(3);
                    int gamesLost = rs.getInt(4);
                    user = new User(id, username, password, gamesWon, gamesLost);
                }

                // end transaction
                con.commit();
            } catch (SQLException e) {
                // undo transaction
                try {
                    con.rollback();
                } catch (SQLException ignored) {}

                throw new DatabaseAccessException("Couldn't retrieve user.");
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access database.");
        }
        
        return user;
    }

    /**
     * Updates a user in the database.
     * @param user the user to update.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public void updateUser(User user) throws DatabaseAccessException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        try (Connection con = getConnection()) {
            String sql = "UPDATE users"
                    + " SET username = ?, password = ?, games_won = ?, games_lost = ?"
                    + " WHERE id = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                // begin transaction
                con.setAutoCommit(false);

                // prepare query
                st.setString(1, user.getUsername());
                st.setString(2, user.getPassword());
                st.setInt(3, user.getGamesWon());
                st.setInt(4, user.getGamesLost());
                st.setInt(5, user.getId());

                // execute and end transaction
                st.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                // undo transaction
                try {
                    con.rollback();
                } catch (SQLException ignored) {}

                throw new DatabaseAccessException("Couldn't update user.");
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access database.");
        }
    }

    /**
     * Removes a user from the database.
     * @param id the id of the user to remove.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public void removeUser(int id) throws DatabaseAccessException {
        try (Connection con = getConnection()) {
            String sql = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                // begin transaction
                con.setAutoCommit(false);

                // prepare query
                st.setInt(1, id);

                // execute and end transaction
                st.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                // undo transaction
                try {
                    con.rollback();
                } catch (SQLException ignored) {}

                throw new DatabaseAccessException("Couldn't remove user.");
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access database.");
        }
    }
}
