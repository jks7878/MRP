package Main;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import Membership.MembershipService;
import Membership.MembershipServiceImpl;
import Service.CommonService;
import Service.CommonServiceImpl;
import Service.LoginService;
import Service.LoginServiceImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginController extends Controller implements Initializable{
	private Parent root;
	private LoginService loginSrv;
	private CommonService comSrv;
	private MembershipService memberSrv;
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginSrv = new LoginServiceImpl();
		comSrv = new CommonServiceImpl();
		memberSrv = new MembershipServiceImpl();
	}
	
	public void LoginProc() {
		String txtFldArr[] = {"#idTxt","#pwTxt"};
		String list[] = {"아이디", "비밀번호"}; 
		Map<String, TextField> txtFldMap = comSrv.getTextFieldInfo(root,txtFldArr);

		if(comSrv.isEmpty(txtFldMap, txtFldArr, list)) {
			return;
		}
		loginSrv.LoginProc(root);
	}
	
	public void cancelProc(ActionEvent event) {
		comSrv.WindowClose(event);
	}
	
	public void openMembershipForm() {	
		Parent root = loginSrv.openMembershipForm();
	}
}













