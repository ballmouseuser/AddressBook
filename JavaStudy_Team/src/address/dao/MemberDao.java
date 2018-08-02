package address.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import address.common.ConnFactory;
import address.vo.Member;

public class MemberDao implements IDao<Member,String> {
	private Connection conn = ConnFactory.getConnection("address.config.oracle");

	@Override
	public void insert(Member vo) {
		// 회원가입 insert 메소드
		String sql = "INSERT INTO MEMBER(MEMBER_NAME, MEMBER_ID, MEMBER_PASSWORD, MEMBER_ADDRESS, MEMBER_REGISTNUMBER) "
				+ "VALUES(?,?,?,?,?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMember_name());
			pstmt.setString(2, vo.getMember_id());
			pstmt.setString(3, vo.getMember_password());
			pstmt.setString(4, vo.getMember_address());
			pstmt.setString(5, vo.getMember_registnumber());
			int res = pstmt.executeUpdate();
			if(res>0) {
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
			}else {
				System.out.println("실패");
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int checkUserLogin(String id, String passwd) { // 로그인시 아이디와 비밀번호 검사
		int check = -1;
		String dbpasswd = "";
		String sql = "SELECT MEMBER_PASSWORD FROM MEMBER WHERE MEMBER_ID = ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				dbpasswd = rs.getString("MEMBER_PASSWORD");
				if(dbpasswd.equals(passwd)) {
					check = 1;
				}else {
					check = 0; // 비밀번호 틀림
				}
			}else {
				check = -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////
	// 비사용 소스 //
	@Override
	public void delete(String name, String phone) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(Member vo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List select(String primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
