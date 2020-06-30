package Service;

import java.io.IOException;
import java.sql.SQLException;

import DAO.DBService;
import DAO.DBServiceImpl;
import Main.RsvController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RsvServiceImpl implements RsvService {
	
	private ImageView exImg;
	private Button btnNext;
	
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
		
		exImg=(ImageView)root.lookup("#exhibition");
		exImg.setImage(new Image(getClass().getResourceAsStream("../img/exhibition/"+exTitle+".jpg")));
		
		btnNext=(Button)root.lookup("#btnNext");
		btnNext.setFont(Font.loadFont(getClass().getResourceAsStream("../font/AppleSDGothicNeoB.ttf"), 24));
		
		rsvStage.setTitle("전시 예매");
		rsvStage.setScene(scene);
		rsvStage.show();
	}
	
}

