package Main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Rsv2Controller implements Initializable{

	private boolean isDisable;	//비활성화
	@FXML private Button Payment;	//결제하기 버튼
	@FXML private CheckBox Agreement; //이용자 약관 동의
	@FXML private Label SumPrice; //합계 레이블
	@FXML private Label Price;	//기본요금 레이블
	int sum = 0;	//관람권 총 가격 저장 변수
	@FXML private Button AdultMinusBtn; //성인 '-' 버튼
	@FXML private Button AdultPlusBtn; //성인 '+' 버튼
	@FXML private Label AdultSum; //성인 관람권 총 갯수 레이블
	@FXML private Label AdultSumPrice; //성인 관람권 총 가격 레이블
	int AdultPrice = 13900; //성인 관람권 가격 
	int Acount = 0;	//성인 관람권 갯수 카운트
	int Atmp = 0; //성인 관람권 총 가격 저장 변수
	
	@FXML private Button YouthMinusBtn; //청소년 '-' 버튼
	@FXML private Button YouthPlusBtn; //청소년 '+' 버튼
	@FXML private Label YouthSum; //청소년 관람권 총 갯수 레이블
	@FXML private Label YouthSumPrice; //청소년 관람권 총 가격 레이블
	int YouthPrice = 11900; //청소년 관람권 가격 
	int Ycount = 0;	//성인 관람권 갯수 카운트
	int Ytmp = 0;	//성인 관람권 총 가격 저장 변수
	
	@FXML private Button ChildMinusBtn;	//어린이 '-' 버튼
	@FXML private Button ChildPlusBtn;	//어린이 '+' 버튼
	@FXML private Label ChildSum;	//어린이 관람권 총 갯수 레이블
	@FXML private Label ChildSumPrice;	//어린이 관람권 총 가격 레이블
	int ChildPrice = 8900;	//어린이 관람권 가격
	int Ccount = 0;	//어린이 관람권 갯수 카운트
	int Ctmp = 0;	//어린이 관람권 총 가격 저장 변수
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		isDisable = false;
		
		SumPrice.setText("0원");
		Price.setText("0원");
		
		AdultSum.setText("0"); 
		AdultSumPrice.setText("0원");
		AdultMinusBtn.setDisable(!isDisable);
		
		//성인 '+'버튼
		AdultPlusBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(Acount + Ycount + Ccount < 5) {
					if(Acount < 5) {
					Acount++;
				AdultSum.setText(Acount+"");
				Atmp += 13900;
				AdultSumPrice.setText(Atmp+"원");
				sum += 13900;
				SumPrice.setText(sum+"원");
				Price.setText(sum+"원");
				}
				AdultMinusBtn.setDisable(isDisable);
				if(Acount + Ycount + Ccount == 5) {
					AdultPlusBtn.setDisable(true);
					YouthPlusBtn.setDisable(true);
					ChildPlusBtn.setDisable(true);
				}
				}
			}
		});
		//성인 '-'버튼
		AdultMinusBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(Ycount < 5 || Ccount < 5) {
					YouthPlusBtn.setDisable(false);
					ChildPlusBtn.setDisable(false);
				}
				
				if(Acount > 0){
					AdultPlusBtn.setDisable(false);	
					Acount--;
					AdultSum.setText(Acount+"");
					Atmp -= 13900;
					AdultSumPrice.setText(Atmp+"원");
					sum -= 13900;
					SumPrice.setText(sum+"원");
					Price.setText(sum+"원");
				}if(Acount ==0) {
					AdultMinusBtn.setDisable(true);
				}
			}
		});	
		
		YouthSum.setText("0");
	YouthSumPrice.setText("0원");
	YouthMinusBtn.setDisable(true);
	
	//청소년 '+'버튼
	YouthPlusBtn.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			
			if(Acount + Ycount + Ccount < 5) {
			if(Ycount < 5) {
					YouthPlusBtn.setDisable(false);
					Ycount++;
			YouthSum.setText(Ycount+"");
			Ytmp += 11900;
			YouthSumPrice.setText(Ytmp+"원");
			sum += 11900;
			SumPrice.setText(sum+"원");
			Price.setText(sum+"원");
			YouthMinusBtn.setDisable(isDisable);
			}	
			}
			if(Acount + Ycount + Ccount == 5) {
				AdultPlusBtn.setDisable(true);
				YouthPlusBtn.setDisable(true);
				ChildPlusBtn.setDisable(true);
			}
		}
	});
	//청소년 '-'버튼
	YouthMinusBtn.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			if(Acount < 5 || Ccount < 5) {
				AdultPlusBtn.setDisable(false);
				ChildPlusBtn.setDisable(false);
			}
			if(Ycount > 0){
				YouthPlusBtn.setDisable(false);	
				Ycount--;
				YouthSum.setText(Ycount+"");
				Ytmp -= 11900;
				YouthSumPrice.setText(Ytmp+"원");
				sum -= 11900;	
				SumPrice.setText(sum+"원");
				Price.setText(sum+"원");
			}if(Ycount == 0) {
				YouthMinusBtn.setDisable(true);
			}	
		}
	});
	
	ChildSum.setText("0");
	ChildSumPrice.setText("0원");
	ChildMinusBtn.setDisable(true);
	
	//어린이 '+'버튼
	ChildPlusBtn.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			
			if(Acount + Ycount + Ccount < 5) {
				if(Ccount < 5) {
				Ccount++;
				ChildSum.setText(Ccount+"");
			Ctmp += 8900;
			ChildSumPrice.setText(Ctmp+"원");
			sum += 8900;	
			SumPrice.setText(sum+"원");
			Price.setText(sum+"원");
			ChildMinusBtn.setDisable(false);
				}
			}if(Acount + Ycount + Ccount == 5) {
				AdultPlusBtn.setDisable(true);
				YouthPlusBtn.setDisable(true);
				ChildPlusBtn.setDisable(true);
			}
		}
	});
	//어린이 '-'버튼
	ChildMinusBtn.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			if(Acount < 5 || Ycount < 5) {
				YouthPlusBtn.setDisable(false);
				AdultPlusBtn.setDisable(false);
			}
			
			if(Ccount > 0){
				ChildPlusBtn.setDisable(false);	
				Ccount--;
				ChildSum.setText(Ccount+"");
				Ctmp -= 8900;
				ChildSumPrice.setText(Ctmp+"원");	
				sum -= 8900;
				SumPrice.setText(sum+"원");
				Price.setText(sum+"원");
			}if(Ccount == 0) {
				ChildMinusBtn.setDisable(true);
			}	
		}
	});
	//결제하기 버튼
	Payment.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
		if(Acount + Ycount + Ccount == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("error");
			alert.setHeaderText("error");
			alert.setContentText("예매할 가격/수량을 선택해 주세요..");		
			alert.showAndWait();
		}
		else if(Agreement.isSelected()) {
			Parent root = (Parent)e.getSource();
			Stage stage = (Stage)root.getScene().getWindow();
			stage.close();
		}
		
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("error");
			alert.setHeaderText("error");
			alert.setContentText("이용 약관에 동의해 주세요.");		
			alert.showAndWait();
			Agreement.requestFocus();
			}
		}
	});
	
	
	
	
	
	
	
	
	
	}
}