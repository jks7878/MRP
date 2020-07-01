package test;

import Service.CommonService;
import Service.CommonServiceImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class test extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		CommonService comSrv=new CommonServiceImpl();
		comSrv.showWindow(primaryStage, "test.fxml");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
