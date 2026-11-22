import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SotrDAO {
	public List<Sotr> getAllSotr() {
		List<Sotr> sotrList = new ArrayList<Sotr>();
		String sql = "SELECT * FROM sotr ORDER BY id_sotr";

		try (Connection conn = Database.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Sotr sotr = new Sotr(
					rs.getInt("id_sotr"),
					rs.getString("fam"),
					rs.getString("name"),
					rs.getString("otchestvo"),
					rs.getString("doljnost"),
					rs.getString("telefon")
				);
				sotrList.add(sotr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sotrList;
	}
}
