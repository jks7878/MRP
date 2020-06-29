package Service;

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
	public void loginProc(Parent root) {
		CommonService comSrv = new CommonServiceImpl();
		TextField txtId = (TextField)root.lookup("#txtId");
		PasswordField txtPw = (PasswordField)root.lookup("#txtPw");

//		DataBaseService db = new DatabaseServiceImpl();
//		boolean login = db.select(idTxt.getText(), pwTxt.getText());		
//		if(login) {
//			comSrv.errorMsg("확인","로그인 되었습니다");
//			System.out.println("ID : " + idTxt.getText());
//			System.out.println("PW : " + pwTxt.getText());
//		}else {
//			comSrv.errorMsg("아이디 혹은 비밀번호가 틀렸습니다");
//		}
		if(txtId.getText().equals("test") && txtPw.getText().equals("1234")) {	
			System.out.println("임시 로그인 성공");
			Stage s = (Stage)root.getScene().getWindow();
			s.close();
		}else {
			System.out.println("test "+"1234");
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












