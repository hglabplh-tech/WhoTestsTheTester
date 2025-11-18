package io.github.hglabplh_tech.backend.access;

import io.github.hglabplh_tech.dastabase.DOCTypes;
import io.github.hglabplh_tech.linklayer.AccessorIfc;
import io.github.hglabplh_tech.linklayer.ConfigurationIfc;

/**
 * This class is between the real configuration definition and the access to the config resource
 */
public class Configuration implements ConfigurationIfc, AccessorIfc {
    /** HTTP port number*/
    private final int port;

    /**the database connection string*/
    private final String dbConnectString;

    /**the default document type when storing documents*/
    private final DOCTypes defaultDocType;

    public Configuration(int port, String dbConnectString, DOCTypes defaultDocType) {
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
