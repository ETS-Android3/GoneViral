package com.blackopalsolutions.goneviral.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.blackopalsolutions.goneviral.model.Goal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GoalDaoTest {
  private GoalDao dao;

  @BeforeEach
  public void setup() {
    dao = spy(GoalDao.class);
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
      assertDoesNotThrow(() -> dao.insertGoals());

      // expected
      verify(dao, times(1)).insertGoals();
      verify(connection, times(0)).prepareStatement(anyString());
    }

    @Test
    @DisplayName("Should insert goal into database when inserting single goal")
    public void should_InsertGoalIntoDatabase_when_InsertingSingleGoal() throws SQLException, DatabaseAccessException {
      // setup mock
      Connection connection = mock(Connection.class);
      PreparedStatement statement = mock(PreparedStatement.class);
      doReturn(connection).when(dao).getConnection(anyString(), anyString(), anyString());
      doReturn(statement).when(connection).prepareStatement(anyString());

      // test
      Goal goal = new Goal("1", "2", "condition");
      assertDoesNotThrow(() -> dao.insertGoals(goal));

      // expected
      verify(dao, times(1)).insertGoals(goal);
      verify(connection, times(1)).prepareStatement(anyString());
      verify(connection, times(1)).setAutoCommit(false);
      verify(connection, times(1)).commit();
      verify(statement, times(1)).setString(1, "1");
      verify(statement, times(1)).setString(2, "2");
      verify(statement, times(1)).setString(3, "condition");
      verify(statement, times(1)).addBatch();
      verify(statement, times(1)).executeBatch();
    }

    @Test
    @DisplayName("Should insert multiple into database when inserting multiple goals")
    public void should_InsertMultipleIntoDatabase_when_InsertingMultipleGoals() throws SQLException, DatabaseAccessException {
      // setup mock
      Connection connection = mock(Connection.class);
      PreparedStatement statement = mock(PreparedStatement.class);
      doReturn(connection).when(dao).getConnection(anyString(), anyString(), anyString());
      doReturn(statement).when(connection).prepareStatement(anyString());

      // test
      Goal g1 = new Goal("1", "2", "c1");
      Goal g2 = new Goal("3", "4", "c2");
      Goal g3 = new Goal("5", "6", "c3");
      assertDoesNotThrow(() -> dao.insertGoals(g1, g2, g3));

      // expected
      verify(dao, times(1)).insertGoals(g1, g2, g3);
      verify(connection, times(1)).prepareStatement(anyString());
      verify(connection, times(1)).setAutoCommit(false);
      verify(connection, times(1)).commit();
      verify(statement, times(1)).setString(1, "1");
      verify(statement, times(1)).setString(2, "2");
      verify(statement, times(1)).setString(3, "c1");
      verify(statement, times(1)).setString(1, "3");
      verify(statement, times(1)).setString(2, "4");
      verify(statement, times(1)).setString(3, "c2");
      verify(statement, times(1)).setString(1, "5");
      verify(statement, times(1)).setString(2, "6");
      verify(statement, times(1)).setString(3, "c3");
      verify(statement, times(3)).addBatch();
      verify(statement, times(1)).executeBatch();
    }

    @Test
    @DisplayName("Should throw exception when inserting goal and connection throws exception")
    public void should_ThrowException_when_InsertingGoalAndConnectionThrowsException() throws SQLException {
      // setup mock
      doThrow(SQLException.class).when(dao).getConnection(anyString(), anyString(), anyString());

      // test
      Goal goal = new Goal("1", "2", "c1");
      assertThrows(DatabaseAccessException.class, () -> dao.insertGoals(goal));
    }

    @Test
    @DisplayName("Should throw exception when inserting goal and statement throws exception")
    public void should_ThrowException_when_InsertingGoalAndStatementThrowsException() throws SQLException {
      // setup mock
      Connection connection = mock(Connection.class);
      doReturn(connection).when(dao).getConnection(anyString(), anyString(), anyString());
      doThrow(SQLException.class).when(connection).prepareStatement(anyString());

      // test
      Goal goal = new Goal("1", "2", "c1");
      assertThrows(DatabaseAccessException.class, () -> dao.insertGoals(goal));
    }
  }
}