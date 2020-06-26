package Reservation;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Main.Controller;
import Reservation.DAO.DBSrv;
import Reservation.DAO.DBSrvImpl;
import Reservation.Service.RSrv;
import Reservation.Service.RSrvImpl;
import Service.CommonService;
import Service.CommonServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class RController extends Controller implements Initializable {
	
	private Parent root;
	private CommonService comSrv;
	private RSrv rSrv;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comSrv=new CommonServiceImpl();
		rSrv=new RSrvImpl();
	}

	@Override
	public void setRoot(Parent root) {
		this.root=root;
	}
	
	public void cancleProc(ActionEvent e) {
		comSrv.WindowClose(e);
	}
	
	public void nextProc() {
		String id="a";
		DBSrv db=new DBSrvImpl();
		List<String> info=db.selectR(id);
		Reservation r=new Reservation();
		//저장한 객체 전달
		rSrv.nextForm(root, id);
	}
	
	public void payProc() {
		
	}

}
