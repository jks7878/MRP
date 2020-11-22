package Main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Service.CommonService;
import Service.CommonServiceImpl;
import Service.Rsv2Service;
import Service.Rsv2ServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class Rsv2Controller implements Initializable {

	private Parent rsv2Form;
	private CommonService comSrv;
	private Rsv2Service rsv2Srv;

	static Parent rsv2Parent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comSrv = new CommonServiceImpl();
		rsv2Srv = new Rsv2ServiceImpl();
	}

	public static Parent getRsv2Parent() {
		return rsv2Parent;
	}

	public static void setRsv2Parent(Parent rsv2Parent) {
		Rsv2Controller.rsv2Parent = rsv2Parent;
	}

	public Parent getRsv2Form() {
		return rsv2Form;
	}

	public void setRsv2Form(Parent rsv2Form) {
		this.rsv2Form = rsv2Form;
	}

	public void adultPlus(ActionEvent e) {
		rsv2Srv.adultPlus(rsv2Form, e);
	}

	public void adultMinus(ActionEvent e) {
		rsv2Srv.adultMinus(rsv2Form, e);
	}

	public void youthPlus(ActionEvent e) {
		rsv2Srv.youthPlus(rsv2Form, e);
	}

	public void youthMinus(ActionEvent e) {
		rsv2Srv.youthMinus(rsv2Form, e);
	}

	public void childPlus(ActionEvent e) {
		rsv2Srv.childPlus(rsv2Form, e);
	}

	public void childMinus(ActionEvent e) {
		rsv2Srv.childMinus(rsv2Form, e);
	}

	public void payment(ActionEvent e) throws SQLException {
		rsv2Srv.payment(rsv2Form, e);
	}
}