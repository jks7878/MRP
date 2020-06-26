package Main;

import java.net.URL;
import java.util.ResourceBundle;

import Service.CommonService;
import Service.CommonServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class MainController extends Controller implements Initializable{
	private Parent root;
	private CommonService comSrv;
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comSrv = new CommonServiceImpl();
	}

}
