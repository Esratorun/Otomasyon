package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;
import Model.Klinik;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import java.awt.Point;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import Helper.*;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {

	static BasHekim bashekim = new BasHekim();
	Klinik klinik = new Klinik();
	private static final long serialVersionUID = 1L;
	protected static final Integer Integar = null;
	private JPanel contentPane;
	private JTextField fld_doktor_ekle_ad_soyad;
	private JTextField fld_doktor_ekle_sifre;
	private JTextField fld_doktor_ekle_tc;
	private JTextField fld_doktor_sil_id;
	private JTable tbl_doktor;
	private DefaultTableModel doktorModel = null;
	private Object[] doktorData = null;
	private JTextField fld_pol_adi;
	private JTable tbl_klinik;
	private DefaultTableModel klinikModel = null;
	private Object[] klinikData = null;
	private JPopupMenu klinikMenu;
	private JTable tbl_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public BashekimGUI(BasHekim bashekim) throws SQLException {

		// DOKTOR MODEL
		doktorModel = new DefaultTableModel();
		Object[] colDoktorIsim = new Object[4];
		colDoktorIsim[0] = "ID";
		colDoktorIsim[1] = "Ad-Soyad";
		colDoktorIsim[2] = "TC Kimlik No ";
		colDoktorIsim[3] = "Şifre";
		doktorModel.setColumnIdentifiers(colDoktorIsim);
		doktorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {

			doktorData[0] = bashekim.getDoctorList().get(i).getId();
			doktorData[1] = bashekim.getDoctorList().get(i).getIsim();
			doktorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doktorData[3] = bashekim.getDoctorList().get(i).getSifre();

			doktorModel.addRow(doktorData);

		}

		// KlinikModel

		klinikModel = new DefaultTableModel();
		Object[] colKlinik = new Object[2];
		colKlinik[0] = "ID";
		colKlinik[1] = "Poliklinik Adı";

		klinikModel.setColumnIdentifiers(colKlinik);
		klinikData = new Object[2];
		for (int i = 0; i < klinik.getList().size(); i++) {

			klinikData[0] = klinik.getList().get(i).getKlinik_id();
			klinikData[1] = klinik.getList().get(i).getKlinik_isim();

			klinikModel.addRow(klinikData);

		}

		// workerModel

		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "Id";
		colWorker[1] = "Ad-Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		setBackground(new Color(255, 255, 255));
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_bashekim_hosgeldiniz = new JLabel("Hoş Geldiniz , Sayın " + bashekim.getIsim());
		lbl_bashekim_hosgeldiniz.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_bashekim_hosgeldiniz.setBounds(10, 11, 276, 35);
		contentPane.add(lbl_bashekim_hosgeldiniz);

		JButton btn_bashekim_cikis = new JButton("ÇIKIŞ");
		btn_bashekim_cikis.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_bashekim_cikis.setBounds(751, 11, 125, 28);
		contentPane.add(btn_bashekim_cikis);

		JTabbedPane tabpane_bashekim_doktor_yonetimi = new JTabbedPane(JTabbedPane.TOP);
		tabpane_bashekim_doktor_yonetimi.setBackground(new Color(255, 255, 255));
		tabpane_bashekim_doktor_yonetimi.setBounds(24, 127, 836, 526);
		contentPane.add(tabpane_bashekim_doktor_yonetimi);

		Panel pnl_doktor_yonetimi = new Panel();
		pnl_doktor_yonetimi.setFont(new Font("Dialog", Font.BOLD, 12));
		tabpane_bashekim_doktor_yonetimi.addTab("Doktor Yönetimi", null, pnl_doktor_yonetimi, null);
		pnl_doktor_yonetimi.setLayout(null);

		JLabel lblNewLabel = new JLabel("DOKTOR KAYIT EKLEME");
		lblNewLabel.setBounds(40, 22, 198, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnl_doktor_yonetimi.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(485, 14, 2, 2);
		pnl_doktor_yonetimi.add(scrollPane);

		JLabel lblNewLabel_1 = new JLabel("Ad-Soyad     :");
		lblNewLabel_1.setBounds(40, 90, 86, 33);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_doktor_yonetimi.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("TC Kimlik No :");
		lblNewLabel_2.setBounds(40, 150, 86, 33);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_doktor_yonetimi.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Şifre            :");
		lblNewLabel_3.setBounds(40, 210, 86, 33);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_doktor_yonetimi.add(lblNewLabel_3);

		fld_doktor_ekle_ad_soyad = new JTextField();
		fld_doktor_ekle_ad_soyad.setBounds(128, 98, 147, 19);
		pnl_doktor_yonetimi.add(fld_doktor_ekle_ad_soyad);
		fld_doktor_ekle_ad_soyad.setColumns(10);

		fld_doktor_ekle_sifre = new JTextField();
		fld_doktor_ekle_sifre.setBounds(128, 218, 147, 19);
		fld_doktor_ekle_sifre.setColumns(10);
		pnl_doktor_yonetimi.add(fld_doktor_ekle_sifre);

		fld_doktor_ekle_tc = new JTextField();
		fld_doktor_ekle_tc.setBounds(128, 158, 147, 19);
		fld_doktor_ekle_tc.setColumns(10);
		pnl_doktor_yonetimi.add(fld_doktor_ekle_tc);

		JButton btn_doktor_ekle = new JButton("EKLE");
		btn_doktor_ekle.setBounds(40, 266, 235, 33);
		btn_doktor_ekle.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_doktor_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_doktor_ekle_ad_soyad.getText().length() == 0 || fld_doktor_ekle_sifre.getText().length() == 0
						|| fld_doktor_ekle_tc.getText().length() == 0) {

					Helper.showMsg("fill");
				} else {
					try {
						boolean kontrol = bashekim.doktorEkle(fld_doktor_ekle_tc.getText(),
								fld_doktor_ekle_sifre.getText(), fld_doktor_ekle_ad_soyad.getText());
						if (kontrol) {

							Helper.showMsg("success");

							fld_doktor_ekle_ad_soyad.setText(null);
							fld_doktor_ekle_tc.setText(null);
							fld_doktor_ekle_sifre.setText(null);

							DoktorModelGuncelle();

						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}

		});
		pnl_doktor_yonetimi.add(btn_doktor_ekle);

		JLabel lblDoktorSil = new JLabel("DOKTOR KAYIT SİLME");
		lblDoktorSil.setBounds(57, 335, 198, 20);
		lblDoktorSil.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnl_doktor_yonetimi.add(lblDoktorSil);

		JLabel lblNewLabel_4 = new JLabel("Doktor ID\r\n     :");
		lblNewLabel_4.setBounds(40, 390, 86, 33);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_doktor_yonetimi.add(lblNewLabel_4);

		fld_doktor_sil_id = new JTextField();
		fld_doktor_sil_id.setColumns(10);
		fld_doktor_sil_id.setBounds(128, 398, 147, 19);
		pnl_doktor_yonetimi.add(fld_doktor_sil_id);

		JButton btn_doktor_sil = new JButton("SİL");
		btn_doktor_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_doktor_sil_id.getText().length() == 0) {

					Helper.showMsg("Lütfen geçerli bir id seçin !");

				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doktor_sil_id.getText());
						try {
							boolean kontrol = bashekim.doktorSil(selectID);
							if (kontrol) {

								Helper.showMsg("success");
								fld_doktor_sil_id.setText(null);
								DoktorModelGuncelle();

							}

						} catch (SQLException e1) {

							e1.printStackTrace();
						}
					}
				}

			}
		});
		btn_doktor_sil.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_doktor_sil.setBounds(40, 444, 235, 33);
		pnl_doktor_yonetimi.add(btn_doktor_sil);

		JScrollPane w_scroll_doktor = new JScrollPane();
		w_scroll_doktor.setBounds(291, 26, 530, 463);
		pnl_doktor_yonetimi.add(w_scroll_doktor);

		tbl_doktor = new JTable(doktorModel);
		w_scroll_doktor.setViewportView(tbl_doktor);
		tbl_doktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					fld_doktor_sil_id.setText(tbl_doktor.getValueAt(tbl_doktor.getSelectedRow(), 0).toString());

				} catch (Exception ex) {

				}

			}

		});

		tbl_doktor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				if (e.getType() == TableModelEvent.UPDATE) {

					int selectID = Integer.parseInt(tbl_doktor.getValueAt(tbl_doktor.getSelectedRow(), 0).toString());

					String selectTcno = tbl_doktor.getValueAt(tbl_doktor.getSelectedRow(), 1).toString();
					String selectSifre = tbl_doktor.getValueAt(tbl_doktor.getSelectedRow(), 2).toString();
					String selectIsim = tbl_doktor.getValueAt(tbl_doktor.getSelectedRow(), 3).toString();
					try {
						boolean kontrol = bashekim.doktorGuncelle(selectID, selectIsim, selectTcno, selectSifre);
						if (kontrol) {
							Helper.showMsg("success");

						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

			}

		});

		JPanel w_klinik = new JPanel();
		w_klinik.setBackground(new Color(255, 255, 255));
		tabpane_bashekim_doktor_yonetimi.addTab("Poliklinikler", null, w_klinik, null);
		w_klinik.setLayout(null);

		JLabel lblPoliklinikEkle = new JLabel("POLİKLİNİK EKLE");
		lblPoliklinikEkle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPoliklinikEkle.setBounds(327, 10, 147, 20);
		w_klinik.add(lblPoliklinikEkle);

		JLabel lblNewLabel_1_1 = new JLabel("Poliklinik Adı  :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(295, 58, 104, 33);
		w_klinik.add(lblNewLabel_1_1);

		fld_pol_adi = new JTextField();
		fld_pol_adi.setColumns(10);
		fld_pol_adi.setBounds(387, 66, 153, 20);
		w_klinik.add(fld_pol_adi);

		JScrollPane scroll_worker = new JScrollPane();
		scroll_worker.setBounds(550, 14, 275, 479);
		w_klinik.add(scroll_worker);

		tbl_worker = new JTable();
		scroll_worker.setViewportView(tbl_worker);

		JButton btn_pol_ekle = new JButton("EKLE");
		btn_pol_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_pol_adi.getText().length() == 0) {

					Helper.showMsg("fill");
				} else {
					try {
						if (klinik.klinikEkle(fld_pol_adi.getText())) {
							Helper.showMsg("success");
							fld_pol_adi.setText(null);
							klinikModelGuncelle();

						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});
		btn_pol_ekle.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_pol_ekle.setBounds(295, 109, 241, 33);
		w_klinik.add(btn_pol_ekle);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 275, 483);
		w_klinik.add(scrollPane_1);

		klinikMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		klinikMenu.add(updateMenu);
		klinikMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selId = Integer.parseInt(tbl_klinik.getValueAt(tbl_klinik.getSelectedRow(), 0).toString());
				Klinik selectKlinik = klinik.getFetch(selId);
				KlinikGuncelleGUI updateGUI = new KlinikGuncelleGUI(selectKlinik);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {

						try {
							klinikModelGuncelle();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}
					}

				});
			}

		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (Helper.confirm("sure")) {
					int selId = Integer.parseInt(tbl_klinik.getValueAt(tbl_klinik.getSelectedRow(), 0).toString());
					try {
						if (klinik.klinikSil(selId)) {

							Helper.showMsg("success");
							klinikModelGuncelle();

						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		});

		tbl_klinik = new JTable(klinikModel);
		tbl_klinik.setComponentPopupMenu(klinikMenu);
		tbl_klinik.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = tbl_klinik.rowAtPoint(point);
				tbl_klinik.setRowSelectionInterval(selectedRow, selectedRow);

			}
		});
		scrollPane_1.setViewportView(tbl_klinik);

		JComboBox select_doktor = new JComboBox();
		select_doktor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		select_doktor.setBounds(295, 390, 245, 33);

		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			select_doktor.addItem(
					new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getIsim()));

		}

		select_doktor.addActionListener(e -> {

			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());

		});

		w_klinik.add(select_doktor);

		JButton btn_worker_ekle = new JButton("EKLE");
		btn_worker_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tbl_klinik.getSelectedRow();
				if (selRow >= 0) {
					String selKlinik = tbl_klinik.getModel().getValueAt(selRow, 0).toString();
					int selKlinikId = Integer.parseInt(selKlinik);
					Item doctorItem = (Item) select_doktor.getSelectedItem();

					try {
						boolean kontrol = bashekim.workerEkle(doctorItem.getKey(), selKlinikId);
						if (kontrol) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) tbl_worker.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < bashekim.getClinicDoktorList(selKlinikId).size(); i++) {
								workerData[0] = bashekim.getClinicDoktorList(selKlinikId).get(i).getId();
								workerData[1] = bashekim.getClinicDoktorList(selKlinikId).get(i).getIsim();
								workerModel.addRow(workerData);
							}
							tbl_worker.setModel(workerModel);
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz !");
				}
			}
		});
		btn_worker_ekle.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_worker_ekle.setBounds(295, 433, 245, 33);
		w_klinik.add(btn_worker_ekle);

		JLabel lblDoktorAta = new JLabel("DOKTOR ATA");
		lblDoktorAta.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDoktorAta.setBounds(345, 194, 120, 20);
		w_klinik.add(lblDoktorAta);

		JLabel lblNewLabel_1_1_1 = new JLabel("Poliklinik Adı  :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(295, 238, 104, 33);
		w_klinik.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Doktor          :");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_1.setBounds(295, 347, 104, 33);
		w_klinik.add(lblNewLabel_1_1_1_1);

		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tbl_klinik.getSelectedRow();
				if (selRow >= 0) {
					String selKlinik = tbl_klinik.getModel().getValueAt(selRow, 0).toString();
					int selKlinikId = Integar.parseInt(selKlinik);
					DefaultTableModel clearModel = (DefaultTableModel) tbl_worker.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < bashekim.getClinicDoktorList(selKlinikId).size(); i++) {
							workerData[0] = bashekim.getClinicDoktorList(selKlinikId).get(i).getId();
							workerData[1] = bashekim.getClinicDoktorList(selKlinikId).get(i).getIsim();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();

					}
					tbl_worker.setModel(workerModel);
				} else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz !");
				}

			}
		});
		btn_workerSelect.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_workerSelect.setBounds(295, 281, 241, 33);
		w_klinik.add(btn_workerSelect);

	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		super.update(g);
	}

	public void DoktorModelGuncelle() throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) tbl_doktor.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {

			doktorData[0] = bashekim.getDoctorList().get(i).getId();
			doktorData[1] = bashekim.getDoctorList().get(i).getIsim();
			doktorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doktorData[3] = bashekim.getDoctorList().get(i).getSifre();

			doktorModel.addRow(doktorData);
		}

	}

	public void klinikModelGuncelle() throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) tbl_klinik.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < klinik.getList().size(); i++) {
			klinikData[0] = klinik.getList().get(i).getKlinik_id();
			klinikData[1] = klinik.getList().get(i).getKlinik_isim();
			klinikModel.addRow(klinikData);

		}

	}
}
