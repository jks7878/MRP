package Service;

import java.io.IOException;

import DAO.DBService;
import DAO.DBServiceImpl;
import DTO.CommonData;
import Main.LoginController;
import Main.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginServiceImpl implements LoginService{
	
	 CommonService comSrv = new CommonServiceImpl();
	 private CommonData cmd = new CommonData();
	
	@Override
	public void loginForm() {
		Stage loginStage=new Stage();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../FXML/login.fxml"));
		Parent root=null;
		try {
			root=loader.load();
		}catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene=new Scene(root);
		LoginController loginCtrl=loader.getController();
		
		loginCtrl.setLoginParent(root);
		loginCtrl.setLoginForm(root);
		setting(root);
		
		loginStage.setTitle("로그인");
		loginStage.setScene(scene);
		loginStage.show();
	}
	
	public void setting(Parent loginForm) {
		Button login = (Button)loginForm.lookup("#btnLogin");
		login.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 16));
	}
	
	//로그인 버튼
	@Override
	   public void loginProc(Parent loginForm, ActionEvent e) {
	      TextField txtId = (TextField)loginForm.lookup("#txtId");
	      PasswordField txtPw = (PasswordField)loginForm.lookup("#txtPw");
	   
	      DBService db = new DBServiceImpl();
	      boolean login = db.select(txtId.getText(), txtPw.getText());      
	      if(login) {
	         cmd.setLoginedId(txtId.getText());
	         comSrv.errorMsg("확인","로그인 되었습니다");
	         
	         //메인화면 로그인 중인 아이디 표시, 로그인 버튼 > 로그아웃 버튼
	         MainController mainCtrl=new MainController();
	         Parent mainForm=mainCtrl.getMainParent();
	         Label lblId=(Label)mainForm.lookup("#lblId");
	         lblId.setText(cmd.getLoginedId()+" 님");
	         ImageView log=(ImageView)mainForm.lookup("#btnLogin");
	         log.setImage(new Image(getClass().getResourceAsStream("../img/main/btn_logout.png")));
	         
	         comSrv.closeWindow(e);
	      }else {
	         comSrv.errorMsg("아이디 혹은 비밀번호가 틀렸습니다");
	      }   
	   }

}












