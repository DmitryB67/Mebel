import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
	public List<Client> getAllClients() {
		List<Client> clients = new ArrayList<Client>();
		String sql = "SELECT * FROM clients ORDER BY id_client";

		try (Connection conn = Database.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Client client = new Client(
					rs.getInt("id_client"),
					rs.getString("fam"),
					rs.getString("name"),
					rs.getString("otchestvo"),
					rs.getString("telefon"),
					rs.getString("adres")
				);
				clients.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}
}
