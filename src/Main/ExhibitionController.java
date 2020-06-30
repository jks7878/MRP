package Main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DTO.Exhibition;
import Service.CommonService;
import Service.CommonServiceImpl;
import Service.ExhibitionService;
import Service.ExhibitionServiceImpl;
import Service.RsvService;
import Service.RsvServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class ExhibitionController implements Initializable {
	
	private Parent exForm;
	private CommonService comSrv;
	private ExhibitionService exSrv;
	
	static Parent exParent;
	static String exTitle="";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comSrv=new CommonServiceImpl();
		exSrv=new ExhibitionServiceImpl();
	}
	
	public static Parent getexParent() {
		return exParent;
	}

	public static void setexParent(Parent exParent) {
		ExhibitionController.exParent = exParent;
	}

	public static String getExTitle() {
		return exTitle;
	}

	public static void setExTitle(String exTitle) {
		ExhibitionController.exTitle = exTitle;
	}

	public void setRsvForm(Parent exForm) {
		this.exForm=exForm;
	}
	
	public Parent getexForm() {
		return exForm;
	}
	
	//취소버튼
	public void cancelProc(ActionEvent e) {
		comSrv.closeWindow(e);
	}
	
	//예매버튼
	public void rsvForm(ActionEvent e) {
		exSrv.openRsvForm(e);
	}

}
