package io.github.hglabplh_tech.backend.access.config;

import io.github.hglabplh_tech.DOCTypes;
import io.github.hglabplh_tech.backend.access.ConfigurationIfc;

public class ConfigAccess implements ConfigurationIfc {

    @Override
    public int getPort() {
        return 8080;
    }

    @Override
    public String getDBConnectString() {
        return "connect";
    }

    @Override
    public DOCTypes getDefaultType() {
        return DOCTypes.ADRESS;
    }
}
