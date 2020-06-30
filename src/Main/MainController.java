package Main;

import java.net.URL;
import java.util.ResourceBundle;

import DTO.CommonData;
import Service.CommonService;
import Service.CommonServiceImpl;
import Service.LoginService;
import Service.LoginServiceImpl;
import Service.MainService;
import Service.MainServiceImpl;
import Service.MyRsvService;
import Service.MyRsvServiceImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController extends Controller implements Initializable{	
	private Parent root;
	private CommonData cmd;
	private CommonService comSrv;
	private MainService mainSrv;
	private LoginService loginSrv;
	private MyRsvService myRsvSrv;
	
	public void setRoot(Parent root) {
		this.root = root;		
		mainSrv.setFont(root);
		mainSrv.setToday(root);
		mainSrv.setDday(root);
		Label tmp = (Label)root.lookup("#today");
		cmd.setToday(tmp.getText());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		cmd = new CommonData();
		comSrv = new CommonServiceImpl();
		mainSrv = new MainServiceImpl();
		loginSrv = new LoginServiceImpl();
		myRsvSrv = new MyRsvServiceImpl();
	}
	
	public void loginForm() {
		if(cmd.getLoginedId() != null) {
			System.out.println("로그인 중인 사용자가 있습니다");
			return;
		}
		Parent root = mainSrv.loginForm();	
		TextField txtId = (TextField)root.lookup("#txtId");
		Button login = (Button)root.lookup("#btnLogin");
		loginSrv.setFont(root);
		login.setOnAction(e -> {
			if(loginSrv.loginProc(root)) {
				cmd.setLoginedId(txtId.getText());	
				System.out.println("현재 로그인 중인 계정 : " + cmd.getLoginedId());
				Stage s = (Stage) root.getScene().getWindow();
				s.close();
			}else {
				System.out.println("현재 로그인 중인 계정 : " + cmd.getLoginedId());
			}
	    });
	}

	public void exhibitionForm(MouseEvent e) {
		mainSrv.selExhibition(e);
	}
	
	public void myRsvForm() {
		if(cmd.getLoginedId() == null) {
			if(comSrv.selectErrMsg("로그인 후에 이용하실 수 있습니다\n로그인 하시겠습니까?")) {
				loginForm();
			}
		}else {
			Parent root = mainSrv.myRsvForm();			
		}		
	}
	
	public void endProc(ActionEvent event) {
		if(comSrv.selectErrMsg("종료하시겠습니까?")) {
			Platform.exit();
		}else {
			return;
		}			
	}
	
}
