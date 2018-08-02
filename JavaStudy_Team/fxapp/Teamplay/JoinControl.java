package Teamplay;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import address.dao.MemberDao;
import address.vo.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JoinControl {


    @FXML
    private JFXPasswordField txtUserpassword;

    @FXML
    private JFXTextField txtUsername;
    
    @FXML
    private Label labelErrid;
    
    @FXML
    private Label labelErrregistnumber;
    
    @FXML
    private Label labelErraddress;
    
    @FXML
    private Label labelErrname;
    
    @FXML
    private Label labelErrpassword;

    @FXML
    private JFXTextField txtUserid;

    @FXML
    private JFXTextField txtUserregistnumber;

    @FXML
    private JFXTextField txtUserAddress;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private Button btnExit;

    @FXML
    private JFXCheckBox btnCheckbox;

	@FXML
	void submit(ActionEvent event) {
		labelClear();
		focusClear();
		if(txtUsername.getText().equals("")) {
			labelErrname.setText("이름을 입력하세요.");
			txtUsername.setUnFocusColor(Color.RED);
			fieldClear();
			return;
		}else if(txtUserid.getText().equals("")) {
			labelErrid.setText("아이디를 입력하세요.");
			txtUserid.setUnFocusColor(Color.RED);
			fieldClear();
			return;
		}else if(!password()) {
			labelErrpassword.setText("암호를 입력하세요.");
			txtUserpassword.setUnFocusColor(Color.RED);
			fieldClear();
			return;
		}else if(txtUserAddress.getText().equals("")) {
			labelErraddress.setText("주소를 입력하세요.");
			txtUserAddress.setUnFocusColor(Color.RED);
			fieldClear();
			return;
		}else if(!juminChk(txtUserregistnumber.getText())) {
			labelErrregistnumber.setText("주민번호를 입력하세요.");
			txtUserregistnumber.setUnFocusColor(Color.RED);
			fieldClear();
			return;	
		}else if(btnCheckbox.isSelected()==false) {
			JOptionPane.showMessageDialog(null, "자동 가입 방지 체크 바랍니다.");
			return;
		}else {
			labelClear();
			focusClear();
			Member vo = new Member();
			vo.setMember_name(txtUsername.getText());
			vo.setMember_id(txtUserid.getText());
			vo.setMember_password(txtUserpassword.getText());
			vo.setMember_address(txtUserAddress.getText());
			vo.setMember_registnumber(txtUserregistnumber.getText());
			MemberDao dao = new MemberDao();
			dao.insert(vo);
			
			try {
				AnchorPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = (Stage)btnSubmit.getScene().getWindow();
				primaryStage.setResizable(false);
				primaryStage.setScene(scene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	@FXML
    void exit(ActionEvent event) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = (Stage)btnSubmit.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public static boolean juminChk(String idNum) {
		String regStr = "("
				+ "?:[0-9]{2}" // 56년,09년
				+ "(?:0[1-9]|1[0-2])" //03월,11월,12월,13월(x)  
				+ "(?:0[1-9]|[1,2][0-9]|3[0,1])"//07일,29일,31일,32일(x)
				+ ")"
				+ "-"
				+ "[1-4]"//1900년대 1 남자 2여자,2000년대 3 남자, 4 여자 
				+ "[0-9]{6}";//805018 나머지숫자
		return Pattern.matches(regStr, idNum);
	}
	
	public void fieldClear() {
		txtUserpassword.clear();
		txtUserregistnumber.clear();
	}
	
	public void labelClear() { // 회원가입에 필요한 정보 입력 누락인 경우
		labelErraddress.setText("");
		labelErrid.setText("");
		labelErrname.setText("");
		labelErrpassword.setText("");
		labelErrregistnumber.setText("");
	}
	public void focusClear() { // 포커스 색상 초기화
		txtUsername.setUnFocusColor(Color.BLACK);
		txtUserid.setUnFocusColor(Color.BLACK);
		txtUserAddress.setUnFocusColor(Color.BLACK);
		txtUserpassword.setUnFocusColor(Color.BLACK);
		txtUserregistnumber.setUnFocusColor(Color.BLACK);
	}
	
	public boolean password() { // 비밀번호 정규식 
		String password = txtUserpassword.getText();
		Pattern p = Pattern.compile("([a-z].*[A-Z].{0,9}|[A-Z].*[a-z].{0,9})");
		Matcher m = p.matcher(password);
		if (m.find()){
			return true;
		}else{
			return false;
		}
	}

}
