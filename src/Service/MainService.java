package Service;

import javafx.scene.Parent;

public interface MainService {
	public void setFont(Parent root);
	public void setToday(Parent root);
	public void setDday(Parent root);
	public Parent loginForm();
	public Parent exhibitionForm();
	public Parent myRsvForm();
}
