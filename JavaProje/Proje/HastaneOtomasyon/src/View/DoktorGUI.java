package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doktor;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;

import Helper.Helper;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoktorGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final String Integar = null;
	private JPanel w_pane;
	private static Doktor doktor = new Doktor();
	private JTable tbl_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData=null;
	private Whour whour=new Whour();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoktorGUI frame = new DoktorGUI(doktor);
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
	public DoktorGUI(Doktor doktor) {
		
		whourModel=new DefaultTableModel();
		Object[] colWhour=new Object[2];
		colWhour[0]="Id";
		colWhour[1]="Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData=new Object[2];
		
		try {
			for (int i=0;i<doktor.getWhourList(doktor.getId()).size();i++) {
				whourData[0]=doktor.getWhourList(doktor.getId()).get(i).getId();  //dikkat hata çıkabilir
				whourData[1]=doktor.getWhourList(doktor.getId()).get(i).getWdate();  //dikkat hata çıkabilir
				whourModel.addRow(whourData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setToolTipText("Hastane Yönetim Sistemi");
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_bashekim_hosgeldiniz = new JLabel("Hoş Geldiniz , Sayın " + doktor.getIsim());
		lbl_bashekim_hosgeldiniz.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_bashekim_hosgeldiniz.setBounds(10, 10, 276, 35);
		w_pane.add(lbl_bashekim_hosgeldiniz);

		JButton btn_bashekim_cikis = new JButton("ÇIKIŞ");
		btn_bashekim_cikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GirisGUI login=new GirisGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_bashekim_cikis.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_bashekim_cikis.setBounds(738, 19, 125, 28);
		w_pane.add(btn_bashekim_cikis);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBackground(new Color(255, 255, 255));
		w_tab.setBounds(10, 80, 821, 560);
		w_pane.add(w_tab);

		JPanel w_whour = new JPanel();
		w_tab.addTab("Çalışma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);

		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 17, 155, 21);
		w_whour.add(select_date);

		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] { "08:00", "08:15", "08:30", "08:45", "09:00",
				"09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45",
				"12:00", "12:15", "12:30", "12:45", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00",
				"15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00" }));
		select_time.setBounds(175, 17, 66, 21);
		w_whour.add(select_time);

		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date="";
				try {
					date=sdf.format(select_date.getDate());
				}catch(Exception e2) {
				
					}
				if (date.length() == 0) {

					Helper.showMsg("Lütfen geçerli bir tarih giriniz !");
				} else {
					
					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					
					try {
						boolean kontrol = doktor.whourEkle(doktor.getId(), doktor.getIsim(), selectDate);
						if (kontrol) {
							Helper.showMsg("success");
							whourModelGuncelle(doktor);
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				}

			}
		});
		btn_addWhour.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_addWhour.setBounds(264, 17, 111, 21);
		w_whour.add(btn_addWhour);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(10, 60, 796, 449);
		w_whour.add(w_scrollWhour);

		tbl_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(tbl_whour);
		
		JButton btn_whor_sil = new JButton("Sil");
		btn_whor_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow=tbl_whour.getSelectedRow();
				if(selRow>=0) {
					String selectRow=tbl_whour.getModel().getValueAt(selRow,0).toString();
					int selId=Integer.parseInt(selectRow);
					boolean kontrol;
					try{
						kontrol=doktor.whourSil(selId);
						if (kontrol) {
					
						Helper.showMsg("success");
						whourModelGuncelle(doktor);
					}
					
					else {
						Helper.showMsg("error");
					}
					
				}catch(SQLException ex){
					ex.printStackTrace();
				}
				}else {
					Helper.showMsg("Lütfen bir tarih seçiniz !");
				}
			}
		});
		btn_whor_sil.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_whor_sil.setBounds(670, 17, 111, 21);
		w_whour.add(btn_whor_sil);
	}
	
	public void whourModelGuncelle(Doktor doktor) throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) tbl_whour.getModel();
		clearModel.setRowCount(0);

		try {
			for (int i=0;i<doktor.getWhourList(doktor.getId()).size();i++) {
				whourData[0]=doktor.getWhourList(doktor.getId()).get(i).getWhour_id(); 
				whourData[1]=doktor.getWhourList(doktor.getId()).get(i).getWdate();  
				whourModel.addRow(whourData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
