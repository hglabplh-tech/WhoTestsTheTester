package io.github.hglabplh_tech.backend.access;

import io.github.hglabplh_tech.DOCTypes;

public interface ConfigurationIfc {

    int getPort();

    String getDBConnectString();

    DOCTypes getDefaultType();


}
