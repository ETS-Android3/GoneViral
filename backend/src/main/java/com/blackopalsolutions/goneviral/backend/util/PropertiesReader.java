package com.blackopalsolutions.goneviral.backend.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Handle reading keys and access tokens from properties file.
 */
public final class PropertiesReader {
  /**
   * The shared instance.
   */
  public static final PropertiesReader SHARED = new PropertiesReader();

  private final Properties properties;

  private PropertiesReader() {
    properties = new Properties();
    try {
      properties.load(getClass().getResourceAsStream("/apikey.properties"));
    } catch (IOException ignored) {}
  }

  /**
   * Retrieve a property from the properties file with the given name.
   *
   * @param name the name of the property to retrieve.
   * @return the retrieved property.
   */
  public String getProperty(final String name) {
    return properties.getProperty(name);
  }
}
