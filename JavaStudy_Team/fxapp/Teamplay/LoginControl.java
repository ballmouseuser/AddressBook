package Teamplay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import address.dao.MemberDao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginControl implements Initializable {

    @FXML
    private JFXPasswordField txtUserpassword;

    @FXML
    private JFXButton btnJoin;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXTextField txtUserid;

    @FXML
    private Button btnExit;

    @FXML
    private JFXCheckBox btnCheckbox;
    
    @FXML
    private Label labelErr;
    
    @FXML
    private Label labelWithdraw;
    
    public static String getid;

	@FXML
	void login(ActionEvent event) {
		String id = txtUserid.getText();
		String pw = txtUserpassword.getText();
		MemberDao dao = new MemberDao();
		int check = dao.checkUserLogin(id, pw);
		if(btnCheckbox.isSelected()) { // 아이디 저장 항목이 체크가 된 상태에서 로그인한 경우
			File file = new File("d:/rememberId.txt");
			FileWriter writer = null;
			try {
				writer = new FileWriter(file,false);
				String set = "true,"+id;
				writer.write(set);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			File file = new File("d:/rememberId.txt");
			FileWriter writer = null;
			try {
				writer = new FileWriter(file,false);
				String set = "false,";
				writer.write(set);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(check==1) {
			try {
				getid = txtUserid.getText();
				Parent root = FXMLLoader.load(getClass().getResource("Loding.fxml"));
				Stage staget = (Stage) btnJoin.getScene().getWindow();
				Scene scenet = new Scene(root);
				staget.setResizable(false);
				staget.setScene(scenet);
				Timer t = new Timer(true);
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						Platform.runLater(() -> {
							try {
								Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
								Stage stage = (Stage) staget.getScene().getWindow();
								Scene scene = new Scene(root);
								stage.setResizable(false);
								stage.setScene(scene);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
					}
				};
				t.schedule(task, 2000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			labelErr.setText("아이디 혹은 비밀번호를 확인해주세요.");
		}
	}

	@FXML
	void join(ActionEvent event) {
		try {
			Parent roott = FXMLLoader.load(getClass().getResource("Loding.fxml"));
			Stage staget = (Stage) btnJoin.getScene().getWindow();
			Scene scenet = new Scene(roott);
			staget.setResizable(false);
			staget.setScene(scenet);
			Timer t = new Timer(true);
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					Platform.runLater(() -> {
						try {
							Parent root = FXMLLoader.load(getClass().getResource("Join.fxml"));
							Stage stage = (Stage) staget.getScene().getWindow();
							Scene scene = new Scene(root);
							stage.setResizable(false);
							stage.setScene(scene);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
 
					});
				}
			};
			t.schedule(task, 1500);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
    @FXML
    void exit(ActionEvent event) {
		System.exit(0);
    }
    
    @FXML
    void draw(MouseEvent event) {
    	try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("Withdrawal.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
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
		File file = new File("d:/rememberId.txt");
		FileWriter writer = null;
				
		if (!file.exists()) {
			try {
				file.createNewFile();
				writer = new FileWriter(file, false);
				String set = "false,";
				writer.write(set);
				writer.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 파일을 실제로 생성
		}	
		
		try {
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			
			String oneline = "";
			String[] split = null;
			while((oneline = bufReader.readLine())!= null) {
				split = oneline.split(",");
			}
			if(split[0].equals("true")) {
				txtUserid.setText(split[1]+"");
				btnCheckbox.setSelected(true);
			}else {
				txtUserid.setText("");
				btnCheckbox.setSelected(false);
			}
			bufReader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}