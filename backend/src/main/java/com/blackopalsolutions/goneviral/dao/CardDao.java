package com.blackopalsolutions.goneviral.dao;

import com.blackopalsolutions.goneviral.model.Card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardDao extends Dao {
    private static final Logger log = LoggerFactory.getLogger(CardDao.class);

    /**
     * Inserts cards into the database.
     * @param cards the cards to insert.
     * @throws DatabaseAccessException if there was an error accessing the database.
     */
    public void insertCards(Card... cards) throws DatabaseAccessException {
        if (cards == null || cards.length == 0) {
            log.warn("Attempted to insert 0 cards.");
            return;
        }

        try (Connection con = getConnection()) {
            String query = "INSERT INTO cards(cardId, type, cost, description,"
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
                } catch (SQLException ex) {
                    log.warn("Couldn't rollback transaction", ex);
                }

                log.error("Couldn't prepare the statement!", e);
                throw new DatabaseAccessException();
            }
        } catch (SQLException e) {
            log.error("Couldn't establish a connection to the database!", e);
            throw new DatabaseAccessException();
        }
    }
}
