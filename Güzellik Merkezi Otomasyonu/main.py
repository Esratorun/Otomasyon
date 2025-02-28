# -*- coding: utf-8 -*-
"""
Created on Tue May 28 21:51:45 2024

@author: esrat
"""

import sys
from PyQt5 import QtWidgets
from PyQt5.QtWidgets import *
from GoldenAuraUi import *
from HakkindaUi import*

Uygulama=QApplication(sys.argv)
penAna=QMainWindow()
ui=Ui_GoldenAura()
ui.setupUi(penAna)
penAna.show()

penHakkinda=QDialog()
ui2=Ui_Dialog()
ui2.setupUi(penHakkinda)

import sqlite3
global curs
global conn
conn=sqlite3.connect('veritabani.db')
curs=conn.cursor()
sorguCreTblGoldenAura=("CREATE TABLE IF NOT EXISTS GoldenAura(            \
                       Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT  ,   \
                       Ad TEXT NOT NULL                            ,   \
                       Soyad TEXT NOT NULL                         ,   \
                       TC TEXT NOT NULL                ,   \
                       Tel TEXT NOT NULL                          ,   \
                       DogumTarihi TEXT NOT NULL                          ,   \
                       CiltBakimi TEXT NOT NULL                     ,   \
                       TirnakBakimi TEXT NOT NULL                   ,   \
                       KaliciMakyaj TEXT NOT NULL                   ,   \
                       EpilasyonAgda TEXT NOT NULL                      ,   \
                       GuzellikUzmani TEXT NOT NULL                 ,   \
                       IslemTutari TEXT NOT NULL                   ,   \
                       OdenenTutar TEXT NOT NULL                        ,   \
                       KalanTutar TEXT NOT NULL) ")

curs.execute(sorguCreTblGoldenAura)
conn.commit()
    #---------------------------KAYDET-----------------------------------#

def ekle():
    _lneAd=ui.lneAd.text()
    _lneSoyad=ui.lneSoyad.text()
    _lneTCK=ui.lneTCK.text()
    _lneTel=ui.lneTel.text()
    _cwDogum=ui.cwDogum.selectedDate().toString(QtCore.Qt.ISODate)
    _cbCiltBakimi=ui.cbCiltBakimi.currentText()
    _cbTirnakBakimi=ui.cbTirnakBakimi.currentText()
    _cbKaliciMakyaj=ui.cbKaliciMakyaj.currentText()
    _cbEpilasyon=ui.cbEpilasyon.currentText()
    _cbGuzellikUzmani=ui.cbGuzellikUzmani.currentText()
    _lneIslemTutari=ui.lneIslemTutari.text()
    _lneOdenen=ui.lneOdenen.text()
    _lneKalan=ui.lneKalan.text()
    
    
                                
    curs.execute("INSERT INTO GoldenAura \
                 (Ad,Soyad,TC,Tel,DogumTarihi,CiltBakimi,TirnakBakimi,KaliciMakyaj,\
                   EpilasyonAgda,GuzellikUzmani,IslemTutari,OdenenTutar,KalanTutar) \
                   VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)" , \
                       (_lneAd,_lneSoyad,_lneTCK,_lneTel,_cwDogum,_cbCiltBakimi,  \
                         _cbTirnakBakimi,_cbKaliciMakyaj,_cbEpilasyon,_cbGuzellikUzmani,\
                         _lneIslemTutari,_lneOdenen,_lneKalan))
        
    conn.commit()

 #--------------------KAYIT LİSTELE--------------------------------------#
def Listele():
    ui.Liste.clear()
    ui.Liste.setHorizontalHeaderLabels(('Müşteri No','Ad','Soyad','TC Kimlik No','Telefon No','Doğum Tarihi',\
                                        'Cilt Bakımı','Tırnak Bakımı','Kalıcı Makyaj','Epilasyon-Ağda',\
                                            'Güzellik Uzmanı','İşlem Tutarı','Ödenen Tutar','Kalan Tutar'))
 
    ui.Liste.horizontalHeader().setSectionResizeMode(QHeaderView.Stretch)
    curs.execute("SELECT * FROM GoldenAura")
    for satirIndeks,satirVeri in enumerate(curs):
        for sutunIndeks,sutunVeri in enumerate (satirVeri):
            ui.Liste.setItem(satirIndeks,sutunIndeks,QTableWidgetItem(str(sutunVeri)))
    ui.lneAd.clear()
    ui.lneSoyad.clear()
    ui.lneTCK.clear()
    ui.lneTel.clear()
    ui.cbCiltBakimi.setCurrentIndex(-1)
    ui.cbTirnakBakimi.setCurrentIndex(-1)
    ui.cbKaliciMakyaj.setCurrentIndex(-1)
    ui.cbEpilasyon.setCurrentIndex(-1)
    ui.cbGuzellikUzmani.setCurrentIndex(-1)
    ui.lneIslemTutari.clear()
    ui.lneOdenen.clear()
    ui.lneKalan.clear()
    curs.execute("SELECT COUNT(*)FROM GoldenAura")
    kayitSayisi=curs.fetchone()
    ui.lblKayitSayisi.setText(str(kayitSayisi[0]))

    

Listele()

 #---------------------------ÇIKIŞ---------------------------------------#
 
def cikis():
    cevap=QMessageBox.question(penAna,"ÇIKIŞ","Programdan Çıkmak İstediğinize Emin Misiniz ?",\
                         QMessageBox.Yes | QMessageBox.No)
         
    if cevap==QMessageBox.Yes:
        conn.close()
        sys.exit(Uygulama.exec_())
             
    else:
        penAna.show()





def Sil():
    cevap=QMessageBox.question(penAna,"KAYIT SİL","Kaydı Silmek İstediğiinize Emin Misiniz ?",\
                                 QMessageBox.Yes | QMessageBox.No)
    if cevap==QMessageBox.Yes:
        secili=ui.Liste.selectedItems()
        silinecek=secili[3].text()
    try:
        curs.execute("DELETE FROM GoldenAura WHERE TC='%s'"%(silinecek))
        conn.commit()
        Listele()
        ui.statusbar.showMessage("Kayıt Başarıyla Silindi..",10000)
    except Exception as Hata:
        ui.statusbar.showMessage("Şöyle bir hata ile karşılaşıldı :"+str(Hata))
        
    else:
        ui.statusbar.showMessage("Silme İşlemi İptal Edildi..",10000)

#-------------------------ARA---------------------------#

def Ara():
    aranan1=ui.lneTCK.text()
    aranan2=ui.lneAd.text()
    aranan3=ui.lneSoyad.text()
    curs.execute("SELECT * FROM GoldenAura WHERE TC=? OR Ad=? OR Soyad=? OR (Ad=? AND Soyad=?)",\
                 (aranan1,aranan2,aranan3,aranan2,aranan3))
        
    conn.commit()
    
    #--------------------------try-except------------------------#
    ui.Liste.clear()
    for satirIndeks,satirVeri in enumerate(curs):
        for sutunIndeks,sutunVeri in enumerate (satirVeri):
            ui.Liste.setItem(satirIndeks,sutunIndeks,QTableWidgetItem(str(sutunVeri)))
    
    #------------------------DOLDUR-----------------------------------#
def Doldur():
    secili = ui.Liste.selectedItems()
    ui.lneAd.setText(secili[1].text())
    ui.lneSoyad.setText(secili[2].text())
    ui.lneTCK.setText(secili[3].text())
    yil = int(secili[5].text()[0:4])
    ay = int(secili[5].text()[5:7])
    gun = int(secili[5].text()[8:10])
    ui.cwDogum.setSelectedDate(QtCore.QDate(yil, ay, gun))
    ui.lneTel.setText(secili[4].text())
    ui.cbCiltBakimi.setCurrentText(secili[6].text())
    ui.cbTirnakBakimi.setCurrentText(secili[7].text())
    ui.cbKaliciMakyaj.setCurrentText(secili[8].text())
    ui.cbEpilasyon.setCurrentText(secili[9].text())
    ui.cbGuzellikUzmani.setCurrentText(secili[10].text())
    ui.lneIslemTutari.setText(secili[11].text())
    ui.lneOdenen.setText(secili[12].text())
    ui.lneKalan.setText(secili[13].text())

   #--------------------------GÜNCELLE--------------------------------# 

def Guncelle(): 
    cevap=QMessageBox.question(penAna,"Kayıt Güncelle","Kaydı Güncellemek İstediğinize Emin Misiniz?",\
                                QMessageBox.Yes | QMessageBox.No)
        
    if cevap==QMessageBox.Yes:
        try:
            secili = ui.Liste.selectedItems()
            _Id=int(secili[0].text())
            _lneAd=ui.lneAd.text()
            _lneSoyad=ui.lneSoyad.text()
            _lneTCK=ui.lneTCK.text()
            _lneTel=ui.lneTel.text()
            _cwDogum=ui.cwDogum.selectedDate().toString(QtCore.Qt.ISODate)
            _cbCiltBakimi=ui.cbCiltBakimi.currentText()
            _cbTirnakBakimi=ui.cbTirnakBakimi.currentText()
            _cbKaliciMakyaj=ui.cbKaliciMakyaj.currentText()
            _cbEpilasyon=ui.cbEpilasyon.currentText()
            _cbGuzellikUzmani=ui.cbGuzellikUzmani.currentText()
            _lneIslemTutari=ui.lneIslemTutari.text()
            _lneOdenen=ui.lneOdenen.text()
            _lneKalan=ui.lneKalan.text()
            
            curs.execute("UPDATE GoldenAura SET Ad=?,Soyad=?,TC=?,Tel=?,DogumTarihi=?,CiltBakimi=?,TirnakBakimi=?,KaliciMakyaj=?,EpilasyonAgda=?,\
                         GuzellikUzmani=?,IslemTutari=?,OdenenTutar=?,KalanTutar=? WHERE Id=?" ,\
                             (_lneAd,_lneSoyad,_lneTCK,_lneTel,_cwDogum,_cbCiltBakimi,  \
                               _cbTirnakBakimi,_cbKaliciMakyaj,_cbEpilasyon,_cbGuzellikUzmani,\
                               _lneIslemTutari,_lneOdenen,_lneKalan,_Id))
                
            conn.commit()
            
        except Exception as Hata:
            ui.statusbar.showMessage("Şöyle Bir Hata Meydana Geldi.."+str(Hata))
                
    else:
        ui.statusbar.showMessage("Güncelleme İptal Edildi ..",10000)
                



def Hakkindaa():
    penHakkinda.show()







  #--------------------SİNYAL SLOT--------------------------------------#

ui.butonKayitEkle.clicked.connect(ekle)
ui.butonListele.clicked.connect(Listele)
ui.butonCikis.clicked.connect(cikis)
ui.butonKayitSil.clicked.connect(Sil)  
ui.butonKayitAra.clicked.connect(Ara) 
ui.Liste.itemSelectionChanged.connect(Doldur)
ui.butonKayitDuzenle.clicked.connect(Guncelle)
ui.MenuHakkinda.triggered.connect(Hakkindaa)

sys.exit(Uygulama.exec_())

