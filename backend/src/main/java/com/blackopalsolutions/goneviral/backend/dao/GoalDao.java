package com.blackopalsolutions.goneviral.backend.dao;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.blackopalsolutions.goneviral.model.domain.Goal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoalDao extends Dao {
  private static final String tableName = "Goals";
  private static final String partitionKey = "name";
  private static final String conditionAttr = "condition";

  /**
    * Retrieve a goal from the database.
    * @param name the name of the goal to retrieve.
    * @return the retrieved goal.
    * @throws DatabaseAccessException if there was an error accessing the database.
    * @throws ServerException if there was an error processing the request.
    */
  public Goal getGoal(String name) throws DatabaseAccessException, ServerException {
    GetItemSpec spec = new GetItemSpec().withPrimaryKey(partitionKey, name);
    Item outcome;
    try {
      outcome = getTable(tableName).getItem(spec);
      if (outcome == null) {
        throw new ServerException("Goal with given name does not exist.");
      }
    } catch (ServerException e) {
      throw e;
    } catch (Exception e) {
      System.err.println(e.getMessage());
      throw new DatabaseAccessException("Could not retrieve goal from database.");
    }

    String condition = outcome.getString(conditionAttr);

    // TODO: remove ID
    return new Goal(0, condition);
  }

  /**
   * Retrieve the goal based on the id.
   * @param id the id of the goal to retrieve.
   * @return null if goal doesn't exist, otherwise the goal with the given id.
   * @throws DatabaseAccessException if there was an error accessing the database.
   * @deprecated
   */
  public Goal getGoal(int id) throws DatabaseAccessException {
    Goal goal = null;

    try (Connection con = getConnection()) {
      String sql = "SELECT * FROM goals WHERE id = ?";
      try (PreparedStatement st = con.prepareStatement(sql)) {
        // begin transaction
        con.setAutoCommit(false);

        // find goal
        st.setInt(1, id);
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
