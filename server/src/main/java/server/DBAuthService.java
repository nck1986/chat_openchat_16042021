package server;

import java.sql.*;

public class DBAuthService implements AuthService {
	private static Connection connection;
	private static PreparedStatement psInsert;

	@Override
	public String getNicknameByLoginAndPassword(String login, String password) {
		String nickName = "";
		try {
			psInsert = connection.prepareStatement("SELECT nickname FROM users WHERE login = ? AND password = ?;");
			psInsert.setString(1, login);
			psInsert.setString(2, password);
			ResultSet resultSet = psInsert.executeQuery();
			if (resultSet.next()) {
				nickName = resultSet.getString(1);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nickName;
	}

	@Override
	public boolean registration(String login, String password, String nickname) {
		try {
			psInsert = connection.prepareStatement("INSERT INTO users(login, password, nickname) VALUES (? ,? ,? );");
			psInsert.setString(1, login);
			psInsert.setString(2, password);
			psInsert.setString(3, nickname);
			psInsert.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}


	}

	@Override
	public boolean changeNick(String oldNickname, String newNickName) {
		return false;
	}

	public static boolean connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:users.db");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void disconnect() {
		try {
			psInsert.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
