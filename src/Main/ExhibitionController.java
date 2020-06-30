package Main;

import java.net.URL;
import java.util.ResourceBundle;

import Service.CommonService;
import Service.CommonServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class ExhibitionController extends Controller implements Initializable {
	
	private Parent root;
	private CommonService comSrv;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comSrv=new CommonServiceImpl();
	}

	@Override
	public void setRoot(Parent root) {
		this.root=root;
	}

}
