package com.ltc.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection对象的相关操作
 */
public class JdbcUtil {
	// 获取Connection
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(Constants.DB_DRIVER);
			conn = DriverManager.getConnection(Constants.DB_URL,
					Constants.DB_USER, Constants.DB_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭Connection链接
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 事物回滚
	public static void rollbackConnection(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
