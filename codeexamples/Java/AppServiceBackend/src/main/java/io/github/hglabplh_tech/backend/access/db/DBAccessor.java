package io.github.hglabplh_tech.backend.access.db;

import io.github.hglabplh_tech.backend.access.AccessCtxAndConnIfc;
import io.github.hglabplh_tech.linklayer.AccessorIfc;

public class DBAccessor implements AccessorIfc {

    @Override
    public AccessCtxAndConnIfc initialize() {
        return new DBConnection();
    }
}
