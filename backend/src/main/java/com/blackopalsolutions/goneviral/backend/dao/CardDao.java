package com.blackopalsolutions.goneviral.backend.dao;

import com.blackopalsolutions.goneviral.model.domain.Card;

import org.w3c.dom.stylesheets.LinkStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDao extends Dao {
    /**
     * Retrieves a card from the database.
     * @param id the id of the card to retrieve.
     * @return null if the card isn't in the database, otherwise the card.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public Card getCard(int id) throws DatabaseAccessException {
        Card card = null;

        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM cards WHERE id = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                // begin transaction
                con.setAutoCommit(false);

                // find card
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    card = decodeCard(rs);
                }

                // end transaction
                con.commit();
            } catch (SQLException e) {
                // undo transaction
                try {
                    con.rollback();
                } catch (SQLException ignored) {
                    throw new DatabaseAccessException("Couldn't undo transaction.");
                }

                throw new DatabaseAccessException("Couldn't retrieve card.");
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access database.");
        }

        return card;
    }

    /**
     * Retrieves all the cards from the database.
     * @return empty list if no cards, otherwise all cards in the database
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public List<Card> getCards() throws DatabaseAccessException {
        List<Card> cards = new ArrayList<>();

        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM cards";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                // begin transaction
                con.setAutoCommit(false);

                // retrieve cards
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    cards.add(decodeCard(rs));
                }

                // end transaction
                con.commit();
            } catch (SQLException e) {
                // undo transaction
                try {
                    con.rollback();
                } catch (SQLException ignored) {
                    throw new DatabaseAccessException("Couldn't undo transaction.");
                }

                throw new DatabaseAccessException("Couldn't retrieve cards");
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException("Couldn't access database.");
        }

        return cards;
    }

    private Card decodeCard(ResultSet rs) throws SQLException {
        int cardId = rs.getInt("id");
        String type = rs.getString("type");
        int cost = rs.getInt("cost");
        String description = rs.getString("description");
        String effect = rs.getString("effect");
        String title = rs.getString("title");
        int value = rs.getInt("value");
        String back = rs.getString("back_texture");
        String front = rs.getString("front_texture");
        return new Card(cardId, type, cost, description, effect, title, value, back, front);
    }
}
