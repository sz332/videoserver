package com.acme.videoserver.library.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.List;

import javax.sql.DataSource;

import org.cactoos.scalar.SolidScalar;
import org.cactoos.scalar.UncheckedScalar;

import com.acme.videoserver.core.image.Base64EncodedImage;
import com.acme.videoserver.core.library.Image;
import com.acme.videoserver.core.library.Videoclip;
import com.acme.videoserver.library.common.ListStringOutcome;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.Outcome;

// see https://www.yegor256.com/2014/12/01/orm-offensive-anti-pattern.html
public class SQLVideoclip implements Videoclip {

	private final String uuid;
	private final UncheckedScalar<Videoclip> output;

	public SQLVideoclip(DataSource dataSource, String uuid) {
		this.uuid = uuid;

		this.output = new UncheckedScalar<>(new SolidScalar<>(() -> {

			List<String> participants = new JdbcSession(dataSource).sql("SELECT name FROM participant WHERE videoclip_id = ?")
					.set(uuid)
					.select(new ListStringOutcome());

			List<String> tags = new JdbcSession(dataSource).sql("SELECT name FROM tag WHERE videoclip_id = ?")
					.set(uuid)
					.select(new ListStringOutcome());

			return new JdbcSession(dataSource).sql("SELECT title, description, thumbnail, recordingDateTime FROM videoclip WHERE id = ?")
					.set(uuid)
					.select(new Outcome<Videoclip>() {

						@Override
						public Videoclip handle(ResultSet rset, Statement stmt) throws SQLException {
							Videoclip result = null;
							if (rset.next()) {
								result = this.fetch(rset);
							}
							return result;
						}

						private Videoclip fetch(ResultSet rset) throws SQLException {

							String title = rset.getString(1);
							String description = rset.getString(2);
							Image thumbnail = new Base64EncodedImage(rset.getString(3));
							Instant time = rset.getTimestamp(4).toInstant();

							return new ConstantVideoclip(uuid, title, description, thumbnail, time, participants, tags);
						}

					});

		}));
	}

	@Override
	public String uuid() {
		return uuid;
	}

	@Override
	public String title() {
		return this.output.value().title();
	}

	@Override
	public String description() {
		return this.output.value().description();
	}

	@Override
	public Image thumbnail() {
		return this.output.value().thumbnail();
	}

	@Override
	public Instant recordingDateTime() {
		return this.output.value().recordingDateTime();
	}

	@Override
	public List<String> participants() {
		return this.output.value().participants();
	}

	@Override
	public List<String> tags() {
		return this.output.value().tags();
	}

}
