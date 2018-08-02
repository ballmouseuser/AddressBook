package Teamplay;

import static Teamplay.LoginControl.getid;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import address.dao.ListMemberDao;
import address.vo.ListMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class UpdateControl implements Initializable {

	@FXML
	private Label labelBirthday;

	@FXML
	private Label labelAddress;

	@FXML
	private Label labelPhone;

	@FXML
	private Label labelEmail;

	@FXML
	private Label labelName;

	@FXML
	private JFXTextField txtPhone;

	@FXML
	private JFXButton btnImgadd;

	@FXML
	private JFXButton btnSave;

	@FXML
	private JFXTextField txtName;

	@FXML
	private JFXTextField txtBirthday;

	@FXML
	private JFXTextField txtAddress;

	@FXML
	private JFXTextField txtEmail;

	@FXML
	private ImageView imgView;

	@FXML
	private JFXButton btnResetAll;

	@FXML
	private Label userid;

	@FXML
	private Button btnExit;

	public String url = null;

	ListMember vo = MainControl.shareVo;

	String src = vo.getList_Member_ImgFile();

	public static String name, phone;

	@FXML
	void resetall(ActionEvent event) {
		txtPhone.clear();
		txtName.clear();
		txtBirthday.clear();
		txtAddress.clear();
		txtEmail.clear();
		imgView.setImage(null);

	}

	@FXML
	void save(ActionEvent event) {
		// labelClear();
		// focusClear();
		// if (!name()) {
		// labelName.setText("이름 형식이 다릅니다.");
		// txtName.setUnFocusColor(Color.RED);
		// } else if (!phone()) {
		// labelPhone.setText("전화번호 형식이 다릅니다.");
		// txtPhone.setUnFocusColor(Color.RED);
		// } else if (!birthday()) {
		// labelBirthday.setText("날짜 형식이 다릅니다.");
		// txtBirthday.setUnFocusColor(Color.RED);
		// } else if (!email()) {
		// labelEmail.setText("이메일 형식이 다릅니다.");
		// txtEmail.setUnFocusColor(Color.RED);
		// } else {

		labelClear();
		focusClear();
		if (txtName.getText().equals("")) {
			labelName.setText("이름을 입력하세요.");
			txtName.setUnFocusColor(Color.RED);
			return;
		} else if (txtPhone.getText().equals("")) {
			labelPhone.setText("전화번호를 입력하세요.");
			txtPhone.setUnFocusColor(Color.RED);
			return;
		} else if (txtBirthday.getText().equals("")) {
			labelBirthday.setText("생일을 입력하세요.");
			txtBirthday.setUnFocusColor(Color.RED);
			return;
		} else if (txtAddress.getText().equals("")) {
			labelAddress.setText("주소를 입력하세요.");
			txtAddress.setUnFocusColor(Color.RED);
			return;
		} else if (txtEmail.getText().equals("")) {
			labelEmail.setText("이메일을 입력하세요.");
			txtEmail.setUnFocusColor(Color.RED);
			return;
		} else {

			ListMember vo = new ListMember();

			ListMemberDao dao = new ListMemberDao();
			vo.setList_Member_Name(txtName.getText());
			vo.setList_Member_Address(txtAddress.getText());
			vo.setList_Member_Birthday(txtBirthday.getText());
			vo.setList_Member_EMail(txtEmail.getText());
			vo.setList_Member_Phone(txtPhone.getText());
			if (!(url == null)) {
				vo.setList_Member_ImgFile(url);
			} else {
				vo.setList_Member_ImgFile(src);
			}
			vo.setMember_Id(getid);
			dao.update(vo);

			try {
				Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage) btnSave.getScene().getWindow();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// }

	@FXML
	void imgadd(ActionEvent event) {
		url = "";
		FileChooser fc = new FileChooser();
		fc.setTitle("이미지 선택");
		fc.setInitialDirectory(new File("C:/Users"));
		ExtensionFilter imgType = new ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
		fc.getExtensionFilters().add(imgType);
		File path = fc.showOpenDialog(null);
		String temp = path + "";
		url = temp;
		temp = temp.replace("\\", "/");
		temp = "file:" + temp;
		Image img = new Image(temp);
		imgView.setImage(img);

	}

	@FXML
	void exit(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) btnExit.getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name = vo.getList_Member_Name();
		phone = vo.getList_Member_Phone();

		txtName.setText(vo.getList_Member_Name());
		txtPhone.setText(vo.getList_Member_Phone());
		txtBirthday.setText(vo.getList_Member_Birthday());
		txtAddress.setText(vo.getList_Member_Address());
		txtEmail.setText(vo.getList_Member_EMail());
		String temp = vo.getList_Member_ImgFile();
		temp = temp.replace("\\", "/");
		temp = "file:" + temp;
		Image img = new Image(temp);
		imgView.setImage(img);

	}

	public boolean email() { // 비밀번호 정규식
		String passwordd = txtEmail.getText();
		Pattern p = Pattern.compile("(^[a-zA-Z0-9]+@[a-zA-Z0-9]+(.[\\w]+))");
		Matcher m = p.matcher(passwordd);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean name() { // 한글이름 정규식
		String name = txtName.getText();
		Pattern p = Pattern.compile("(^[가-힣]*$)");
		Matcher m = p.matcher(name);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean phone() { // 전화번호 정규식 ex)010-0000-0000
		String phone = txtPhone.getText();
		Pattern p = Pattern.compile("((\\d{3})-(\\d{4})-(\\d{4}))");
		Matcher m = p.matcher(phone);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean birthday() { // 날짜 정규식 ex)YYYY-MM-DD
		String birthday = txtBirthday.getText();
		Pattern p = Pattern.compile("(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])");
		Matcher m = p.matcher(birthday);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	public void labelClear() { // 회원가입에 필요한 정보 입력 누락인 경우
		labelName.setText("");
		labelBirthday.setText("");
		labelAddress.setText("");
		labelPhone.setText("");
		labelEmail.setText("");
	}

	public void focusClear() {
		txtAddress.setUnFocusColor(Color.BLACK);
		txtBirthday.setUnFocusColor(Color.BLACK);
		txtEmail.setUnFocusColor(Color.BLACK);
		txtName.setUnFocusColor(Color.BLACK);
		txtPhone.setUnFocusColor(Color.BLACK);
	}
}
