import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MebelDAO {
	public List<Mebel> getAllMebel() {
		List<Mebel> mebelList = new ArrayList<Mebel>();
		String sql = "SELECT * FROM mebel ORDER BY id_mebel";

		try (Connection conn = Database.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Mebel mebel = new Mebel(
					rs.getInt("id_mebel"),
					rs.getString("naimenovanie"),
					rs.getString("opisanie"),
					rs.getString("material"),
					rs.getInt("kolvo_sklad"),
					rs.getBigDecimal("cena")
				);
				mebelList.add(mebel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mebelList;
	}
}
