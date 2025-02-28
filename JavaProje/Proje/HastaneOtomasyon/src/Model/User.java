package Model;

import Helper.DBCon;

public class User {
	private int id;
	String tcno,isim,sifre,type;
	
	DBCon conn=new DBCon();
	
	
	public User(int id, String tcno, String isim, String sifre, String type) {
		this.id = id;
		this.tcno = tcno;
		this.isim = isim;
		this.sifre = sifre;
		this.type = type;
	}
	
	
	
	public User() {}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTcno() {
		return tcno;
	}
	public void setTcno(String tcno) {
		this.tcno = tcno;
	}
	public String getIsim() {
		return isim;
	}
	public void setIsim(String isim) {
		this.isim = isim;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	

}
