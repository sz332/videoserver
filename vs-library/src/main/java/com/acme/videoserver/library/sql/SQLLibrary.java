package com.acme.videoserver.library.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.acme.videoserver.core.library.Library;
import com.acme.videoserver.core.library.LibraryAccessException;
import com.acme.videoserver.core.library.Videoclip;
import com.acme.videoserver.library.common.DataSource;

public class SQLLibrary implements Library {

	private final DataSource dataSource;

	public SQLLibrary(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void add(Videoclip clip) throws LibraryAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Videoclip> clips() throws LibraryAccessException {

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM videoclip");
				ResultSet rs = ps.executeQuery()) {

			List<Videoclip> clips = new ArrayList<>();

			while (rs.next()) {
				clips.add(null);
			}

			return clips;
		} catch (SQLException e) {
			throw new LibraryAccessException(e);
		}

	}

	@Override
	public Videoclip clip(String clipId) throws LibraryAccessException {
		return null;
	}

}
