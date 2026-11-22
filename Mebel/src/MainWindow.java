import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainWindow extends JFrame {
	private JTabbedPane tabbedPane;
	private JTable tableSotr, tableClients, tableMebel, tableZakazy;

	public MainWindow() {
		super("Информационная система «Магазин мебели»");

		setSize(1100, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		tabbedPane = new JTabbedPane();

		tableSotr = new JTable();
		tableClients = new JTable();
		tableMebel = new JTable();
		tableZakazy = new JTable();

		tabbedPane.addTab("Сотрудники", createTablePanel(tableSotr, "Сотрудники"));
		tabbedPane.addTab("Клиенты", createTablePanel(tableClients, "Клиенты"));
		tabbedPane.addTab("Мебель", createTablePanel(tableMebel, "Мебель"));
		tabbedPane.addTab("Заказы", createTablePanel(tableZakazy, "Заказы"));

		JPanel buttonPanel = new JPanel();
		JButton refreshButton = new JButton("Обновить данные");
		JButton exitButton = new JButton("Выход");

		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadAllData();
			}
		});

		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		buttonPanel.add(refreshButton);
		buttonPanel.add(exitButton);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(tabbedPane, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(mainPanel);

		loadAllData();
	}

	private JPanel createTablePanel(JTable table, String title) {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		panel.add(titleLabel, BorderLayout.NORTH);
		panel.add(new JScrollPane(table), BorderLayout.CENTER);

		return panel;
	}

	private void loadAllData() {
		if (!Database.testConnection()) {
			JOptionPane.showMessageDialog(this, "Не удалось подключиться к базе данных.\n"
					+ "Проверьте, что MySQL запущен.", "Ошибка", JOptionPane.ERROR_MESSAGE);
			return;
		}

		loadSotr();
		loadClients();
		loadMebel();
		loadZakazy();
	}

	private void loadSotr() {
		SotrDAO sotrDAO = new SotrDAO();
		List<Sotr> sotrList = sotrDAO.getAllSotr();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID сотрудника");
		model.addColumn("Фамилия");
		model.addColumn("Имя");
		model.addColumn("Отчество");
		model.addColumn("Должность");
		model.addColumn("Телефон");

		for (Sotr sotr : sotrList) {
			model.addRow(new Object[] {
				sotr.getIdSotr(),
				sotr.getFam(),
				sotr.getName(),
				sotr.getOtchestvo(),
				sotr.getDoljnost(),
				sotr.getTelefon()
			});
		}

		tableSotr.setModel(model);
	}

	private void loadClients() {
		ClientDAO clientDAO = new ClientDAO();
		List<Client> clients = clientDAO.getAllClients();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID клиента");
		model.addColumn("Фамилия");
		model.addColumn("Имя");
		model.addColumn("Отчество");
		model.addColumn("Телефон");
		model.addColumn("Адрес");

		for (Client client : clients) {
			model.addRow(new Object[] {
				client.getIdClient(),
				client.getFam(),
				client.getName(),
				client.getOtchestvo(),
				client.getTelefon(),
				client.getAdres()
			});
		}

		tableClients.setModel(model);
		tableClients.getColumnModel().getColumn(5).setPreferredWidth(300);
	}

	private void loadMebel() {
		MebelDAO mebelDAO = new MebelDAO();
		List<Mebel> mebelList = mebelDAO.getAllMebel();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID мебели");
		model.addColumn("Наименование");
		model.addColumn("Описание");
		model.addColumn("Материал");
		model.addColumn("Количество на складе");
		model.addColumn("Цена");

		for (Mebel mebel : mebelList) {
			model.addRow(new Object[] {
				mebel.getIdMebel(),
				mebel.getNaimenovanie(),
				mebel.getOpisanie(),
				mebel.getMaterial(),
				mebel.getKolvoSklad(),
				mebel.getCena()
			});
		}

		tableMebel.setModel(model);
		tableMebel.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableMebel.getColumnModel().getColumn(1).setPreferredWidth(170);
		tableMebel.getColumnModel().getColumn(2).setPreferredWidth(400);
		tableMebel.getColumnModel().getColumn(4).setPreferredWidth(150);
	}

	private void loadZakazy() {
		ZakazDAO zakazDAO = new ZakazDAO();
		List<Zakaz> zakazy = zakazDAO.getAllZakazy();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID заказа");
		model.addColumn("Сотрудник");
		model.addColumn("Клиент");
		model.addColumn("Мебель");
		model.addColumn("Количество");
		model.addColumn("Дата заказа");
		model.addColumn("Сумма заказа");

		for (Zakaz zakaz : zakazy) {
			model.addRow(new Object[] {
				zakaz.getIdZakaza(),
				zakaz.getSotr(),
				zakaz.getClient(),
				zakaz.getMebel(),
				zakaz.getKolvo(),
				zakaz.getDataZakaza(),
				zakaz.getSumma()
			});
		}

		tableZakazy.setModel(model);
		tableZakazy.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableZakazy.getColumnModel().getColumn(1).setPreferredWidth(220);
		tableZakazy.getColumnModel().getColumn(2).setPreferredWidth(220);
		tableZakazy.getColumnModel().getColumn(3).setPreferredWidth(170);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainWindow window = new MainWindow();
			window.setVisible(true);
		});
	}
}
