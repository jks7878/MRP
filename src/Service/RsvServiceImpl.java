package Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import DAO.DBService;
import DAO.DBServiceImpl;
import Main.RsvController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RsvServiceImpl implements RsvService {
	
	private ImageView exImg;
	private Button btnNext;
	private Label lblTitle,lblSelDate,lblSelTime,lblTime; //전시제목,선택날짜,선택시간,회차선택
	private ToggleButton tb1,tb2,tb3,tb4,tb5,tb6,tb7,tb8,tb9,tb10; //시간선택
	private ToggleButton list[]= {tb1,tb2,tb3,tb4,tb5,tb6,tb7,tb8,tb9,tb10};
	
	private Calendar cal=Calendar.getInstance();
	private int today=cal.get(Calendar.DATE);
	private int month=cal.get(Calendar.MONTH)+1;
	
	@Override
	public void dbCheck(Parent root, DBService dbSrv) throws SQLException {
		
	}

	@Override
	public void rsvForm(String exTitle) {
		DBService dbSrv=new DBServiceImpl();
		Stage rsvStage=new Stage();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../FXML/rsv.fxml"));
		Parent root=null;
		try {
			root=loader.load();
		}catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene=new Scene(root);
		RsvController rsvCtrl=loader.getController();
		
		rsvCtrl.setRsvParent(root);
		rsvCtrl.setExTitle(exTitle);
		rsvCtrl.setRsvForm(root);
		setImage(root, exTitle);
		setTitle(root, exTitle);
		
		rsvStage.setTitle("전시 예매");
		rsvStage.setScene(scene);
		rsvStage.show();
	}
	
	@Override
	public void setImage(Parent root, String exTitle) {
		exImg=(ImageView)root.lookup("#exhibition");
		exImg.setImage(new Image(getClass().getResourceAsStream("../img/exhibition/"+exTitle+".jpg")));
	}
	
	@Override
	public void setTitle(Parent root, String exTitle) {
		lblTitle=(Label)root.lookup("#lblTitle");
		if(exTitle.equals("pino")) {
			lblTitle.setText("My Dear 피노키오 전");
		}else if(exTitle.equals("brother")) {
			lblTitle.setText("퀘이 형제 : 도미토리움으로의 초대전");
		}else if(exTitle.equals("photo")) {
			lblTitle.setText("퓰리처상 사진전");
		}
		lblTitle.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"),26));
		
		lblTime=(Label)root.lookup("#lblTime");
		lblTime.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 20));
		
		lblSelDate=(Label)root.lookup("#lblSelDate");
		lblSelTime=(Label)root.lookup("#lblSelTime");
		lblSelDate.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 18));
		lblSelTime.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 18));
		
		btnNext=(Button)root.lookup("#btnNext");
		btnNext.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 20));
		
		for(ToggleButton t:list) {
			for(int i=1;i<=10;i++) {
				t=(ToggleButton)root.lookup("#tb"+i);
				t.setUserData(10+(i-1));
			}
		}
	}

	@Override
	public void rsvFormSet(Parent rsvForm) {
		
	}
	
	@Override
	public void calBeforeBtn(Parent rsvForm) {
		
	}

	@Override
	public void calAfterBtn(Parent rsvForm) {
		
	}

	@Override
	public void selDate(ActionEvent e) {
		
	}

	@Override
	public void selTime(Parent rsvForm,ActionEvent e) {
		lblSelTime=(Label)rsvForm.lookup("#lblSelTime");
		for(ToggleButton t:list) {
			t=(ToggleButton)e.getSource();
//			t.setStyle("-fx-background-color: #ff4b4b");
//			t.setTextFill(Color.WHITE);
			lblSelTime.setText(t.getUserData().toString()+":00");
			return;
		}
	}

	@Override
	public void isSelected(Parent rsvForm) {
		
	}
	
}

