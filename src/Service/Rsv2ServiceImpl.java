package Service;

import java.io.IOException;
import java.sql.SQLException;

import DAO.DBService;
import DAO.DBServiceImpl;
import DTO.CommonData;
import DTO.Reservation;
import Main.Rsv2Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Rsv2ServiceImpl implements Rsv2Service {

	private CommonService comsrv=new CommonServiceImpl();
	private CommonData cmd=new CommonData();
	
	private ImageView exImg,won;
	private Button adultm, adultp, youthm, youthp, childm, childp, Payment;
	private Label sumPrice, aNumber, yNumber, cNumber;
	private Label title, lblSelDate, lblSelTime, lblInfo, lblTicket, lblSel, ticket, resInfo;
	private Label name, tel, email;
	private CheckBox agreement;

	int sum = 0; // 관람권 총 가격 저장 변수
	int aPrice = 13900; // 성인 관람권 가격
	int aCount = 0; // 성인 관람권 갯수 카운트
	int yPrice = 11900; // 청소년 관람권 가격
	int yCount = 0; // 청소년 관람권 갯수 카운트
	int cPrice = 8900; // 어린이 관람권 가격
	int cCount = 0; // 어린이 관람권 갯수 카운트
	static String strA,strY,strC=null;
	static int tsum=0;
	String s= cmd.getExDate()+", "+cmd.getExTime();

	@Override
	public void rsv2Form(Stage rsvStage) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/rsv2.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		Rsv2Controller rsv2Ctrl = loader.getController();

		rsv2Ctrl.setRsv2Parent(root);
		rsv2Ctrl.setRsv2Form(root);
		setting(root);

		rsvStage.setTitle("전시 예매");
		rsvStage.setScene(scene);
		rsvStage.show();
	}

	public void set(Parent rsv2Form) {
		exImg=(ImageView)rsv2Form.lookup("#exhibition");
		title=(Label)rsv2Form.lookup("#title");
		lblSelDate=(Label)rsv2Form.lookup("#lblSelDate");
		lblSelTime=(Label)rsv2Form.lookup("#lblSelTime");
		won=(ImageView)rsv2Form.lookup("#won");
		lblTicket=(Label)rsv2Form.lookup("#lblTicket");
		lblSel=(Label)rsv2Form.lookup("#lblSel");
		lblInfo=(Label)rsv2Form.lookup("#lblInfo");
		
		ticket=(Label)rsv2Form.lookup("#ticket");
		name=(Label)rsv2Form.lookup("#name");
		tel=(Label)rsv2Form.lookup("#tel");
		email=(Label)rsv2Form.lookup("#email");
		resInfo=(Label)rsv2Form.lookup("#resInfo");
		
		adultm=(Button)rsv2Form.lookup("#AdultMinusBtn");
		adultp = (Button) rsv2Form.lookup("#AdultPlusBtn");
		youthm = (Button) rsv2Form.lookup("#YouthMinusBtn");
		youthp = (Button) rsv2Form.lookup("#YouthPlusBtn");
		childm = (Button) rsv2Form.lookup("#ChildMinusBtn");
		childp = (Button) rsv2Form.lookup("#ChildPlusBtn");
		
		aNumber = (Label) rsv2Form.lookup("#ANumber");
		yNumber = (Label) rsv2Form.lookup("#YNumber");
		cNumber = (Label) rsv2Form.lookup("#CNumber");
		sumPrice = (Label) rsv2Form.lookup("#SumPrice");
		
		agreement=(CheckBox)rsv2Form.lookup("#Agreement");
		Payment=(Button)rsv2Form.lookup("#Payment");
	}
	
	public void setting(Parent rsv2Form) {
		set(rsv2Form);
		String exTitle=cmd.getExTitle();
		exImg.setImage(new Image(getClass().getResourceAsStream("../img/exhibition/"+exTitle+".jpg")));
		won.setImage(new Image(getClass().getResourceAsStream("../img/rsv/won.png")));
		
		if(exTitle.equals("pino")) {
			title.setText("My Dear 피노키오 전");
		}else if(exTitle.equals("brother")) {
			title.setText("퀘이 형제 : 도미토리움으로의 초대전");
		}else if(exTitle.equals("photo")) {
			title.setText("퓰리처상 사진전");
		}
		title.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"),26));
		lblSelDate.setText(cmd.getExDate());
		lblSelTime.setText(cmd.getExTime());
		lblSelDate.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 18));
		lblSelTime.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 18));
		lblTicket.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 18));
		lblSel.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 18));
		lblInfo.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 18));
		Payment.setFont(Font.loadFont(getClass().getResourceAsStream("../font/나눔고딕Bold.ttf"), 20));
		
		sumPrice.setText("0");
		aNumber.setText("0");
		yNumber.setText("0");
		cNumber.setText("0");
		
		adultm.setDisable(true);
		youthm.setDisable(true);
		childm.setDisable(true);
		
		resInfo.setText(s+", "+sum+"매");
		ticket.setText("");
		
		DBService dbSrv=new DBServiceImpl();
		String loginedIdInfo[]=dbSrv.selectLId(cmd.getLoginedId());
		name.setText(loginedIdInfo[0]);
		tel.setText(loginedIdInfo[1]);
		email.setText(loginedIdInfo[2]);
	}
	
	//성인 '-'버튼
	@Override
	public void adultMinus(Parent rsv2Form, ActionEvent e) {
		set(rsv2Form);
		if (yCount < 5 || cCount < 5) {
			youthp.setDisable(false);
			childp.setDisable(false);
		}
		if (aCount > 0) {
			adultp.setDisable(false);
			aCount--;
			aNumber.setText(aCount + "");
			sum -= 13900;
			sumPrice.setText(sum + "");
			
			strA = "성인" + "("+aCount+")";
			strSet(strA,strY,strC,rsv2Form);
		}
		if (aCount == 0) {
			adultm.setDisable(true);
			strA = null;
			strSet(strA,strY,strC,rsv2Form);
		}
		resInfo.setText(s+", "+getTCount(rsv2Form)+"매");
	}

	//성인 '+'버튼
	@Override
	public void adultPlus(Parent rsv2Form, ActionEvent e) {
		set(rsv2Form);
		if (aCount + yCount + cCount < 5) {
			if (aCount < 5) {
				aCount++;
				aNumber.setText(aCount + "");
				sum += 13900;
				sumPrice.setText(sum+"");
				
				strA = "성인" + "("+aCount+")";
				strSet(strA,strY,strC,rsv2Form);
			}
			adultm.setDisable(false);
			if (aCount + yCount + cCount == 5) {
				adultp.setDisable(true);
				youthp.setDisable(true);
				childp.setDisable(true);
			}
		}
		resInfo.setText(s+", "+getTCount(rsv2Form)+"매");
	}
	
	//청소년 '-'버튼
	@Override
	public void youthMinus(Parent rsv2Form, ActionEvent e) {
		set(rsv2Form);
		if (aCount < 5 || cCount < 5) {
			childp.setDisable(false);
			adultp.setDisable(false);
		}
		if (yCount > 0) {
			youthp.setDisable(false);
			yCount--;
			yNumber.setText(yCount + "");
			sum -= 11900;
			sumPrice.setText(sum + "");
			
			strY = "청소년" + "("+yCount+")";
			strSet(strY,strA,strC,rsv2Form);
		}
		if (yCount == 0) {
			youthm.setDisable(true);
			
			strY = null;
			strSet(strY,strA,strC,rsv2Form);
		}
		resInfo.setText(s+", "+getTCount(rsv2Form)+"매");
	}

	//청소년 '+'버튼
	@Override
	public void youthPlus(Parent rsv2Form, ActionEvent e) {
		set(rsv2Form);
		if (aCount + yCount + cCount < 5) {
			if (yCount < 5) {
				youthm.setDisable(false);
				yCount++;
				yNumber.setText(yCount + "");
				sum += 11900;
				sumPrice.setText(sum+"");
				youthp.setDisable(false);
				
				strY = "청소년" + "("+yCount+")";
				strSet(strY,strA,strC,rsv2Form);
			}
		}
		if (aCount + yCount + cCount == 5) {
			adultp.setDisable(true);
			youthp.setDisable(true);
			childp.setDisable(true);
		}
		resInfo.setText(s+", "+getTCount(rsv2Form)+"매");
	}

	//어린이 '-'버튼
	@Override
	public void childMinus(Parent rsv2Form, ActionEvent e) {
		set(rsv2Form);
		if (aCount < 5 || yCount < 5) {
			youthp.setDisable(false);
			adultp.setDisable(false);
		}
		if (cCount > 0) {
			childp.setDisable(false);
			cCount--;
			cNumber.setText(cCount + "");
			sum -= 8900;
			sumPrice.setText(sum + "");
			
			strC = "어린이" + "("+cCount+")";
			strSet(strC,strA,strY,rsv2Form);
		}
		if (cCount == 0) {
			childm.setDisable(true);
			
			strC = null;
			strSet(strC,strA,strY,rsv2Form);
		}
		resInfo.setText(s+", "+getTCount(rsv2Form)+"매");
		
	}

	//어린이 '+'버튼
	@Override
	public void childPlus(Parent rsv2Form, ActionEvent e) {
		set(rsv2Form);
		if (aCount + yCount + cCount < 5) {
			if (cCount < 5) {
				cCount++;
				cNumber.setText(cCount + "");
				sum += 8900;
				sumPrice.setText(sum+"");
				childm.setDisable(false);
				
				strC = "어린이" + "("+cCount+")";
				strSet(strC,strA,strY,rsv2Form);
			}
		}
		if (aCount + yCount + cCount == 5) {
			adultp.setDisable(true);
			youthp.setDisable(true);
			childp.setDisable(true);
		}
		resInfo.setText(s+", "+getTCount(rsv2Form)+"매");
	}
	
	//선택내역
	public void strSet(String a, String b, String c, Parent rsv2Form) {
		set(rsv2Form);
		if(a!=null) {
			if(b!=null&&c!=null) {
				ticket.setText(a+", "+b+", "+c);
			}else if(b==null&&c==null) {
				ticket.setText(a);
			}else {
				if(c==null) {
					ticket.setText(a+", "+b);
				}
				if(b==null) {
					ticket.setText(a+", "+c);
				}
			}
		}else {
			if(b!=null&&c!=null) {
				ticket.setText(b+", "+c);
			}else if(b==null&&c==null) {
				ticket.setText(a);
			}else {
				if(c==null) {
					ticket.setText(b);
				}
				if(b==null) {
					ticket.setText(c);
				}
			}
		}
	}
	
	//티켓 총 매수
	public int getTCount(Parent rsv2Form) {
		set(rsv2Form);
		int a=Integer.parseInt(aNumber.getText());
		int y=Integer.parseInt(yNumber.getText());
		int c=Integer.parseInt(cNumber.getText());
		return tsum=a+y+c;
	}
	
	//결제 버튼
	@Override
	public void payment(Parent rsv2Form, ActionEvent e) throws SQLException {
		set(rsv2Form);
		if (aCount + yCount + cCount == 0) {
			comsrv.errorMsg("예매할 가격/수량을 선택해 주세요.");
			return;
		}else if(!agreement.isSelected()) {
			comsrv.errorMsg("취소/환불 규정에 동의해 주세요.");
			return;
		}
		if(comsrv.selectErrMsg("결제하시겠습니까?")) {
			DBService dbSrv=new DBServiceImpl();
			String loginedIdInfo[]=dbSrv.selectLId(cmd.getLoginedId());
			
			Reservation r=new Reservation();
			r.setRes_id(cmd.getLoginedId());
			r.setName(loginedIdInfo[0]);
			r.setTitle(title.getText());
			r.setRes_date(cmd.getExDate());
			r.setRes_time(cmd.getExTime());
			r.setTicket(tsum);
			r.setTicket_sel(ticket.getText());
			r.setPrice(sum);
			
			dbSrv.insertRsv(r);
			comsrv.errorMsg("결제가 완료되었습니다");
			clear();
			comsrv.closeWindow(e);
		}
	}
	
	//예매정보 초기화
	public void clear() {
		strA=null;
		strY=null;
		strC=null;
		sum=0;
		cmd.setExDate(null);
		cmd.setExTime(null);
	}
}
