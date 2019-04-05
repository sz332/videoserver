package com.acme.videoserver.library.common;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSource {

	Connection getConnection() throws SQLException;
	
}
