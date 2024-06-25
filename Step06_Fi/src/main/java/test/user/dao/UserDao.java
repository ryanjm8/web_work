package test.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import test.user.dto.UserDto;
import test.util.DbcpBean;

public class UserDao {
	private static UserDao dao;
	//static 초기화 블럭 (이 클래스가 최초로 사용되는 시점에 한번만 빌행되는 블럭)
	static {
		dao=new UserDao();
	}
	//외부에서 객체 생성하지 못하도록 생성자의 접근 지정자를 private 로 지정 
	private UserDao() {}
	//Dao 객체의 참조값을 메소드를 호출해서 받아 가도록 한다. 
	public static UserDao getInstance() {
		return dao;
	}
	//비밀번호를 수정하는 메소드
	public boolean updatePwd(UserDto dto) {				
		Connection conn=null;
		PreparedStatement pstmt=null;
		int rowCount=0;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql="update user_info"
					+ " set pwd=?"
					+ " where id=?";
			pstmt=conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getId());
			// update 문 실행하고 변화된 rowCount 를 리턴 받는다.
			rowCount=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}			
		}
		if(rowCount>0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	//아이디를 이용해서 회원 한명의 정보를 리턴하는 메소드
	public UserDto getData(String id) {
		
		UserDto dto=null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성 
			String sql = "SELECT pwd, email, profile, regdate"
					+ " FROM user_info"
					+ " WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			//만일 select 된 row 가 존재한다면 cursor 를 한칸내려서 
			if(rs.next()) {
				//UserDto 객체를 생성해서 
				dto=new UserDto();
				//select 된 정보를 담아준다. 
				dto.setId(id);
				dto.setPwd(rs.getString("pwd"));
				dto.setEmail(rs.getString("email"));
				dto.setProfile(rs.getString("profile"));
				dto.setRegdate(rs.getString("regdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		
		return dto;
	}
	
	//회원정보를 DB 에 저장하고 성공여부를 리턴하는 메소드 ( regdate 에는 SYSDATE 를 넣어준다)
	public boolean insert(UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql = "INSERT INTO user_info"
					+ " (id, pwd, email, profile, regdate)"
					+ " VALUES(?, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getProfile());
			// update 문 실행하고 변화된 rowCount 를 리턴 받는다.
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
	}
}

