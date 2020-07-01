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

public class LoginController extends Controller implements Initializable{
	private Parent root;
	private LoginService loginSrv;
	private CommonService comSrv;
	private JoinService joinSrv;
	
	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginSrv = new LoginServiceImpl();
		comSrv = new CommonServiceImpl();
		joinSrv = new JoinServiceImpl();
	}
	
	//로그인버튼
	public void loginProc() {
		String txtFldArr[] = {"#txtId","#txtPw"};
		Map<String, TextField> txtFldMap = comSrv.getTextFieldInfo(root,txtFldArr);
		if(comSrv.isEmpty(txtFldMap, txtFldArr)) {
			return;
		}
		loginSrv.loginProc(root);
	}
	
	//취소버튼
	public void cancelProc(ActionEvent e) {
		comSrv.closeWindow(e);
	}
	
	//회원가입버튼 > 회원가입창
	public void joinForm() {	
		Parent root = loginSrv.joinForm();
		joinSrv.setFont(root);
	}
}













