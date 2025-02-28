package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;
import Model.User;

import java.awt.Dialog.ModalExclusionType;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_isim;
	private JPasswordField sifre;
	private JButton btn_backto;
	private Hasta hasta=new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 409);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad-Soyad     :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(40, 60, 86, 33);
		w_pane.add(lblNewLabel_1);
		
		fld_isim = new JTextField();
		fld_isim.setColumns(10);
		fld_isim.setBounds(129, 68, 147, 19);
		w_pane.add(fld_isim);
		
		JLabel lblNewLabel_2 = new JLabel("TC Kimlik No :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(40, 120, 86, 33);
		w_pane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Şifre            :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(40, 180, 86, 33);
		w_pane.add(lblNewLabel_3);
		
		JTextField fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(129, 128, 147, 19);
		w_pane.add(fld_tcno);
		
		sifre = new JPasswordField();
		sifre.setBounds(129, 188, 147, 19);
		w_pane.add(sifre);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length()==0|| fld_isim.getText().length()==0||sifre.getText().length()==0) {
					Helper.showMsg("fill");
				}else{
					try {
						boolean kontrol=hasta.register(fld_tcno.getText(), sifre.getText(), fld_isim.getText());
						
						if(kontrol) {
							Helper.showMsg("success");
							GirisGUI giris=new GirisGUI();
							giris.setVisible(true);
							dispose();
						}else {
							Helper.showMsg("error");
						}
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_register.setBounds(40, 240, 236, 33);
		w_pane.add(btn_register);
		
		btn_backto = new JButton("Geri Dön");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GirisGUI giris=new GirisGUI();
				giris.setVisible(true);
				dispose();
			}
		});
		btn_backto.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_backto.setBounds(40, 297, 236, 33);
		w_pane.add(btn_backto);
	}
}
