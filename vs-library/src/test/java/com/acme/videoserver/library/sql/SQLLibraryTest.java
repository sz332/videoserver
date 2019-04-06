package com.acme.videoserver.library.sql;

import java.sql.SQLException;
import java.util.List;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.library.LibraryAccessException;
import com.acme.videoserver.core.library.Videoclip;
import com.jcabi.jdbc.JdbcSession;

public class SQLLibraryTest {

	JdbcDataSource ds;

	@Before
	public void before() throws SQLException {
		ds = new JdbcDataSource();
		ds.setURL("jdbc:h2:˜/test");
		ds.setUser("sa");
		ds.setPassword("sa");

		new JdbcSession(ds)
		.autocommit(false)
		.sql("START TRANSACTION")
		.execute()
		.sql("CREATE TABLE videoclip ("
				+ "id VARCHAR(36) NOT NULL PRIMARY KEY, "
				+ "title VARCHAR(255) NOT NULL, "
				+ "description VARCHAR(2000), "
				+ "thumbnail VARCHAR(MAX), "
				+ "recordingDateTime TIMESTAMP)")
		.execute()
		.sql("CREATE TABLE participant ("
				+ "	videoclip_id VARCHAR(36) NOT NULL, "
				+ "	name VARCHAR(255) NOT NULL, "
				+ " FOREIGN KEY (videoclip_id) REFERENCES videoclip(id) ON DELETE CASCADE, "
				+ " PRIMARY KEY (videoclip_id, name))")
		.execute()
		.sql("CREATE TABLE participant ("
				+ "	videoclip_id VARCHAR(36) NOT NULL, "
				+ "	name VARCHAR(255) NOT NULL, "
				+ " FOREIGN KEY (videoclip_id) REFERENCES videoclip(id) ON DELETE CASCADE, "
				+ " PRIMARY KEY (videoclip_id, name))")
		.execute()
		.commit();
	}

	@Test
	public void testSaveData() throws LibraryAccessException {

		Library library = new SQLLibrary(ds);
		List<Videoclip> clips = library.clips();

		Assert.assertNotNull(clips);
	}

}
