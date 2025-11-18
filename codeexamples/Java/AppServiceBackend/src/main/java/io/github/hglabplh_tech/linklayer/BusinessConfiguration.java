package io.github.hglabplh_tech.linklayer;

import io.github.hglabplh_tech.dastabase.DOCTypes;
import io.github.hglabplh_tech.backend.access.AccessCtxAndConnIfc;

public class BusinessConfiguration implements AccessorIfc, ConfigurationIfc {
    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public String getDBConnectString() {
        return "";
    }

    @Override
    public DOCTypes getDefaultType() {
        return null;
    }

    @Override
    public AccessCtxAndConnIfc initialize() {
        return null;
    }
}
