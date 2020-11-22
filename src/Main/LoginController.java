package Main;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import Service.CommonService;
import Service.CommonServiceImpl;
import Service.LoginService;
import Service.LoginServiceImpl;
import Service.JoinService;
import Service.JoinServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{
	
	private Parent loginForm;
	private LoginService loginSrv;
	private CommonService comSrv;
	private JoinService joinSrv;
	
	static Parent loginParent;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginSrv = new LoginServiceImpl();
		comSrv = new CommonServiceImpl();
		joinSrv = new JoinServiceImpl();
	}
	
	public static Parent getLoginParent() {
		return loginParent;
	}

	public static void setLoginParent(Parent loginParent) {
		LoginController.loginParent = loginParent;
	}

	public Parent getLoginForm() {
		return loginForm;
	}

	public void setLoginForm(Parent loginForm) {
		this.loginForm = loginForm;
	}

	//로그인버튼
	public void loginProc(ActionEvent e) {
		String txtFldArr[] = {"#txtId","#txtPw"};
		Map<String, TextField> txtFldMap = comSrv.getTextFieldInfo(loginForm,txtFldArr);
		if(comSrv.isEmpty(txtFldMap, txtFldArr)) {
			return;
		}
		loginSrv.loginProc(loginForm, e);
	}
	
	//취소버튼
	public void cancelProc(ActionEvent e) {
		comSrv.closeWindow(e);
	}
	
	//회원가입버튼 > 회원가입창
	public void joinForm() {	
		joinSrv.joinForm();
	}
}













