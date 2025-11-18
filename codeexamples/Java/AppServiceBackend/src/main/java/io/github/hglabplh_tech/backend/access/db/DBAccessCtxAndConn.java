package io.github.hglabplh_tech.backend.access.db;

import io.github.hglabplh_tech.backend.access.AccessCtxAndConnIfc;

import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;
import javax.sql.PooledConnectionBuilder;
import javax.sql.StatementEventListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ShardingKey;

public class DBAccessCtxAndConn implements AccessCtxAndConnIfc {

    private final Connection dbConnection;
    public DBAccessCtxAndConn() throws SQLException {
        this.dbConnection = new PooledConnectionBuilder() {
            @Override
            public PooledConnectionBuilder user(String username) {
                return null;
            }

            @Override
            public PooledConnectionBuilder password(String password) {
                return null;
            }

            @Override
            public PooledConnectionBuilder shardingKey(ShardingKey shardingKey) {
                return null;
            }

            @Override
            public PooledConnectionBuilder superShardingKey(ShardingKey superShardingKey) {
                return null;
            }

            @Override
            public PooledConnection build() throws SQLException {
                return new PooledConnection() {
                    @Override
                    public Connection getConnection() throws SQLException {
                        return null;
                    }

                    @Override
                    public void close() throws SQLException {

                    }

                    @Override
                    public void addConnectionEventListener(ConnectionEventListener listener) {
                    }

                    @Override
                    public void removeConnectionEventListener(ConnectionEventListener listener) {

                    }

                    @Override
                    public void addStatementEventListener(StatementEventListener listener) {

                    }

                    @Override
                    public void removeStatementEventListener(StatementEventListener listener) {

                    }
                };
            }
        }.user("").password("").build().getConnection();
    }
}
