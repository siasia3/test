package dao;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.ClientDTO;
import util.JDBCTemplate;

public class RentalDAO {
	private JDBCTemplate util = JDBCTemplate.getInstance();
	Scanner sc = new Scanner(System.in);
	SeatDAO sdao = new SeatDAO();

	public void rental(ClientDTO dto, String seat) { // 자리대여
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		// int res = 0;
		String sql = "INSERT INTO RENTAL(R_NO,CLI_ID,SEAT_NO,R_START,R_END) VALUES((SELECT MAX(R_NO+1) FROM RENTAL),?, ?, SYSDATE, SYSDATE+(?/1440))";
		
		String sql2 = "UPDATE SEAT SET SEAT_USE = 'Y' WHERE SEAT_NO = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,dto.getCli_id());
			pstmt.setString(2,seat);
			pstmt.setInt(3,dto.getCli_time());
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1,seat);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(conn);
		}
	}
	
	public void checkTime(String id) { //사용중인 경우 잔여시간 보여주기
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT CLI_ID FROM RENTAL WHERE CLI_ID = ?";
		sql = sql + " AND SYSDATE BETWEEN R_START AND R_END";
		String sql2 = "SELECT (ROUND((R_END - SYSDATE) * 24 * 60)) AS TIME FROM RENTAL WHERE CLI_ID = ?";
		sql2 = sql2 + " AND SYSDATE BETWEEN R_START AND R_END";
		String sql3 = "SELECT CLI_TIME FROM CLIENT WHERE CLI_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			pstmt = null;
			if (rs.next()) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				rs.next();
				System.out.println("─────────────────────────────────\n");
				System.out.println("   남은 시간은 "+rs.getString("TIME")+"분입니다. \n");
				System.out.println("─────────────────────────────────\n");			
			}else {
				pstmt = conn.prepareStatement(sql3);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				rs.next();
				System.out.println("─────────────────────────────────\n");
				System.out.println("   남은 시간은 "+rs.getString("CLI_TIME")+"분입니다. \n");
				System.out.println("─────────────────────────────────\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(pstmt);
			util.close(conn);
		}
		
	}

	public String rentalSeatCheck(String id) { // 이 사람이 사용중인 자리를 반환
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT SEAT_NO FROM RENTAL WHERE CLI_ID = ?";
		sql = sql + " AND SYSDATE BETWEEN R_START AND R_END";
		String seat = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				seat = rs.getString("SEAT_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(pstmt);
			util.close(conn);
		}
		return seat;
	}

	
	public void rentalEnd(String seat, String id) { // 좌석 종료시 대여종료를 현재시간으로 바꿔줌
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE RENTAL SET R_END = SYSDATE WHERE SYSDATE BETWEEN R_START AND R_END AND CLI_ID = ? AND SEAT_NO = ?";
		
	    try {
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setString(1, id);
	    	pstmt.setString(2, seat);
	    	pstmt.executeUpdate();
	    } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(conn);
		}
	    
	}
	
	public String duplicationSeat(String id) { //좌석을 두개 사용 못하게 하는 부분
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT SEAT_NO FROM RENTAL WHERE SYSDATE BETWEEN R_START AND R_END AND CLI_ID = ?";
		ResultSet rs = null;
		String res = "";
	    try {
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setString(1, id);
	    	rs = pstmt.executeQuery();
	    	if(rs.next()){
	    		res = rs.getString("SEAT_NO");
	    	}
	    	
	    	
	    } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(pstmt);
			util.close(conn);
		}
	    
		return res;
	}
	

}
