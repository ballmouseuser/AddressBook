package address.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Teamplay.UpdateControl;
import address.common.ConnFactory;
import address.vo.ListMember;

public class ListMemberDao implements IDao<ListMember, String> {
	Connection conn = ConnFactory.getConnection("address.config.oracle");
	
	// 추가한 주소록를 수정을 할때 필요한 수정 전 이름과 전화번호 값을 저장
	String name = UpdateControl.name;
	String phone = UpdateControl.phone;

	@Override
	public void insert(ListMember vo) {
		// 주소록 추가를 위한 메소드
		// 외래키인 MEMBER_ID는 따로 입력 받지 않고 로그인한 회원의 아이디를 가져와 자동으로 넣어줌
		String sql = "INSERT INTO LIST(LIST_MEMBER_NAME, LIST_MEMBER_PHONE, LIST_MEMBER_ADDRESS, LIST_MEMBER_BIRTHDAY,LIST_MEMBER_EMAIL,LIST_MEMBER_IMGFILE,MEMBER_ID) "
				+ "VALUES (?,?,?,?,?,?,(SELECT member_id FROM MEMBER WHERE MEMBER_ID = ?))";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getList_Member_Name());
			pstmt.setString(2, vo.getList_Member_Phone());
			pstmt.setString(3, vo.getList_Member_Address());
			pstmt.setString(4, vo.getList_Member_Birthday());
			pstmt.setString(5, vo.getList_Member_EMail());
			pstmt.setString(6, vo.getList_Member_ImgFile());
			pstmt.setString(7, vo.getMember_Id());

			int res = pstmt.executeUpdate();
			if (res > 0) {
				System.out.println("잘입력됨");
			} else {
				System.out.println("안됨");
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(String name, String phone) {
		// 주소록의 정보를 삭제하는 메소드
		// 별도의 기본키가 없는 테이블이기 때문에 이름과 전화번호를 비교하여 삭제
		String sql = "DELETE FROM LIST WHERE LIST_MEMBER_NAME = ? AND LIST_MEMBER_PHONE = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			int res = pstmt.executeUpdate();
			if (res > 0) {
				System.out.println("삭제됨");
			} else {
				System.out.println("안됨");
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(ListMember vo) {
		// 주소록의 정보를 수정하기 위한 메소드
		String sql = "UPDATE LIST "
				+ "SET LIST.LIST_MEMBER_NAME =?, LIST.LIST_MEMBER_PHONE=?, LIST.LIST_MEMBER_BIRTHDAY=?, "
				+ "LIST.LIST_MEMBER_ADDRESS=?, LIST.LIST_MEMBER_EMAIL=?, LIST.LIST_MEMBER_IMGFILE=?, "
				+ "LIST.MEMBER_ID =? WHERE LIST.LIST_MEMBER_NAME =? "
				+ "AND LIST.LIST_MEMBER_PHONE=? AND LIST.MEMBER_ID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getList_Member_Name());
			pstmt.setString(2, vo.getList_Member_Phone());
			pstmt.setString(3, vo.getList_Member_Birthday());
			pstmt.setString(4, vo.getList_Member_Address());
			pstmt.setString(5, vo.getList_Member_EMail());
			pstmt.setString(6, vo.getList_Member_ImgFile());
			pstmt.setString(7, vo.getMember_Id());
			// 추가한 주소록를 수정을 할때 필요한 수정 전 이름과 전화번호 값을 저장
			// name, phone의 값을 where절에 사용하여 정보를 수정함.
			pstmt.setString(8, name);
			pstmt.setString(9, phone);
			pstmt.setString(10, vo.getMember_Id());
			int res = pstmt.executeUpdate();
			if (res > 0) {
				System.out.println("잘됨");
			} else {
				System.out.println("안됨");
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List select(String primaryKey) {
		// 주소록의 정보를 가져오기 위한 메소드
		String sql = "SELECT LIST.LIST_MEMBER_NAME, LIST.LIST_MEMBER_PHONE, LIST.LIST_MEMBER_BIRTHDAY, "
				+ " LIST.LIST_MEMBER_ADDRESS, LIST.LIST_MEMBER_EMAIL, LIST.LIST_MEMBER_IMGFILE, "
				+ " MEMBER.MEMBER_ID FROM LIST, MEMBER WHERE LIST.MEMBER_ID = MEMBER.MEMBER_ID AND MEMBER.MEMBER_ID = ?";
		List<ListMember> list = new ArrayList<>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, primaryKey);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ListMember vo = new ListMember();
				vo.setList_Member_Name(rs.getString("LIST_MEMBER_NAME"));
				vo.setList_Member_Phone(rs.getString("LIST_MEMBER_PHONE"));
				vo.setList_Member_Birthday(rs.getString("LIST_MEMBER_BIRTHDAY"));
				vo.setList_Member_Address(rs.getString("LIST_MEMBER_ADDRESS"));
				vo.setList_Member_EMail(rs.getString("LIST_MEMBER_EMAIL"));
				vo.setList_Member_ImgFile(rs.getString("LIST_MEMBER_IMGFILE"));
				vo.setMember_Id(rs.getString("MEMBER_ID"));
				list.add(vo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List selectAll() {
		// 주소록에 추가된 모든 정보를 불러오는 메소드
		String sql = "SELECT LIST_MEMBER_NAME, LIST_MEMBER_PHONE, LIST_MEMBER_BIRTHDAY, LIST_MEMBER_ADDRESS, LIST_MEMBER_EMAIL, LIST_MEMBER_IMGFILE, MEMBER_ID FROM LIST ";
		List<ListMember> list = new ArrayList<>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ListMember vo = new ListMember();
				vo.setList_Member_Name(rs.getString("LIST_MEMBER_NAME"));
				vo.setList_Member_Phone(rs.getString("LIST_MEMBER_PHONE"));
				vo.setList_Member_Birthday(rs.getString("LIST_MEMBER_BIRTHDAY"));
				vo.setList_Member_Address(rs.getString("LIST_MEMBER_ADDRESS"));
				vo.setList_Member_EMail(rs.getString("LIST_MEMBER_EMAIL"));
				vo.setList_Member_ImgFile(rs.getString("LIST_MEMBER_IMGFILE"));
				vo.setMember_Id(rs.getString("MEMBER_ID"));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List find(String id,String name) {
		// 특정 주소록의 정보를 가져오기위한 메소드
			String sql = "SELECT * FROM LIST, MEMBER WHERE LIST.MEMBER_ID = MEMBER.MEMBER_ID AND LIST.MEMBER_ID =? AND LIST.LIST_MEMBER_NAME=?";
			List<ListMember> list = new ArrayList<>();
			
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					ListMember vo = new ListMember();
					vo.setList_Member_Name(rs.getString("LIST_MEMBER_NAME"));
					vo.setList_Member_Phone(rs.getString("LIST_MEMBER_PHONE"));
					vo.setList_Member_Birthday(rs.getString("LIST_MEMBER_BIRTHDAY"));
					vo.setList_Member_Address(rs.getString("LIST_MEMBER_ADDRESS"));
					vo.setList_Member_EMail(rs.getString("LIST_MEMBER_EMAIL"));
					vo.setList_Member_ImgFile(rs.getString("LIST_MEMBER_IMGFILE"));
					vo.setMember_Id(rs.getString("MEMBER_ID"));
					list.add(vo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}

}
