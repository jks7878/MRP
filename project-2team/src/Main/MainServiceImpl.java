package Main;

import Service.CommonService;
import Service.CommonServiceImpl;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class MainServiceImpl implements MainService{
	
	@Override
	public Parent openLoginForm() {
		// TODO Auto-generated method stub
		CommonService comSrv = new CommonServiceImpl();
		Stage loginForm = new Stage();
		Parent form = comSrv.showWindow(loginForm, "../FXML/login.fxml");
		
		return form;	
	}
	
}
