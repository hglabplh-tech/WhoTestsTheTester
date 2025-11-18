package io.github.hglabplh_tech.backend.access.config;

import io.github.hglabplh_tech.DOCTypes;
import io.github.hglabplh_tech.backend.access.AccessCtxAndConnIfc;
import io.github.hglabplh_tech.linklayer.ConfigurationIfc;
import io.github.hglabplh_tech.linklayer.AccessorIfc;

/**
 * This class holds the configuration settings for our system backend writes
 * to the database
 * @author Harald Glab-Plhak (C) 2025
 *
 */
public class ConfigAccess implements ConfigurationIfc, AccessorIfc {
    /** HTTP port number*/
    private final int port;

    /**the database connection string*/
    private final String dbConnectString;

    /**the default document type when storing documents*/
    private final DOCTypes defaultDocType;

    public ConfigAccess(int port, String dbConnectString, DOCTypes defaultDocType) {
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


    @Override
    public AccessCtxAndConnIfc initialize() {
        return null;
    }
}
