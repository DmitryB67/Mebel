import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static final String URL = "jdbc:mysql://localhost:3306/mebel?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			throw new SQLException("Драйвер MySQL не найден. Подключите mysql-connector-j к проекту.", e);
		}
	}

	public static boolean testConnection() {
		try (Connection conn = getConnection()) {
			if (conn != null) {
				System.out.println("Подключение к БД успешно!");
			}
			return true;
		} catch (SQLException e) {
			System.out.println("Ошибка подключения: " + e.getMessage());
			return false;
		}
	}
}
