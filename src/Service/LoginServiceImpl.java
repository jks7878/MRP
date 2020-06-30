package Service;

import DAO.DBService;
import DAO.DBServiceImpl;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginServiceImpl implements LoginService{

	@Override
	public void setFont(Parent root) {
		Button login = (Button)root.lookup("#btnLogin");
		login.setFont(Font.loadFont(getClass().getResourceAsStream("../font/AppleSDGothicNeoR.ttf"), 16));
	}
	
	@Override
	public boolean loginProc(Parent root) {
		CommonService comSrv = new CommonServiceImpl();
		TextField txtId = (TextField)root.lookup("#txtId");
		PasswordField txtPw = (PasswordField)root.lookup("#txtPw");

		DBService db = new DBServiceImpl();
		boolean login = db.select(txtId.getText(), txtPw.getText());		
		if(login) {
			comSrv.errorMsg("확인","로그인 되었습니다");
			return true;
		}else {
			comSrv.errorMsg("아이디 혹은 비밀번호가 틀렸습니다");
			return false;
		}	
	}
	
	@Override
	public Parent joinForm() {
		CommonService comSrv = new CommonServiceImpl();
		Stage joinForm = new Stage();
		Parent form = comSrv.showWindow(joinForm, "../FXML/join.fxml");
		return form;
	}

}












