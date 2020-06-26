package Main;

import java.io.IOException;

import Service.CommonService;
import Service.CommonServiceImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		CommonService comSrv = new CommonServiceImpl();
		comSrv.showWindow(primaryStage, "../FXML/main.fxml");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
