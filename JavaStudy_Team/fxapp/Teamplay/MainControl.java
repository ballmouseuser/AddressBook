package Teamplay;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import address.common.ConnFactory;
import address.dao.ListMemberDao;
import address.vo.ListMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import static Teamplay.LoginControl.getid;

public class MainControl implements Initializable {

	@FXML
	private TableColumn<ListMember, String> colName;

	@FXML
	private Label labelBirthday;

	@FXML
	private JFXButton btnLogout;

	@FXML
	private Label labelLoginid;

	@FXML
	private TableColumn<ListMember, String> colPhone;

	@FXML
	private Label labelAddress;

	@FXML
	private JFXButton btnUpdate;

	@FXML
	private JFXTextField txtSelect;

	@FXML
	private Label labelPhone;

	@FXML
	private JFXButton btnDelete;

	@FXML
	private JFXButton btnAdd;

	@FXML
	private Label labelEmail;

	@FXML
	private ImageView imageview;

	@FXML
	private TableView<ListMember> tableview;

	@FXML
	private Label labelName;

	public List<ListMember> list;

	Connection conn = ConnFactory.getConnection("address.config.oracle");

	public static ListMember shareVo = null;

	@FXML
	void add(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Regist.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) btnLogout.getScene().getWindow();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void logout(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) btnLogout.getScene().getWindow();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void update(ActionEvent event) {
		try {

			// for(int i=0;i<buffer.length;i++) {
			// System.out.println(buffer[i]);
			// }
			int index = tableview.getSelectionModel().getSelectedIndex();
			shareVo = tableview.getItems().get(index);
			list = new ArrayList<>();
			list.add(shareVo);

			Parent root = FXMLLoader.load(getClass().getResource("Update.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) btnLogout.getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void delete(ActionEvent event) {
		ListMemberDao dao = new ListMemberDao();
		int index = tableview.getSelectionModel().getSelectedIndex();
		ListMember vo = tableview.getItems().get(index);
		dao.delete(vo.getList_Member_Name(), vo.getList_Member_Phone());
		tableview.getItems().remove(index);
		clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colName.setCellValueFactory(new PropertyValueFactory("list_Member_Name"));
		colPhone.setCellValueFactory(new PropertyValueFactory("list_Member_Phone"));
		labelLoginid.setText(getid + "님 환영합니다.");
		ListMemberDao listdao = new ListMemberDao();
		List<ListMember> list = listdao.select(getid);
		tableview.getItems().addAll(list);

		// try {
		// String sql = "SELECT LIST_MEMBER_NAME, LIST_MEMBER_PHONE FROM LIST";
		// PreparedStatement pstmt = conn.prepareStatement(sql);
		// ResultSet rs = pstmt.executeQuery();
		// while(rs.next()) {
		// ListMember vo = new ListMember();
		// vo.setList_Member_Name(rs.getString("LIST_MEMBER_NAME"));
		// vo.setList_Member_Phone(rs.getString("LIST_MEMBER_PHONE"));
		// list.add(vo);
		// }
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		tableview.setOnMouseReleased(e -> {
			int index = tableview.getSelectionModel().getSelectedIndex();
			if (index >= 0) {
				ListMember vo = tableview.getItems().get(index);
				labelName.setText(vo.getList_Member_Name());
				labelPhone.setText(vo.getList_Member_Phone());
				labelBirthday.setText(vo.getList_Member_Birthday());
				labelAddress.setText(vo.getList_Member_Address());
				labelEmail.setText(vo.getList_Member_EMail());
				String temp = vo.getList_Member_ImgFile();
				temp = temp.replace("\\", "/");
				temp = "file:" + temp;
				Image img = new Image(temp);
				imageview.setImage(img);
			}
		});

	}

	@FXML
	public void onEnter(ActionEvent event) {
		ListMemberDao dao = new ListMemberDao();
		String getName = txtSelect.getText();
		if (!getName.equals("")) {
			List<ListMember> list = dao.find(getid, getName);
			tableview.getItems().clear();
			tableview.getItems().addAll(list);
		} else {
			List<ListMember> list = dao.select(getid);
			tableview.getItems().clear();
			tableview.getItems().addAll(list);
		}
	}
	
	public void clear() {
		labelAddress.setText("");
		labelBirthday.setText("");
		labelEmail.setText("");
		labelName.setText("");
		labelPhone.setText("");
		String src = "file:d:/img/default.jpg";
		Image img = new Image(src);
		imageview.setImage(img);
	}
}
