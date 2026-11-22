import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ZakazDAO {
	public List<Zakaz> getAllZakazy() {
		List<Zakaz> zakazy = new ArrayList<Zakaz>();
		String sql = "SELECT z.id_zakaza, z.kolvo, z.data_zakaza, z.summa, " +
					 "CONCAT_WS(' ', s.fam, s.name, s.otchestvo) AS sotr_fio, " +
					 "CONCAT_WS(' ', c.fam, c.name, c.otchestvo) AS client_fio, " +
					 "m.naimenovanie " +
					 "FROM zakazy z " +
					 "JOIN sotr s ON z.id_sotr = s.id_sotr " +
					 "JOIN clients c ON z.id_client = c.id_client " +
					 "JOIN mebel m ON z.id_mebel = m.id_mebel " +
					 "ORDER BY z.id_zakaza";

		try (Connection conn = Database.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Zakaz zakaz = new Zakaz(
					rs.getInt("id_zakaza"),
					rs.getString("sotr_fio"),
					rs.getString("client_fio"),
					rs.getString("naimenovanie"),
					rs.getInt("kolvo"),
					rs.getDate("data_zakaza"),
					rs.getBigDecimal("summa")
				);
				zakazy.add(zakaz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zakazy;
	}
}
