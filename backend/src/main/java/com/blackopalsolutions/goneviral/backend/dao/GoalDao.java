package com.blackopalsolutions.goneviral.backend.dao;

import com.blackopalsolutions.goneviral.backend.model.domain.Goal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoalDao extends Dao {
  /**
   * Retrieve the goal based on the id.
   * @param id the id of the goal to retrieve.
   * @return null if goal doesn't exist, otherwise the goal with the given id.
   * @throws DatabaseAccessException if there was an error accessing the database.
   */
  public Goal getGoal(String id) throws DatabaseAccessException {
    Goal goal = null;

    try (Connection con = getConnection()) {
      String sql = "SELECT * FROM goals WHERE id = ?";
      try (PreparedStatement st = con.prepareStatement(sql)) {
        // begin transaction
        con.setAutoCommit(false);

        // find goal
        st.setString(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
          int goalId = rs.getInt("id");
          String condition = rs.getString("condition");
          goal = new Goal(goalId, condition);
        }

        // end transaction
        con.commit();
      } catch (SQLException e) {
        try {
          con.rollback();
        } catch (SQLException ignored) {}

        throw new DatabaseAccessException("Couldn't retrieve goal.");
      }
    } catch (SQLException e) {
      throw new DatabaseAccessException("Couldn't access database.");
    }

    return goal;
  }
}
