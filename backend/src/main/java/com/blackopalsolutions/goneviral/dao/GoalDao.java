package com.blackopalsolutions.goneviral.dao;

import com.blackopalsolutions.goneviral.model.Goal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoalDao extends Dao {
  private static final Logger log = LoggerFactory.getLogger(GoalDao.class);

  /**
   * Inserts goals into the database.
   * @param goals the goals to insert.
   * @throws DatabaseAccessException if there was an error accessing the database.
   */
  public void insertGoals(Goal... goals) throws DatabaseAccessException {
    if (goals.length == 0) {
      return;
    }

    try (Connection con = getConnection(getDatabaseUrl(), getUser(), getPassword())) {
      String query = "INSERT INTO goals(goalId, roleId, condition) VALUES(?, ?, ?)";
      try (PreparedStatement st = con.prepareStatement(query)) {
        // begin transaction
        con.setAutoCommit(false);

        // create row and add to batch
        for (Goal goal : goals) {
          st.setString(1, goal.getGoalId());
          st.setString(2, goal.getRoleId());
          st.setString(3, goal.getCondition());
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
          log.warn("Couldn't rollback connection.", ex);
        }

        log.error("Couldn't create statement!", e);
        throw new DatabaseAccessException();
      }
    } catch (SQLException e) {
      log.error("Couldn't establish connection to the database!", e);
      throw new DatabaseAccessException();
    }
  }

  /**
   * Removes a goal from the database.
   * @param goal the goal to remove.
   * @throws DatabaseAccessException if there was an error accessing the database.
   */
  public void removeGoal(Goal goal) throws DatabaseAccessException {
    if (goal == null) {
      log.warn("Goal to remove is null");
      return;
    }

    try (Connection con = getConnection(getDatabaseUrl(), getUser(), getPassword())) {
      String query = "DELETE FROM goals WHERE goalId = ?";
      try (PreparedStatement st = con.prepareStatement(query)) {
        con.setAutoCommit(false);
        st.setString(1, goal.getGoalId());
        st.executeQuery();
        con.commit();
      } catch (SQLException e) {
        // undo transaction
        try {
          con.rollback();
        } catch (SQLException ex) {
          log.warn("Couldn't rollback connection.", ex);
        }

        log.error("Couldn't create statement!", e);
        throw new DatabaseAccessException();
      }
    } catch (SQLException e) {
      log.error("Couldn't establish a connection to the database!", e);
      throw new DatabaseAccessException();
    }
  }
}
