package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButonText","İptal");
		UIManager.put("OptionPane.noButonText","Hayır");
		UIManager.put("OptionPane.okButonText","Tamam");
		UIManager.put("OptionPane.yesButonText","Evet");
	}
	
public static void showMsg(String str) {
	
	String msg;
	
	optionPaneChangeButtonText();
	
	switch(str) {
	
	case "fill":
		msg="Lütfen tüm alanları doldurunuz ! ";
		break;
		
	case "success":
		msg="İşlem başarılı..";
		
		break;
	case "error":
		msg="Bir hata gerçekleşti !";
		break;
		
		default:
			msg=str;
			break;
	
	}
	
	JOptionPane.showMessageDialog(null, msg,"Mesaj ",JOptionPane.INFORMATION_MESSAGE);
	
}	

public static boolean confirm(String str) {
	
	String msg;
	switch(str) {
	case "sure":
		msg="Bu işlemi gerçekleştirmek istediğinize emin misiniz ?";
	break;
	default :
		msg=str;
		break;
		
}
	int res=JOptionPane.showConfirmDialog(null, msg,"Dikkat !",JOptionPane.YES_NO_OPTION);
	
	if(res==0) {
		return true;
	}else {
		return false;
	}
	}



}
