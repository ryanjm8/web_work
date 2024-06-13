package test.guest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.guest.dto.GuestDto;
import test.util.DbcpBean;

/*
 *   application 전역에서 GuestDao 객체는 오직 한개만 생성되어서 사용되도록 클래스를 설계한다.
 *   - 한정된 자원인 Connection 객체를 좀더 효율적으로 사용하기 위해 
 */
public class GuestDao {
	//자신의 참조값을 저장할 static 필드
	private static GuestDao dao;
	
	//static 초기화 블럭 (이클래스가 최초로 사용될때 오직 한번만 수행된다)
	static {
		//객체를 생성해서 static 필드에 담는다 
		dao=new GuestDao();
	}
	
	//외부에서 객체 생성하지 못하도록 생성자의 접근 지정자를 private 로 설정
	private GuestDao() {}
	
	//static 필드에 저장된 GuestDao 의 참조값을 리턴해주는 static 메소드
	public static GuestDao getInstance() {
		return dao;
	}
	
	//글 하나의 정보를 수정하는 메소드
	//회원 정보를 DB 에 저장하고 성공 여부를 리턴하는 메소드
	public boolean update(GuestDto dto) {
	
		Connection conn=null;
		PreparedStatement pstmt=null;
		int rowCount=0;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql="update board_guest"
					+ " set writer=?, content=?"
					+ " where num=?";
			pstmt=conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());
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
	
	//글 하나의 정보를 삭제하는 메소드
	public boolean delete(int num) {
			
		Connection conn=null;
		PreparedStatement pstmt=null;
		int rowCount=0;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql="delete from board_guest"
					+ " where num=?";
			pstmt=conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setInt(1, num);
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
	
	
	
	//글 하나의 정보를 리턴하는 메소드
	public GuestDto getData(int num) {
		GuestDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성 
			String sql = "select writer, content, pwd, regdate"
					+ " from board_guest"
					+ " where num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				//GuestDto 객체를 생성해서
				dto=new GuestDto();
				dto.setNum(num);
				//select 된 정보를 담는다.
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setPwd(rs.getString("pwd"));
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
	
	//글 목록을 리턴하는 메소드
	public List<GuestDto> getList(){
		//글목록을 저장할 ArrayList 객체 생성
		List<GuestDto> list=new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성 
			String sql = "SELECT num, writer, content, pwd, regdate"
					+ " FROM board_guest"
					+ " ORDER BY num DESC";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.

			rs = pstmt.executeQuery();
			while (rs.next()) {
				//커서가 위치한 곳의 글정보를 추출해서 GuestDto 객체에 담은 다음
				GuestDto dto=new GuestDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setPwd(rs.getString("pwd"));
				dto.setRegdate(rs.getString("regdate")); //날짜도 .getString() 하면 된다.
				//ArrayList 객체에 누적 시킨다.
				list.add(dto);
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
		return list;
	}
	
	//글정보를 DB 에 저장하는 메소드
	public boolean insert(GuestDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql = "INSERT INTO board_guest"
					+ " (num, writer, content, pwd, regdate)"
					+ " VALUES(board_guest_seq.NEXTVAL, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getPwd());
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
