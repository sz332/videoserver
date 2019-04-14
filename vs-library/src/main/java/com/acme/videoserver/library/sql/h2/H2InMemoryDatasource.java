package com.acme.videoserver.library.sql.h2;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.cactoos.scalar.SolidScalar;
import org.cactoos.scalar.UncheckedScalar;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;

public class H2InMemoryDatasource implements DataSource {

	private final UncheckedScalar<JdbcDataSource> output;

	public H2InMemoryDatasource() {

		this.output = new UncheckedScalar<>(new SolidScalar<>(() -> {
			JdbcDataSource ds = new JdbcDataSource();
			ds.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;TRACE_LEVEL_SYSTEM_OUT=2");
			ds.setUser("");
			ds.setPassword("");

			Flyway flyway = Flyway.configure().dataSource(ds).load();

			flyway.migrate();

			return ds;
		}));

	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return this.output.value().getLogWriter();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		this.output.value().setLogWriter(out);
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		this.output.value().setLoginTimeout(seconds);
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return this.output.value().getLoginTimeout();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return this.output.value().getParentLogger();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return this.output.value().unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.output.value().isWrapperFor(iface);
	}

	@Override
	public Connection getConnection() throws SQLException {
		return this.output.value().getConnection();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return this.output.value().getConnection(username, password);
	}

}
