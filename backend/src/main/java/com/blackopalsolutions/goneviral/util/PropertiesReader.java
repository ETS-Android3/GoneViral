package com.blackopalsolutions.goneviral.util;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handle reading keys and access tokens from properties file.
 */
public final class PropertiesReader {
  /**
   * The shared instance.
   */
  public static final PropertiesReader SHARED = new PropertiesReader();

  private static final Logger log = LoggerFactory.getLogger(PropertiesReader.class);
  private final Properties properties;

  private PropertiesReader() {
    properties = new Properties();
    final URL props = ClassLoader.getSystemResource("apikey.properties");
    try {
      properties.load(props.openStream());
    } catch (IOException e) {
      log.error("Couldn't load properties!", e);
    }
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
