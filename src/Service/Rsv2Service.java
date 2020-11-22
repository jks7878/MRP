package Service;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;

public interface Rsv2Service {
	public void rsv2Form(Stage rsvStage);
	public void adultPlus(Parent rsv2Form, ActionEvent e);
	public void adultMinus(Parent rsv2Form, ActionEvent e);
	public void youthPlus(Parent rsv2Form, ActionEvent e);
	public void youthMinus(Parent rsv2Form, ActionEvent e);
	public void childPlus(Parent rsv2Form, ActionEvent e);
	public void childMinus(Parent rsv2Form, ActionEvent e);
	public void payment(Parent rsv2Form, ActionEvent e) throws SQLException;
}