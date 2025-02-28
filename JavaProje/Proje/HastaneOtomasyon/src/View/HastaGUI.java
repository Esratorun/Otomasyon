package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Hasta;
import Model.Klinik;
import Model.Whour;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final String Integar = null;
	private JPanel contentPane;
	private static Hasta hasta = new Hasta();
	private Klinik klinik = new Klinik();
	private JTable tbl_doktor;
	private DefaultTableModel doktorModel;
	private Object[] doktorData = null;
	private JTable tbl_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoktorId=0;
	private String selectDoktorName=null;
	private JTable tbl_appoint;
	private DefaultTableModel appointModel=null;
	private Object[] appointData=null;
	private Appointment appoint =new Appointment();
	/**

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HastaGUI(Hasta hasta) {

		doktorModel = new DefaultTableModel();
		Object[] colDoktor = new Object[2];
		colDoktor[0] = "ID";
		colDoktor[1] = "Ad-Soyad";
		doktorModel.setColumnIdentifiers(colDoktor);
		doktorData = new Object[2];
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];

		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Doktor ";
		colAppoint[2] = "Tarih ";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		try {
			for(int i=0;i<appoint.getHastaList(hasta.getId()).size();i++) {
				appointData[0]=appoint.getHastaList(hasta.getId()).get(i).getId();
				appointData[1]=appoint.getHastaList(hasta.getId()).get(i).getDoktor_isim();
				appointData[2]=appoint.getHastaList(hasta.getId()).get(i).getApp_date();
				appointModel.addRow(appointData);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

		setBackground(new Color(255, 255, 255));
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_bashekim_hosgeldiniz = new JLabel("Hoş Geldiniz , Sayın " + hasta.getIsim());
		lbl_bashekim_hosgeldiniz.setBounds(10, 15, 258, 20);
		lbl_bashekim_hosgeldiniz.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lbl_bashekim_hosgeldiniz);

		JButton btn_bashekim_cikis = new JButton("ÇIKIŞ");
		btn_bashekim_cikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GirisGUI login=new GirisGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_bashekim_cikis.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_bashekim_cikis.setBounds(726, 10, 125, 28);
		contentPane.add(btn_bashekim_cikis);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBackground(new Color(255, 255, 255));
		w_tab.setBounds(10, 62, 866, 491);
		contentPane.add(w_tab);

		JPanel w_appointment = new JPanel();
		w_tab.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(559, 38, 292, 416);
		w_appointment.add(w_scrollWhour);

		tbl_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(tbl_whour);
		

		JScrollPane w_scrollDoktor = new JScrollPane();
		w_scrollDoktor.setBounds(10, 38, 292, 416);
		w_appointment.add(w_scrollDoktor);

		tbl_doktor = new JTable(doktorModel);
		w_scrollDoktor.setViewportView(tbl_doktor);

		JLabel lbl_doktor_listesi = new JLabel("DOKTOR LİSTESİ");
		lbl_doktor_listesi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_doktor_listesi.setBounds(10, 10, 115, 28);
		w_appointment.add(lbl_doktor_listesi);

		JLabel lblNewLabel_1_1 = new JLabel("Poliklinik Adı  :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(312, 85, 104, 33);
		w_appointment.add(lblNewLabel_1_1);

		JComboBox select_klinik = new JComboBox();
		select_klinik.setBounds(407, 89, 142, 26);
		select_klinik.addItem("      Poliklinik Seç  ");
		try {
			for (int i = 0; i < klinik.getList().size(); i++) {

				select_klinik.addItem(
						new Item(klinik.getList().get(i).getKlinik_id(), klinik.getList().get(i).getKlinik_isim()));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		select_klinik.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_klinik.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) tbl_doktor.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < klinik.getClinicDoktorList(item.getKey()).size(); i++) {

							doktorData[0] = klinik.getClinicDoktorList(item.getKey()).get(i).getId();
							doktorData[1] = klinik.getClinicDoktorList(item.getKey()).get(i).getIsim();
							doktorModel.addRow(doktorData);

						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				}

			}

		});

		w_appointment.add(select_klinik);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Doktor          ");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_1.setBounds(312, 171, 104, 33);
		w_appointment.add(lblNewLabel_1_1_1_1);

		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tbl_doktor.getSelectedRow();
				if (row >= 0) {
					
					String value = tbl_doktor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) tbl_whour.getModel();
					clearModel.setRowCount(0);
					Whour whour = new Whour();
					try {
						for (int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData); }
					
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					tbl_whour.setModel(whourModel);
					selectDoktorId=id;
					selectDoktorName=tbl_doktor.getModel().getValueAt(row, 1).toString();
					
				}else {
					Helper.showMsg("Lütfen bir doktor seçiniz !");
				}

			}
		});
		btn_workerSelect.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_workerSelect.setBounds(308, 214, 241, 33);
		w_appointment.add(btn_workerSelect);
		
		JLabel lbl_doktor_listesi_1 = new JLabel("UYGUN  SAATLER");
		lbl_doktor_listesi_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_doktor_listesi_1.setBounds(559, 10, 115, 28);
		w_appointment.add(lbl_doktor_listesi_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Randevu Al");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_1_1.setBounds(312, 286, 104, 33);
		w_appointment.add(lblNewLabel_1_1_1_1_1);
		
		JButton btn_addApp = new JButton("Randevu Al");
		btn_addApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=tbl_whour.getSelectedRow();
				if(selRow>=0) {
					String date=tbl_whour.getModel().getValueAt(selRow, 1).toString();
					try{
						boolean kontrol=hasta.addAppointment(selectDoktorId, hasta.getId(), selectDoktorName, hasta.getIsim(), date);
						if(kontrol) {
							Helper.showMsg("success");
							hasta.updateWhourStatus(selectDoktorId, date);
							updateWhourModel(selectDoktorId);
							
						}else {
							Helper.showMsg("error");
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					}else{
				 
					Helper.showMsg("Lütfen geçerli bir tarih seçiniz !");
				}
				
				
			}
		});
		btn_addApp.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_addApp.setBounds(308, 325, 241, 33);
		w_appointment.add(btn_addApp);
		
		JPanel w_appoint = new JPanel();
		w_tab.addTab("Randevularım", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane w_scroolApp = new JScrollPane();
		w_scroolApp.setBounds(10, 10, 841, 444);
		w_appoint.add(w_scroolApp);
		
		tbl_appoint = new JTable(appointModel);
		w_scroolApp.setViewportView(tbl_appoint);
	}
	public void updateWhourModel(int doktor_id) throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) tbl_whour.getModel();
		clearModel.setRowCount(0);
		Whour whour = new Whour();
		for (int i = 0; i < whour.getWhourList(doktor_id).size(); i++) {

			whourData[0] = whour.getWhourList(doktor_id).get(i).getId();
			whourData[1] = whour.getWhourList(doktor_id).get(i).getWdate();
			whourModel.addRow(whourData);

		}
	}
	
	public void updateAppointModel(int hasta_id) throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) tbl_whour.getModel();
		clearModel.setRowCount(0);
		
		for(int i=0;i<appoint.getHastaList(hasta_id).size();i++) {
			appointData[0]=appoint.getHastaList(hasta_id).get(i).getId();
			appointData[1]=appoint.getHastaList(hasta_id).get(i).getDoktor_isim();
			appointData[2]=appoint.getHastaList(hasta_id).get(i).getApp_date();
			appointModel.addRow(appointData);
		}
	}

}
