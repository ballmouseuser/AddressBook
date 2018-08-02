package Teamplay;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;

import address.common.ConnFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WithdrawalControl {
	
	private Connection conn = ConnFactory.getConnection("address.config.oracle");
	
    @FXML
    private PasswordField txtUserpassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtUserid;

    @FXML
    private PasswordField txtUserpassword2;

    @FXML
    private JFXButton btnsubmit;
    
    @FXML
    private JFXButton btncancel;

    @FXML
    void submit(ActionEvent event) {
    	String sql ="DELETE FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_NAME=? AND MEMBER_PASSWORD = ? " ; 
    	try {
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, txtUserid.getText());
			pstmt.setString(2, txtUsername.getText());
			pstmt.setString(3, txtUserpassword.getText());
//			int res = pstmt.executeUpdate();
			String pass1 = txtUserpassword.getText();
			String pass2 = txtUserpassword2.getText();
			 
			if(pass1.equals(pass2)){
				int res = pstmt.executeUpdate();
				if(res>0) {
					JOptionPane.showMessageDialog(null, "회원탈퇴");
			    	try {
			    		Parent back = FXMLLoader.load(getClass().getResource("Withdrawal.fxml"));
			    		Scene scene = new Scene(back);
			    		Stage stage = (Stage)btnsubmit.getScene().getWindow();
			    		stage.setResizable(false);
			    		stage.setScene(scene);
			    		stage.close();
			    	} catch (IOException e) {
			    		// TODO Auto-generated catch block
			    		e.printStackTrace();
			    	}
					
				}else {
					JOptionPane.showMessageDialog(null, "아이디가 틀렸거나 없는 아이디입니다.");
				}
			}else {
				JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }
    @FXML
    void cancel(ActionEvent event) {
    	Parent back;
    	try {
    		back = FXMLLoader.load(getClass().getResource("Withdrawal.fxml"));
    		Scene scene = new Scene(back);
    		Stage stage = (Stage)btncancel.getScene().getWindow();
    		stage.setResizable(false);
    		stage.setScene(scene);
    		stage.close();
    		
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    }

}
