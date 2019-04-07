package com.acme.videoserver.library.sql;

import static org.hamcrest.Matchers.hasSize;

import java.sql.SQLException;
import java.util.Collection;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.library.LibraryAccessException;
import com.acme.videoserver.core.library.Videoclip;
import com.acme.videoserver.library.common.CachedLibrary;

public class CachedLibraryTest {
	
	JdbcDataSource ds;

	@Before
	public void before() throws SQLException {
		ds = new JdbcDataSource();
		ds.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;TRACE_LEVEL_SYSTEM_OUT=3");
		ds.setUser("");
		ds.setPassword("");

	    Flyway flyway = Flyway.configure().dataSource(ds).load();

	    flyway.migrate();
	}
	
	@Test
	public void testCaching() throws LibraryAccessException {
		Library library = new CachedLibrary(new SQLLibrary(ds));

		Collection<Videoclip> clips = library.clips();
		
		Assert.assertNotNull(clips);
		Assert.assertThat(clips, hasSize(3));

		Collection<Videoclip> cachedClips = library.clips();
		
		Assert.assertNotNull(cachedClips);
		Assert.assertThat(cachedClips, hasSize(3));
		
		Videoclip clip = library.clip("e817cbe5-e2be-44fb-9858-a6cab54ee03e");
		Assert.assertEquals("First video", clip.title());
		
		clip = library.clip("e817cbe5-e2be-44fb-9858-a6cab54ee03e");
		Assert.assertEquals("First video", clip.title());
	}
	

}
