package Service;

import java.io.IOException;

import Main.ExhibitionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ExhibitionServiceImpl implements ExhibitionService {
   
   private ImageView exImg;
   private Button btnRsv;
   
   @Override
   public void exhibitionForm(String exTitle) {
      Stage exStage=new Stage();
      FXMLLoader loader=new FXMLLoader(getClass().getResource("../FXML/exhibition.fxml"));
      Parent root=null;
      try {
         root=loader.load();
      } catch(IOException e) {
         e.printStackTrace();
      }
      Scene scene=new Scene(root);
      ExhibitionController ctrl=loader.getController();
      ctrl.setexParent(root);
      
      ExhibitionController exCtrl=new ExhibitionController();
      exCtrl.setExTitle(exTitle);
      
      exImg=(ImageView)root.lookup("#exhibition");
      exImg.setImage(new Image(getClass().getResourceAsStream("../img/exhibition/"+exTitle+"_info.jpg")));
      
      btnRsv=(Button)root.lookup("#btnRsv");
      btnRsv.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 20));
      
      exStage.setTitle("전시 정보");
      exStage.setScene(scene);
      exStage.show();
   }

   @Override
   public void openRsvForm(ActionEvent e) {
      ExhibitionController exCtrl=new ExhibitionController();
      RsvService rsvSrv=new RsvServiceImpl();
      rsvSrv.rsvForm(exCtrl.getExTitle());
   }
   
}
