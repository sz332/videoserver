package com.acme.videoserver.library.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import com.acme.videoserver.core.image.Base64Encoded;
import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.library.LibraryAccessException;
import com.acme.videoserver.core.library.Videoclip;
import com.acme.videoserver.library.common.ListStringOutcome;
import com.jcabi.jdbc.JdbcSession;

// FIXME rename class to H2SQLLibrary because of the H2 implementation
public class SQLLibrary implements Library {

	private final DataSource dataSource;

	public SQLLibrary(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void add(Videoclip clip) throws LibraryAccessException {

		try (Connection connection = dataSource.getConnection()) {

			connection.setAutoCommit(false);

			try (PreparedStatement ps = connection.prepareStatement(
					"MERGE INTO videoclip(id, title, description, thumbnail, recordingDateTime) VALUES (?, ?, ?, ?, ?)")) {

				ps.setString(1, clip.uuid());
				ps.setString(2, clip.title());
				ps.setString(3, clip.description());
				ps.setString(4, new Base64Encoded(clip.thumbnail()).asString());
				ps.setTimestamp(5, Timestamp.from(clip.recordingDateTime()));

				ps.executeUpdate();

				try (PreparedStatement psp = connection.prepareStatement("DELETE FROM participant WHERE videoclip_id = ?")) {
					psp.setString(1, clip.uuid());
					psp.executeUpdate();
				}
				
				try (PreparedStatement psp = connection.prepareStatement("INSERT INTO participant(videoclip_id, name) VALUES (?, ?)")) {

					for (String participant : clip.participants()) {
						psp.setString(1, clip.uuid());
						psp.setString(2, participant);
						psp.addBatch();
					}

					psp.executeBatch();
				}

				try (PreparedStatement psp = connection.prepareStatement("DELETE FROM tag WHERE videoclip_id = ?")) {
					psp.setString(1, clip.uuid());
					psp.executeUpdate();
				}
				
				try (PreparedStatement psp = connection.prepareStatement("INSERT INTO tag(videoclip_id, name) VALUES (?, ?)")) {

					for (String tag : clip.tags()) {
						psp.setString(1, clip.uuid());
						psp.setString(2, tag);
						psp.addBatch();
					}

					psp.executeBatch();
				}

			}

			connection.commit();

		} catch (SQLException e) {
			throw new LibraryAccessException(e);
		}
	}

	@Override
	public List<Videoclip> clips() throws LibraryAccessException {

		try {

			return new JdbcSession(dataSource).sql("SELECT id FROM videoclip")
					.select(new ListStringOutcome())
					.stream()
					.map(s -> new SQLVideoclip(dataSource, s))
					.collect(Collectors.toList());

		} catch (SQLException e) {
			throw new LibraryAccessException(e);
		}

	}

	@Override
	public Videoclip clip(String clipId) throws LibraryAccessException {
		return new SQLVideoclip(dataSource, clipId);
	}

}
