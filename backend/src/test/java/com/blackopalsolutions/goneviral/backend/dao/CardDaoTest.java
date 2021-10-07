package com.blackopalsolutions.goneviral.backend.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.blackopalsolutions.goneviral.backend.model.domain.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardDaoTest {
    private CardDao dao;

    @BeforeEach
    public void setup() {
        dao = spy(CardDao.class);
    }

    @Nested
    @DisplayName("Insert Tests")
    public class InsertTests {
        @Test
        @DisplayName("Should not insert when inserting nothing")
        public void should_NotInsert_when_InsertingNothing() throws SQLException, DatabaseAccessException {
            // setup mock
            Connection connection = mock(Connection.class);
            doReturn(connection).when(dao).getConnection(anyString(), anyString(), anyString());
            doReturn(null).when(connection).prepareStatement(anyString());

            // test
            assertDoesNotThrow(() -> dao.insertCards());

            // expected
            verify(dao, times(1)).insertCards();
            verify(connection, times(0)).prepareStatement(anyString());
        }

        @Test
        @DisplayName("Should insert card into database when inserting single card")
        public void should_InsertCardIntoDatabase_when_InsertingSingleCard() throws SQLException, DatabaseAccessException {
            // setup mock
            Connection connection = mock(Connection.class);
            PreparedStatement statement = mock(PreparedStatement.class);
            doReturn(connection).when(dao).getConnection(anyString(), anyString(), anyString());
            doReturn(statement).when(connection).prepareStatement(anyString());

            // test
            Card card = new Card(1, "t", 0, "d", "e", "ti", 0, "b", "f");
            assertDoesNotThrow(() -> dao.insertCards(card));

            // expected
            verify(dao, times(1)).insertCards(card);
            verify(connection, times(1)).prepareStatement(anyString());
            verify(connection, times(1)).setAutoCommit(false);
            verify(connection, times(1)).commit();
            verifyStatement(statement, card);
            verify(statement, times(1)).addBatch();
            verify(statement, times(1)).executeBatch();
        }

        @Test
        @DisplayName("Should insert multiple into database when inserting multiple cards")
        public void should_InsertMultipleIntoDatabase_when_InsertingMultipleCards() throws SQLException, DatabaseAccessException {
            // setup mock
            Connection connection = mock(Connection.class);
            PreparedStatement statement = mock(PreparedStatement.class);
            doReturn(connection).when(dao).getConnection(anyString(), anyString(), anyString());
            doReturn(statement).when(connection).prepareStatement(anyString());

            // test
            Card c1 = new Card(1, "t1", 0, "d1", "e1", "ti1", 1, "b1", "f1");
            Card c2 = new Card(2, "t2", 1, "d2", "e2", "ti2", 2, "b2", "f2");
            Card c3 = new Card(3, "t3", 2, "d3", "e3", "ti3", 3, "b3", "f3");
            assertDoesNotThrow(() -> dao.insertCards(c1, c2, c3));

            // expected
            verify(dao, times(1)).insertCards(c1, c2, c3);
            verify(connection, times(1)).prepareStatement(anyString());
            verify(connection, times(1)).setAutoCommit(false);
            verify(connection, times(1)).commit();
            verifyStatement(statement, c1);
            verifyStatement(statement, c2);
            verifyStatement(statement, c3);
            verify(statement, times(3)).addBatch();
            verify(statement, times(1)).executeBatch();
        }

        @Test
        @DisplayName("Should throw exception when inserting card and connection throws exception")
        public void should_ThrowException_when_InsertingCardAndConnectionThrowsException() throws SQLException {
            // setup mock
            doThrow(SQLException.class).when(dao).getConnection(anyString(), anyString(), anyString());

            // test
            Card card = new Card(1, "t", 0, "d", "e", "ti", 0, "b", "f");
            assertThrows(DatabaseAccessException.class, () -> dao.insertCards(card));
        }

        @Test
        @DisplayName("Should throw exception when inserting card and statement throws exception")
        public void should_ThrowException_when_InsertingCardAndStatementThrowsException() throws SQLException {
            // setup mock
            Connection connection = mock(Connection.class);
            doReturn(connection).when(dao).getConnection(anyString(), anyString(), anyString());
            doThrow(SQLException.class).when(connection).prepareStatement(anyString());

            // test
            Card card = new Card(1, "t", 0, "d", "e", "ti", 0, "b", "f");
            assertThrows(DatabaseAccessException.class, () -> dao.insertCards(card));
        }

        private void verifyStatement(PreparedStatement statement, Card card) throws SQLException {
            verify(statement, times(1)).setInt(1, card.getCardId());
            verify(statement, times(1)).setString(2, card.getType());
            verify(statement, times(1)).setInt(3, card.getCost());
            verify(statement, times(1)).setString(4, card.getDescription());
            verify(statement, times(1)).setString(5, card.getEffect());
            verify(statement, times(1)).setString(6, card.getTitle());
            verify(statement, times(1)).setInt(7, card.getValue());
            verify(statement, times(1)).setString(8, card.getBackTexture());
            verify(statement, times(1)).setString(9, card.getFrontTexture());
        }
    }

    @Nested
    @DisplayName("Remove Tests")
    public class RemoveTests {
        @Test
        @DisplayName("Should remove card from database")
        public void should_RemoveCardFromDatabase() throws SQLException, DatabaseAccessException {
            // setup mock
            Connection connection = mock(Connection.class);
            PreparedStatement statement = mock(PreparedStatement.class);
            doReturn(connection).when(dao).getConnection(anyString(), anyString(), anyString());
            doReturn(statement).when(connection).prepareStatement(anyString());

            // test
            assertDoesNotThrow(() -> dao.removeCard("1"));

            // expected
            verify(dao, times(1)).removeCard("1");
            verify(connection, times(1)).prepareStatement(anyString());
            verify(connection, times(1)).setAutoCommit(false);
            verify(connection, times(1)).commit();
            verify(connection, times(0)).rollback();
            verify(statement, times(1)).setString(1, "1");
            verify(statement, times(1)).executeQuery();
        }

        @Test
        @DisplayName("Should do nothing when card is null")
        public void should_DoNothing_when_CardIsNull() throws SQLException, DatabaseAccessException {
            // test
            assertDoesNotThrow(() -> dao.removeCard(null));

            // expected
            verify(dao, times(1)).removeCard(null);
            verify(dao, times(0)).getConnection(anyString(), anyString(), anyString());
        }

        @Test
        @DisplayName("Should throw exception when card is not null and connection throws exception")
        public void should_ThrowException_when_CardNonnullAndConnectionThrowsException()
                throws SQLException, DatabaseAccessException {
            // setup mock
            doThrow(SQLException.class).when(dao).getConnection(anyString(), anyString(), anyString());

            // test
            assertThrows(DatabaseAccessException.class, () -> dao.removeCard("1"));

            // expected
            verify(dao, times(1)).removeCard("1");
            verify(dao, times(1)).getConnection(anyString(), anyString(), anyString());
        }

        @Test
        @DisplayName("Should throw exception when card is not null and connection throws exception")
        public void should_ThrowException_when_CardNonnullAndStatementThrowsException()
                throws SQLException, DatabaseAccessException {
            // setup mock
            Connection connection = mock(Connection.class);
            doReturn(connection).when(dao).getConnection(anyString(), anyString(), anyString());
            doThrow(SQLException.class).when(connection).prepareStatement(anyString());

            // test
            assertThrows(DatabaseAccessException.class, () -> dao.removeCard("1"));

            // expected
            verify(dao, times(1)).removeCard("1");
            verify(dao, times(1)).getConnection(anyString(), anyString(), anyString());
            verify(connection, times(1)).prepareStatement(anyString());
            verify(connection, times(0)).setAutoCommit(false);
            verify(connection, times(1)).rollback();
        }
    }
}