package com.beesinergi.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigManager {
	
private static PropertiesConfiguration config = null;
    
    static {
        try {
            config = new PropertiesConfiguration();
            config.setBasePath("config");
            config.setFileName("config.properties");
            config.load();
        } catch (ConfigurationException e) {
            throw new RuntimeException("Please configure config.properties properly", e);
        }
    }
    
    private ConfigManager(){}
    
    public static Configuration getConfiguration(){
        return config;
    }
}
