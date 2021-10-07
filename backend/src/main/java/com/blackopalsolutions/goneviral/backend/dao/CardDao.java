package com.blackopalsolutions.goneviral.backend.dao;

import com.blackopalsolutions.goneviral.backend.model.domain.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardDao extends Dao {
    /**
     * Inserts cards into the database.
     * @param cards the cards to insert.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public void insertCards(Card... cards) throws DatabaseAccessException {
        if (cards == null || cards.length == 0) {
            return;
        }

        try (Connection con = getConnection()) {
            String query = "INSERT INTO cards(id, type, cost, description,"
                    + " effect, title, value, backTexture, frontTexture)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement st = con.prepareStatement(query)) {
                // begin transaction
                con.setAutoCommit(false);

                // create row and add to batch
                for (Card card : cards) {
                    st.setString(1, card.getCardId());
                    st.setString(2, card.getType());
                    st.setInt(3, card.getCost());
                    st.setString(4, card.getDescription());
                    st.setString(5, card.getEffect());
                    st.setString(6, card.getTitle());
                    st.setInt(7, card.getValue());
                    st.setString(8, card.getBackTexture());
                    st.setString(9, card.getFrontTexture());
                    st.addBatch();
                }

                // execute and end transaction
                st.executeBatch();
                con.commit();
            } catch (SQLException e) {
                // undo transaction
                try {
                    con.rollback();
                } catch (SQLException ignored) {}

                throw new DatabaseAccessException();
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException();
        }
    }

    /**
     * Retrieves a card from the database.
     * @param id the id of the card to retrieve.
     * @return null if the card isn't in the database, otherwise the card.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public Card getCard(String id) throws DatabaseAccessException {
        Card card = null;

        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM cards WHERE id = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                // begin transaction
                con.setAutoCommit(false);

                // find card
                st.setString(1, id);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    String cardId = rs.getString("id");
                    String type = rs.getString("type");
                    int cost = rs.getInt("cost");
                    String description = rs.getString("description");
                    String effect = rs.getString("effect");
                    String title = rs.getString("title");
                    int value = rs.getInt("value");
                    String back = rs.getString("backTexture");
                    String front = rs.getString("frontTexture");
                    card = new Card(cardId, type, cost, description, effect, title, value, back, front);
                }

                // end transaction
                con.commit();
            } catch (SQLException e) {
                // undo transaction
                try {
                    con.rollback();
                } catch (SQLException ignored) {}

                throw new DatabaseAccessException();
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException();
        }

        return card;
    }

    /**
     * Updates the card in the database.
     * @param card the card to update.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public void updateCard(Card card) throws DatabaseAccessException {
        if (card == null) {
            return;
        }

        try (Connection con = getConnection()) {
            String sql = "UPDATE cards SET type = ?, cost = ?, description = ?,"
                    + " effect = ?, title = ?, value = ?, backTexture = ?, frontTexture = ?"
                    + " WHERE id = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                // begin transaction
                con.setAutoCommit(false);

                // update row
                st.setString(1, card.getType());
                st.setInt(2, card.getCost());
                st.setString(3, card.getDescription());
                st.setString(4, card.getEffect());
                st.setString(5, card.getTitle());
                st.setInt(6, card.getValue());
                st.setString(7, card.getBackTexture());
                st.setString(8, card.getFrontTexture());
                st.setString(9, card.getCardId());

                // execute and end transaction
                st.executeQuery();
                con.commit();
            } catch (SQLException e) {
                // undo transaction
                try {
                    con.rollback();
                } catch (SQLException ignored) {}

                throw new DatabaseAccessException();
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException();
        }
    }

    /**
     * Removes a card from the database.
     * @param id the id of the card to remove.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public void removeCard(String id) throws DatabaseAccessException {
        try (Connection con = getConnection()) {
            String sql = "DELETE FROM cards WHERE id = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                con.setAutoCommit(false);
                st.setString(1, id);
                st.executeQuery();
                con.commit();
            } catch (SQLException e) {
                // undo transaction
                try {
                    con.rollback();
                } catch (SQLException ignored) {}

                throw new DatabaseAccessException();
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException();
        }
    }
}
