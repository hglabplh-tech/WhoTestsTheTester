package io.github.hglabplh_tech.backend.access;

import io.github.hglabplh_tech.linklayer.AccessorIfc;

public class Accessor implements AccessorIfc {

    @Override
    public AccessCtxAndConnIfc initialize() {
        return new Access();
    }

    public static class Access implements AccessCtxAndConnIfc {
        public Access() {
        }
    }
}
