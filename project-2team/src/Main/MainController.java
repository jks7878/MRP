package Main;

import java.net.URL;
import java.util.ResourceBundle;

import Service.CommonService;
import Service.CommonServiceImpl;
import Service.LoginService;
import Service.LoginServiceImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class MainController extends Controller implements Initializable{
	private Parent root;
	private CommonService comSrv;
	private MainService mainSrv;
	private LoginService loginSrv;
	private String loginedId;
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comSrv = new CommonServiceImpl();
		mainSrv = new MainServiceImpl();
		loginSrv = new LoginServiceImpl();
	}
	
	public void endProc(ActionEvent event) {
		if(((CommonServiceImpl) comSrv).cancelMsg("종료하시겠습니까?")) {
			Platform.exit();
		}else {
			return;
		}			
	}
	
	public void openLoginForm() {	
		if(loginedId != null){
			System.out.println("로그인 중인 사용자가 있습니다");
		}else {
			Parent root = mainSrv.openLoginForm();
			TextField idTxt = (TextField)root.lookup("#idTxt");
			loginedId = idTxt.getText();
		}
	}
}
