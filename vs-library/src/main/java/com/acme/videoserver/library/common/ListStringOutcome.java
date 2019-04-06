package com.acme.videoserver.library.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jcabi.jdbc.Outcome;

public class ListStringOutcome implements Outcome<List<String>> {

	@Override
	public List<String> handle(ResultSet rset, Statement stmt) throws SQLException {
		final List<String> names = new ArrayList<>();
		while (rset.next()) {
			names.add(rset.getString(1));
		}
		return names;
	}

}
