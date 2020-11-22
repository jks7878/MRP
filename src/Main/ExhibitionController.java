package Main;

import java.net.URL;
import java.util.ResourceBundle;

import DTO.CommonData;
import Service.CommonService;
import Service.CommonServiceImpl;
import Service.ExhibitionService;
import Service.ExhibitionServiceImpl;
import Service.LoginService;
import Service.LoginServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class ExhibitionController implements Initializable {
	
	private Parent exForm;
	private CommonService comSrv;
	private ExhibitionService exSrv;
	
	static Parent exParent;
	
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
		CommonData cmd=new CommonData();
		LoginService loginSrv=new LoginServiceImpl();
		if(cmd.getLoginedId()==null) {
			if(comSrv.selectErrMsg("로그인 후에 이용하실 수 있습니다\n로그인 하시겠습니까?")) {
				loginSrv.loginForm();
			}
		}else {
			exSrv.openRsvForm(e);
			comSrv.closeWindow(e);
		}
	}

}
