package Main;

import java.net.URL;
import java.util.ResourceBundle;

import Service.CommonService;
import Service.CommonServiceImpl;
import Service.RsvService;
import Service.RsvServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class RsvController implements Initializable {
	
	private Parent rsvForm;
	private CommonService comSrv;
	private RsvService rsvSrv;
	
	static Parent rsvParent;
	static String exTitle="";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comSrv=new CommonServiceImpl();
		rsvSrv=new RsvServiceImpl();
	}
	
	public static Parent getRsvParent() {
		return rsvParent;
	}
	
	public static void setRsvParent(Parent rsvParent) {
		RsvController.rsvParent = rsvParent;
	}

	public static String getExTitle() {
		return exTitle;
	}

	public static void setExTitle(String exTitle) {
		RsvController.exTitle = exTitle;
	}	

	public Parent getRsvForm() {
		return rsvForm;
	}

	public void setRsvForm(Parent rsvForm) {
		this.rsvForm = rsvForm;
	}

	//취소버튼
	public void cancelProc(ActionEvent e) {
		comSrv.closeWindow(e);
	}
	
	//예매 초기세팅
	public void rsvFormSet() {
//		rsvSrv.rsvFormSet(rsvForm);
	}
	
	//이전 달
	
	//다음 달
	
	//날짜 선택 버튼(토글버튼)
	
	//시간 선택 버튼(토글 버튼)
	
	//누락값 확인
}
