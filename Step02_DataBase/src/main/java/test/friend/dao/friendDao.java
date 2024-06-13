package test.friend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.friend.dto.FriendDto;
import test.member.dto.MemberDto;
import test.util.DbcpBean;

public class friendDao {
	
	public boolean update(FriendDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql = "UPDATE member"
					+ " SET name=?, addr=?"
					+ " WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPhone());
			pstmt.setInt(3, dto.getNum());
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
	
	public MemberDto getData(int num) {
		MemberDto dto=null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성 
			String sql = "select name, phone"
					+ " from friend"
					+ " where num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {

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
	
	//회원 정보를 DB 에 저장하고 성공 여부를 리턴하는 메소드
	public boolean delete(int num) {
	
		Connection conn=null;
		PreparedStatement pstmt=null;
		int rowCount=0;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql="delete from friend"
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
	
	public boolean insert(FriendDto dto) {
	
		Connection conn=null;
		PreparedStatement pstmt=null;
		int rowCount=0;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql="insert into friend"
					+ "	(num, name, phone)"
					+ " values(friend_seq.nextval, ?, ?)";
			pstmt=conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPhone());
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
	
	
	
	public List<FriendDto> getList(){
		List<FriendDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			conn = new DbcpBean().getConn();
			
			String sql = "select num, name, phone"
					+ " from friend"
					+ " order by num asc";
			pstmt = conn.prepareStatement(sql);				
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FriendDto dto = new FriendDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)rs.close();
				if (pstmt != null)pstmt.close();
				if (conn != null)conn.close();
			} catch (Exception e) {}
		}
			return list;
		
	}
}
