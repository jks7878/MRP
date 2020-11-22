package Service;

import javafx.event.ActionEvent;
import javafx.scene.Parent;

public interface RsvService {
	public void rsvForm();
	public void calPrevBtn(Parent rsvForm);
	public void calNextBtn(Parent rsvForm);
	public void selDate(Parent rsvForm, ActionEvent e);
	public void selTime(Parent rsvForm, ActionEvent e);
	public boolean isSelected(Parent rsvForm);
	public void rsvNext(ActionEvent e);
}
