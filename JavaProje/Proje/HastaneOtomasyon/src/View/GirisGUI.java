package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JPasswordField;
import Helper.*;
import Model.BasHekim;
import Model.Doktor;
import Model.Hasta;

public class GirisGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private DBCon conn = new DBCon();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GirisGUI frame = new GirisGUI();
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
	public GirisGUI() {
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel("");
		lbl_logo.setBounds(300, 10, 321, 271);
		lbl_logo.setIcon(new ImageIcon("C:\\Users\\esrat\\OneDrive\\Masaüstü\\antalya.png"));
		w_pane.add(lbl_logo);

		JTabbedPane tabPane_Giris = new JTabbedPane(JTabbedPane.TOP);
		tabPane_Giris.setBounds(226, 306, 472, 335);
		w_pane.add(tabPane_Giris);

		JPanel pnl_hasta_girisi = new JPanel();
		pnl_hasta_girisi.setBackground(new Color(255, 255, 255));
		pnl_hasta_girisi.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_hasta_girisi.setToolTipText("");
		tabPane_Giris.addTab("Hasta Girişi", null, pnl_hasta_girisi, null);

		JLabel lbl_hasta_tc = new JLabel("TC Kimlik No     :");
		lbl_hasta_tc.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lbl_hasta_sifre = new JLabel("Şifre                 :");
		lbl_hasta_sifre.setFont(new Font("Tahoma", Font.BOLD, 12));

		fld_hasta_tc = new JTextField();
		fld_hasta_tc.setColumns(10);

		JButton btn_hasta_giris = new JButton("GİRİŞ");
		btn_hasta_giris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_hasta_tc.getText().length() == 0 || fld_hasta_sifre.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = (Statement) con.createStatement();
						ResultSet rs = ((java.sql.Statement) st).executeQuery("SELECT* FROM user");

						while (rs.next()) {

							if (fld_hasta_tc.getText().equals(rs.getString("tcno"))
									&& fld_hasta_sifre.getText().equals(rs.getString("sifre"))) {

								if (rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setSifre("sifre");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setIsim(rs.getString("isim"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;
								}
								
							}
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (key) {
									Helper.showMsg("Kullanıcı bulunamadı ! Lütfen kayıt olunuz !");
								}
				}
			}
		});
		btn_hasta_giris.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btn_hasta_kayit = new JButton("KAYIT OL");
		btn_hasta_kayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();

			}
		});
		btn_hasta_kayit.setFont(new Font("Tahoma", Font.BOLD, 16));

		fld_hasta_sifre = new JPasswordField();
		GroupLayout gl_pnl_hasta_girisi = new GroupLayout(pnl_hasta_girisi);
		gl_pnl_hasta_girisi.setHorizontalGroup(gl_pnl_hasta_girisi.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_hasta_girisi.createSequentialGroup().addGroup(gl_pnl_hasta_girisi
						.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnl_hasta_girisi.createSequentialGroup().addGap(10).addGroup(gl_pnl_hasta_girisi
								.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_pnl_hasta_girisi.createSequentialGroup()
										.addComponent(lbl_hasta_tc, GroupLayout.PREFERRED_SIZE, 101,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(fld_hasta_tc,
												GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnl_hasta_girisi.createSequentialGroup()
										.addComponent(lbl_hasta_sifre, GroupLayout.PREFERRED_SIZE, 101,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(fld_hasta_sifre))))
						.addGroup(gl_pnl_hasta_girisi.createSequentialGroup().addGap(45)
								.addComponent(btn_hasta_giris, GroupLayout.PREFERRED_SIZE, 126,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn_hasta_kayit, GroupLayout.PREFERRED_SIZE, 126,
										GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(85, Short.MAX_VALUE)));
		gl_pnl_hasta_girisi.setVerticalGroup(gl_pnl_hasta_girisi.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_hasta_girisi.createSequentialGroup().addGap(28).addGroup(gl_pnl_hasta_girisi
						.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_hasta_tc, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnl_hasta_girisi.createSequentialGroup().addGap(12).addComponent(fld_hasta_tc,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(38)
						.addGroup(gl_pnl_hasta_girisi.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_hasta_sifre, GroupLayout.PREFERRED_SIZE, 41,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(fld_hasta_sifre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(47)
						.addGroup(gl_pnl_hasta_girisi.createParallelGroup(Alignment.BASELINE)
								.addComponent(btn_hasta_giris, GroupLayout.PREFERRED_SIZE, 41,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btn_hasta_kayit, GroupLayout.PREFERRED_SIZE, 41,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(72, Short.MAX_VALUE)));
		pnl_hasta_girisi.setLayout(gl_pnl_hasta_girisi);

		JPanel pnl_doktor_girisi = new JPanel();
		tabPane_Giris.addTab("Doktor Girşi", null, pnl_doktor_girisi, null);

		JPanel pnl_hasta_girisi_1 = new JPanel();
		pnl_hasta_girisi_1.setToolTipText("");
		pnl_hasta_girisi_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_hasta_girisi_1.setBackground(Color.WHITE);

		JLabel lbl_doktor_tc = new JLabel("TC Kimlik No     :");
		lbl_doktor_tc.setFont(new Font("Tahoma", Font.BOLD, 12));

		fld_doktor_tc = new JTextField();
		fld_doktor_tc.setColumns(10);

		JLabel lbl_doktor_sifre = new JLabel("Şifre                 :");
		lbl_doktor_sifre.setFont(new Font("Tahoma", Font.BOLD, 12));

		JButton btn_doktor_giris = new JButton("GİRİŞ");
		btn_doktor_giris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_doktor_tc.getText().length() == 0 || fld_doktor_sifre.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

					try {
						Connection con = conn.connDb();
						Statement st = (Statement) con.createStatement();
						ResultSet rs = ((java.sql.Statement) st).executeQuery("SELECT* FROM user");

						while (rs.next()) {

							if (fld_doktor_tc.getText().equals(rs.getString("tcno"))
									&& fld_doktor_sifre.getText().equals(rs.getString("sifre"))) {

								if (rs.getString("type").equals("bashekim")) {
									BasHekim bshekim = new BasHekim();
									bshekim.setId(rs.getInt("id"));
									bshekim.setSifre("sifre");
									bshekim.setTcno(rs.getString("tcno"));
									bshekim.setIsim(rs.getString("isim"));
									bshekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bshekim);
									bGUI.setVisible(true);
									dispose();
								}

								if (rs.getString("type").equals("doktor")) {
									Doktor doktor = new Doktor();
									doktor.setId(rs.getInt("id"));
									doktor.setSifre("sifre");
									doktor.setTcno(rs.getString("tcno"));
									doktor.setIsim(rs.getString("isim"));
									doktor.setType(rs.getString("type"));
									DoktorGUI dGUI = new DoktorGUI(doktor);
									dGUI.setVisible(true);
									dispose();
								}
							}

						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});
		btn_doktor_giris.setFont(new Font("Tahoma", Font.BOLD, 16));

		fld_doktor_sifre = new JPasswordField();
		GroupLayout gl_pnl_hasta_girisi_1 = new GroupLayout(pnl_hasta_girisi_1);
		gl_pnl_hasta_girisi_1.setHorizontalGroup(gl_pnl_hasta_girisi_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_hasta_girisi_1.createSequentialGroup().addGroup(gl_pnl_hasta_girisi_1
						.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnl_hasta_girisi_1.createSequentialGroup().addContainerGap().addComponent(
								btn_doktor_giris, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_pnl_hasta_girisi_1.createSequentialGroup().addGap(10)
								.addGroup(gl_pnl_hasta_girisi_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_pnl_hasta_girisi_1.createSequentialGroup()
												.addComponent(lbl_doktor_tc, GroupLayout.PREFERRED_SIZE, 101,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(fld_doktor_tc,
														GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_pnl_hasta_girisi_1.createSequentialGroup()
												.addComponent(lbl_doktor_sifre, GroupLayout.PREFERRED_SIZE, 101,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(fld_doktor_sifre, GroupLayout.PREFERRED_SIZE, 271,
														GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(81, Short.MAX_VALUE)));
		gl_pnl_hasta_girisi_1.setVerticalGroup(gl_pnl_hasta_girisi_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_hasta_girisi_1.createSequentialGroup().addGap(28).addGroup(gl_pnl_hasta_girisi_1
						.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_doktor_tc, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnl_hasta_girisi_1.createSequentialGroup().addGap(12).addComponent(fld_doktor_tc,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(38)
						.addGroup(gl_pnl_hasta_girisi_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_doktor_sifre, GroupLayout.PREFERRED_SIZE, 41,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(fld_doktor_sifre, GroupLayout.PREFERRED_SIZE, 21,
										GroupLayout.PREFERRED_SIZE))
						.addGap(43)
						.addComponent(btn_doktor_giris, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(76, Short.MAX_VALUE)));
		pnl_hasta_girisi_1.setLayout(gl_pnl_hasta_girisi_1);
		GroupLayout gl_pnl_doktor_girisi = new GroupLayout(pnl_doktor_girisi);
		gl_pnl_doktor_girisi.setHorizontalGroup(gl_pnl_doktor_girisi.createParallelGroup(Alignment.LEADING)
				.addComponent(pnl_hasta_girisi_1, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE));
		gl_pnl_doktor_girisi.setVerticalGroup(gl_pnl_doktor_girisi.createParallelGroup(Alignment.LEADING)
				.addComponent(pnl_hasta_girisi_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE));
		pnl_doktor_girisi.setLayout(gl_pnl_doktor_girisi);
	}

	private JLabel lbl_logo;
	private JTextField fld_hasta_tc;
	private JTextField fld_doktor_tc;
	private JPasswordField fld_doktor_sifre;
	private JPasswordField fld_hasta_sifre;

	public Icon getLbl_logoIcon() {
		return lbl_logo.getIcon();
	}

	public void setLbl_logoIcon(Icon icon) {
		lbl_logo.setIcon(icon);
	}
}
