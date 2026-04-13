package org.qapower.config;

import java.io.InputStream;
import java.util.Properties;

public class Config {
  
  private static final Properties props = new Properties();
  
  static {
    try (InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
      if (is == null) {
        throw new RuntimeException("config.properties not found in classpath");
      }
      props.load(is);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load config.properties", e);
    }
  }
  
  public static String getBaseUrl() {
    return System.getProperty("baseUrl", props.getProperty("baseUrl"));
  }
  
}
