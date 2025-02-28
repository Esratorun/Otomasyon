package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Klinik;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class KlinikGuncelleGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fld_klinik_adi;
	private static Klinik klinik;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		klinik=new Klinik(1,"");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KlinikGuncelleGUI frame = new KlinikGuncelleGUI(klinik);
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
	public KlinikGuncelleGUI(Klinik klinik) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 341, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Poliklinik Adı    :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(32, 48, 104, 33);
		contentPane.add(lblNewLabel_1_1);
		
		fld_klinik_adi = new JTextField();
		fld_klinik_adi.setColumns(10);
		fld_klinik_adi.setBounds(132, 56, 174, 20);
		fld_klinik_adi.setText(klinik.getKlinik_isim());
		contentPane.add(fld_klinik_adi);
		
		JButton btn_duzenle = new JButton("DÜZENLE");
		btn_duzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						klinik.klinikGuncelle(klinik.getKlinik_id(),fld_klinik_adi.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btn_duzenle.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_duzenle.setBounds(47, 103, 244, 33);
		contentPane.add(btn_duzenle);
	}
}
