package com.blackopalsolutions.goneviral;

import com.blackopalsolutions.goneviral.util.PropertiesReader;
import com.blackopalsolutions.goneviral.util.PropertyKeys;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    /**
     * Main access point.
     * @param args the environment variables.
     */
    public static void main(String[] args) {
        String url = "jdbc:postgresql://"
                + PropertiesReader.SHARED.getProperty(PropertyKeys.DATABASE_URL)
                + ":" + PropertiesReader.SHARED.getProperty(PropertyKeys.DB_PORT)
                + "/" + PropertiesReader.SHARED.getProperty(PropertyKeys.DATABASE);
        String user = PropertiesReader.SHARED.getProperty(PropertyKeys.DB_USER);
        String pass = PropertiesReader.SHARED.getProperty(PropertyKeys.DB_ACCESS_KEY);

        try (Connection con = DriverManager.getConnection(url, user, pass);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT VERSION()")) {
            if (rs.next()) {
                log.debug(rs.getString(1));
            }
        } catch (SQLException e) {
            log.error("Couldn't execute query!", e);
        }
    }
}
