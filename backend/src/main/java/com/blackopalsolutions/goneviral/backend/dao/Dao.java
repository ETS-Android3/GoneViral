package com.blackopalsolutions.goneviral.backend.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.blackopalsolutions.goneviral.backend.util.PropertiesReader;
import com.blackopalsolutions.goneviral.backend.util.PropertyKeys;
import com.amazonaws.services.dynamodbv2.document.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class Dao {
  private static final String region = "us-east-2";

  private Table table;

  String getDatabaseUrl() {
    return "jdbc:postgresql://"
        + PropertiesReader.SHARED.getProperty(PropertyKeys.DATABASE_URL)
        + ":" + PropertiesReader.SHARED.getProperty(PropertyKeys.DB_PORT)
        + "/" + PropertiesReader.SHARED.getProperty(PropertyKeys.DATABASE);
  }

  Table getTable(String tableName) {
    if (table == null) {
      AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(region).build();
      DynamoDB dynamoDB = new DynamoDB(client);
      table = dynamoDB.getTable(tableName);
    }
    return table;
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
