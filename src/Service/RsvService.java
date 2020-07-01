package Service;

import java.sql.SQLException;

import DAO.DBService;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

public interface RsvService {
	public void dbCheck(Parent root, DBService dbSrv) throws SQLException;
	public void rsvForm(String exTitle);
	public void setTitle(Parent rsvForm, String exTitle);
	public void setImage(Parent rsvForm, String exTitle);
	public void rsvFormSet(Parent rsvForm);
	public void calBeforeBtn(Parent rsvForm);
	public void calAfterBtn(Parent rsvForm);
	public void selDate(ActionEvent e);
	public void selTime(Parent rsvForm,ActionEvent e);
	public void isSelected(Parent rsvForm);
}
