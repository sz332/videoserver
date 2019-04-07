package com.acme.videoserver.library.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import com.acme.videoserver.core.image.Base64EncodedImage;
import com.acme.videoserver.core.library.Image;
import com.acme.videoserver.core.library.Videoclip;
import com.acme.videoserver.library.common.ListStringOutcome;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.Outcome;
import com.jcabi.jdbc.SingleOutcome;

// see https://www.yegor256.com/2014/12/01/orm-offensive-anti-pattern.html
public class SQLVideoclip implements Videoclip {

	private final DataSource dataSource;
	private final String uuid;

	public SQLVideoclip(DataSource dataSource, String uuid) {
		this.dataSource = dataSource;
		this.uuid = uuid;
	}

	@Override
	public String uuid() {
		return uuid;
	}

	@Override
	public String title() {
		try {
			return new JdbcSession(dataSource).sql("SELECT title FROM videoclip WHERE id = ?")
					.set(uuid)
					.select(new SingleOutcome<String>(String.class));
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public String description() {
		try {
			return new JdbcSession(dataSource).sql("SELECT description FROM videoclip WHERE id = ?")
					.set(uuid)
					.select(new SingleOutcome<String>(String.class));
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Image thumbnail() {

		try {
			String text = new JdbcSession(dataSource).sql("SELECT thumbnail FROM videoclip WHERE id = ?")
					.set(uuid)
					.select(new SingleOutcome<String>(String.class));

			return new Base64EncodedImage(text);
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Instant recordingDateTime() {
		try {
			Timestamp timestamp = new JdbcSession(dataSource)
					.sql("SELECT recordingDateTime FROM videoclip WHERE id = ?")
					.set(uuid)
					.select(new Outcome<Timestamp>() {
						@Override
						public Timestamp handle(ResultSet rset, Statement stmt) throws SQLException {
							if (rset.next()) {
								return rset.getTimestamp(1);
							}
							return null;
						}
					});

			return timestamp.toInstant();
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<String> participants() {
		try {
			return new JdbcSession(dataSource).sql("SELECT name FROM participant WHERE videoclip_id = ?")
					.set(uuid)
					.select(new ListStringOutcome());
		} catch (SQLException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<String> tags() {
		try {
			return new JdbcSession(dataSource).sql("SELECT name FROM tag WHERE videoclip_id = ?")
					.set(uuid)
					.select(new ListStringOutcome());
		} catch (SQLException e) {
			return Collections.emptyList();
		}
	}

}
