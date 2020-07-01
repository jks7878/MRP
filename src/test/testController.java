package test;

import java.net.URL;
import java.util.ResourceBundle;

import Main.Controller;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class testController extends Controller implements Initializable {
	
	private Parent root;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@Override
	public void setRoot(Parent root) {
		this.root=root;
	}

}
