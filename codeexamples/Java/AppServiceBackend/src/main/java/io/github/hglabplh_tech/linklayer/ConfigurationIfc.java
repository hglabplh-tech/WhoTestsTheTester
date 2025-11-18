package io.github.hglabplh_tech.linklayer;

import io.github.hglabplh_tech.DOCTypes;
/**
 * This interface holds the public functions for the configuration access
  * @author Harald Glab-Plhak (C) 2025
 *
 */
public interface ConfigurationIfc {

    /**
     * The HTTP / HTTPS port
     * @return the port number as integer
     */
    int getPort();

    /**
     * The database connect string
     * @return the database connect parameters as string
     */
    String getDBConnectString();

    /**
     * The default type for import / export / create
     * @return the default type enum
     */
    DOCTypes getDefaultType();
}
