package com.acme.videoserver.library.sql;

import java.sql.SQLException;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.library.LibraryAccessException;
import com.acme.videoserver.core.library.Videoclip;

public class SQLLibraryTest {

	JdbcDataSource ds;

	@Before
	public void before() throws SQLException {
		ds = new JdbcDataSource();
		ds.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		ds.setUser("");
		ds.setPassword("");

	    Flyway flyway = Flyway.configure().dataSource(ds).load();

	    flyway.migrate();
	}

	@Test
	public void testSaveData() throws LibraryAccessException {

		Library library = new SQLLibrary(ds);
		List<Videoclip> clips = library.clips();

		Assert.assertNotNull(clips);
	}

}
