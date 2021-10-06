package com.blackopalsolutions.goneviral.dao;

import com.blackopalsolutions.goneviral.util.PropertiesReader;
import com.blackopalsolutions.goneviral.util.PropertyKeys;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class Dao {
  String getDatabaseUrl() {
    return "jdbc:postgresql://"
        + PropertiesReader.SHARED.getProperty(PropertyKeys.DATABASE_URL)
        + ":" + PropertiesReader.SHARED.getProperty(PropertyKeys.DB_PORT)
        + "/" + PropertiesReader.SHARED.getProperty(PropertyKeys.DATABASE);
  }

  String getUser() {
    return PropertiesReader.SHARED.getProperty(PropertyKeys.DB_USER);
  }

  String getPassword() {
    return PropertiesReader.SHARED.getProperty(PropertyKeys.DB_ACCESS_KEY);
  }

  Connection getConnection() throws SQLException {
    return getConnection(getDatabaseUrl(), getUser(), getPassword());
  }

  Connection getConnection(String url, String user, String pass) throws SQLException {
    return DriverManager.getConnection(url, user, pass);
  }
}
