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
import javafx.scene.control.ToggleButton;

public class RsvController implements Initializable {
	
	private Parent rsvForm;
	private CommonService comSrv;
	private RsvService rsvSrv;
	
	static Parent rsvParent;

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
	
	//이전 달
	public void calPrevBtn() {
		rsvSrv.calPrevBtn(getRsvParent());
	}
	
	//다음 달
	public void calNextBtn() {
		rsvSrv.calNextBtn(getRsvParent());
	}
	
	//날짜 선택 버튼(토글버튼)
	public void selDate(ActionEvent e) {
		rsvSrv.selDate(rsvForm, e);
	}
	
	//시간 선택 버튼(토글 버튼)
	public void selTime(ActionEvent e) {
		rsvSrv.selTime(rsvForm, e);
	}
	
	//누락값 확인
	public void rsvNext(ActionEvent e) {
		if(!rsvSrv.isSelected(rsvForm)) {
			return;
		}
		rsvSrv.rsvNext(e);
	}
	
}
