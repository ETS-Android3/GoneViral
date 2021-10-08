package com.blackopalsolutions.goneviral.backend.dao;

import com.blackopalsolutions.goneviral.backend.model.domain.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                    int cardId = rs.getInt("id");
                    String type = rs.getString("type");
                    int cost = rs.getInt("cost");
                    String description = rs.getString("description");
                    String effect = rs.getString("effect");
                    String title = rs.getString("title");
                    int value = rs.getInt("value");
                    String back = rs.getString("back_texture");
                    String front = rs.getString("front_texture");
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
}
