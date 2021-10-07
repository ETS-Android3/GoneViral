package com.blackopalsolutions.goneviral.backend.dao;

import com.blackopalsolutions.goneviral.backend.model.domain.Goal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    if (goals == null || goals.length == 0) {
      log.warn("Attempted to insert 0 goals");
      return;
    }

    try (Connection con = getConnection()) {
      String sql = "INSERT INTO goals(goalId, roleId, condition) VALUES(?, ?, ?)";
      try (PreparedStatement st = con.prepareStatement(sql)) {
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
   * Retrieve the goal based on the id.
   * @param id the id of the goal to retrieve.
   * @return null if goal doesn't exist, otherwise the goal with the given id.
   * @throws DatabaseAccessException if there was an error accessing the database.
   */
  public Goal getGoal(String id) throws DatabaseAccessException {
    Goal goal = null;

    try (Connection con = getConnection()) {
      String sql = "SELECT * FROM goals WHERE goalId = ?";
      try (PreparedStatement st = con.prepareStatement(sql)) {
        // begin transaction
        con.setAutoCommit(false);

        // find goal
        st.setString(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
          String goalId = rs.getString(1);
          String roleId = rs.getString(2);
          String condition = rs.getString(3);
          goal = new Goal(goalId, roleId, condition);
        }

        // end transaction
        con.commit();
      } catch (SQLException e) {
        try {
          con.rollback();
        } catch (SQLException ex) {
          log.warn("Couldn't undo transaction.", ex);
        }

        log.error("Couldn't prepare statement!", e);
        throw new DatabaseAccessException();
      }
    } catch (SQLException e) {
      log.error("Couldn't access the database!", e);
      throw new DatabaseAccessException();
    }

    return goal;
  }

  /**
   * Updates a goal in the database.
   * @param goal the updated goal.
   * @throws DatabaseAccessException if there was an error accessing the database.
   */
  public void updateGoal(Goal goal) throws DatabaseAccessException {
    if (goal == null) {
      log.warn("Attempted to update a null goal.");
      return;
    }

    try (Connection con = getConnection()) {
      String sql = "UPDATE goals SET roleId = ?, condition = ? WHERE goalId = ?";
      try (PreparedStatement st = con.prepareStatement(sql)) {
        // begin transaction
        con.setAutoCommit(false);

        // update row
        st.setString(1, goal.getRoleId());
        st.setString(2, goal.getCondition());
        st.setString(3, goal.getGoalId());

        // execute and end transaction
        st.executeQuery();
        con.commit();
      } catch (SQLException e) {
        // undo transaction
        try {
          con.rollback();
        } catch (SQLException ex) {
          log.warn("Couldn't undo transaction.", ex);
        }

        log.error("Couldn't prepare statement!", e);
        throw new DatabaseAccessException();
      }
    } catch (SQLException e) {
      log.error("Couldn't access the database!", e);
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

    try (Connection con = getConnection()) {
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
