package Main;

import java.net.URL;
import java.util.ResourceBundle;

import DTO.CommonData;
import DTO.TableRowDataModel;
import Service.CommonService;
import Service.CommonServiceImpl;
import Service.MyRsvService;
import Service.MyRsvServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MyRsvController extends Controller implements Initializable {	
	private Parent root;
	private CommonData cmd;
	private CommonService comSrv;
	private MyRsvService myRsvSrv;
	private int selectedRsv;
		
	@FXML private TableView<TableRowDataModel> reservTable;
	ObservableList<TableRowDataModel> list = FXCollections.<TableRowDataModel>observableArrayList();
	@FXML private TableColumn<TableRowDataModel, Integer> res_noColumn;
	@FXML private TableColumn<TableRowDataModel, String> nameColumn;
	@FXML private TableColumn<TableRowDataModel, String> exhibitColumn;
	@FXML private TableColumn<TableRowDataModel, String> res_dateColumn;
	@FXML private TableColumn<TableRowDataModel, String> res_timeColumn;
	@FXML private TableColumn<TableRowDataModel, String> ticket_selColumn;
	@FXML private TableColumn<TableRowDataModel, Integer> ticketColumn;
	@FXML private TableColumn<TableRowDataModel, Integer> priceColumn;
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cmd = new CommonData();
		comSrv = new CommonServiceImpl();
		myRsvSrv = new MyRsvServiceImpl();
		
		list = myRsvSrv.listMyReservation(cmd.getLoginedId());
		
		res_noColumn.setCellValueFactory(cellData -> cellData.getValue().getRes_no().asObject());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		exhibitColumn.setCellValueFactory(cellData -> cellData.getValue().getExhibit());
		res_dateColumn.setCellValueFactory(cellData -> cellData.getValue().getRes_date());
		res_timeColumn.setCellValueFactory(cellData -> cellData.getValue().getRes_time());
		ticketColumn.setCellValueFactory(cellData -> cellData.getValue().getTicket().asObject());
		ticket_selColumn.setCellValueFactory(cellData -> cellData.getValue().getTicket_sel());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
		reservTable.setItems(list);
		

		reservTable.setOnMouseClicked(e -> {
			if(!reservTable.getSelectionModel().isEmpty()) {
				selectedRsv = reservTable.getSelectionModel().getSelectedItem().getRes_no().getValue().intValue();
			}
		});
		
	}
	
	public void changeRsv() {
		myRsvSrv.changeRsv(selectedRsv);
	}
	
	public void cancelRsv() {
		myRsvSrv.cancleRsv(selectedRsv);
		list = myRsvSrv.listMyReservation(cmd.getLoginedId());
		reservTable.setItems(list);
	}
	
	public void openLastRsvForm() {
		
	}
}
