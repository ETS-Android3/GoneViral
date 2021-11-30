package com.blackopalsolutions.goneviral.backend.dao;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.blackopalsolutions.goneviral.model.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao {
    private static final String tableName = "Users";
    private static final String partitionKey = "username";
    private static final String passwordAttr = "password";
    private static final String gamesWonAttr = "games_won";
    private static final String gamesLostAttr = "games_lost";

    /**
     * Add a user to the database.
     * @param user the user to add.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public void createUser(User user) throws DatabaseAccessException {
        Table table = getTable(tableName);
        try {
            table.putItem(new Item()
                .withPrimaryKey(partitionKey, user.getUsername())
                .withString(passwordAttr, user.getPassword())
                .withInt(gamesWonAttr, user.getGamesWon())
                .withInt(gamesLostAttr, user.getGamesLost()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DatabaseAccessException("Could not add user to database.");
        }
    }

    /**
     * Retrieve a user from the database.
     * @param username the username of the user to retrieve.
     * @return the retrieved user.
     * @throws DatabaseAccessException if there was an error accessing the database.
     * @throws ServerException if there was an error processing the request.
     */
    public User getUser(String username) throws DatabaseAccessException, ServerException {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey(partitionKey, username);
        Item outcome;
        try {
            outcome = getTable(tableName).getItem(spec);
            if (outcome == null) {
                throw new ServerException("User with given username does not exist.");
            }
        } catch (ServerException e) {
            throw e;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DatabaseAccessException("Could not retrieve user from database.");
        }

        String password = outcome.getString(passwordAttr);
        int gamesWon = outcome.getInt(gamesWonAttr);
        int gamesLost = outcome.getInt(gamesLostAttr);

        // TODO: remove ID
        return new User(0, username, password, gamesWon, gamesLost);
    }

    /**
     * Updates a user in the database.
     * @param user the user to update.
     * @throws DatabaseAccessException if there was an error accessing the database.
     * TODO: change method name
     */
    public void updateNewUser(User user) throws DatabaseAccessException {
        UpdateItemSpec spec = new UpdateItemSpec()
                .withPrimaryKey(partitionKey, user.getUsername())
                .withUpdateExpression("set " + passwordAttr + " = :p,"
                                              + gamesWonAttr + " = :w,"
                                              + gamesLostAttr + " = :l")
                .withValueMap(new ValueMap()
                                      .withString(":p", user.getPassword())
                                      .withInt(":w", user.getGamesWon())
                                      .withInt(":l", user.getGamesLost()));
        try {
            getTable(tableName).updateItem(spec);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DatabaseAccessException("Could not update user.");
        }
    }

    /**
     * Removes a user from the database.
     * @param username the username of the user to remove.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public void deleteUser(String username) throws DatabaseAccessException {
        DeleteItemSpec spec = new DeleteItemSpec().withPrimaryKey(partitionKey, username);
        try {
            getTable(tableName).deleteItem(spec);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DatabaseAccessException("Could not delete user.");
        }
    }

    /**
     * Adds users to the database.
     * @param users the users to add to the database.
     * @throws DatabaseAccessException if there was an error accessing the database.
     * @deprecated
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
     * @deprecated
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
     * @deprecated
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
     * @deprecated
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
