package Service;

import java.sql.SQLException;

import DAO.DBService;
import javafx.scene.Parent;

public interface RsvService {
	public void dbCheck(Parent root, DBService dbSrv) throws SQLException;
	public void rsvForm(String exTitle);
}
