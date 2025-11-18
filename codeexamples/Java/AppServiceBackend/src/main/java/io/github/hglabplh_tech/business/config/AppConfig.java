package io.github.hglabplh_tech.business.config;

import io.github.hglabplh_tech.DOCTypes;
import io.github.hglabplh_tech.linklayer.ConfigurationIfc;

/**
 * This class holds the configuration settings for our system in business view
 * @author Harald Glab-Plhak (C) 2025
 *
 */
public class AppConfig implements ConfigurationIfc {

    /** HTTP port number*/
    private final int port;

    /**the database connection string*/
    private final String dbConnectString;

    /**the default document type when storing documents*/
    private final DOCTypes defaultDocType;

    public AppConfig(int port, String dbConnectString, DOCTypes defaultDocType) {
        this.port = port;
        this.dbConnectString = dbConnectString;
        this.defaultDocType = defaultDocType;
    }



    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public String getDBConnectString() {
        return this.dbConnectString;
    }

    @Override
    public DOCTypes getDefaultType() {
        return this.defaultDocType;
    }
}
