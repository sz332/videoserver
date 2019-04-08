package com.acme.videoserver.library.sql;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Collection;
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
		ds.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;TRACE_LEVEL_SYSTEM_OUT=2");
		ds.setUser("");
		ds.setPassword("");

		Flyway flyway = Flyway.configure()
				.dataSource(ds)
				.load();

		flyway.migrate();
	}

	@Test
	public void testListData() throws LibraryAccessException {

		Library library = new SQLLibrary(ds);
		Collection<Videoclip> clips = library.clips();

		Assert.assertNotNull(clips);
		Assert.assertThat(clips, hasSize(3));

		Videoclip clip = library.clip("e817cbe5-e2be-44fb-9858-a6cab54ee03e");

		Assert.assertEquals("e817cbe5-e2be-44fb-9858-a6cab54ee03e", clip.uuid());
		Assert.assertEquals("First video", clip.title());
		Assert.assertEquals("Descr1", clip.description());
		Assert.assertEquals("image/jpeg", clip.thumbnail().mimeType());
		Assert.assertThat(clip.thumbnail().data().length, is(1026));
		Assert.assertEquals(Instant.parse("2019-01-15T18:01:00.Z"), clip.recordingDateTime());

		List<String> participants = clip.participants();
		Assert.assertThat(participants, containsInAnyOrder("p1_1", "p1_2", "p1_3"));

		List<String> tags = clip.tags();
		Assert.assertThat(tags, containsInAnyOrder("t1_1", "t1_2", "t1_3"));

		clip = library.clip("e817cbe5-e2be-44fb-9858-a6cab54ee03e");
		Assert.assertNotNull(clip);
	}

}
