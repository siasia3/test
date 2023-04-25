package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.JDBCTemplate;

public class SeatDAO {
	private JDBCTemplate util = JDBCTemplate.getInstance();

	
	public String[][] seatCheck() { // �¼� ��뿩��
		String[][] seat = new String[4][10];
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT SEAT_USE FROM SEAT";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 10; j++) {
					rs.next();
					if(rs.getString("SEAT_USE").equals("Y")) {
						seat[i][j] = "\u25A0";
					}else {
						seat[i][j] = "\u25A1";
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
		return seat;

	}
	public boolean UsingCheck(String seat) { // �¼��� �������� �� �̹� ��������� Ȯ��
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = true;
		String sql = "SELECT SEAT_NO FROM SEAT WHERE SEAT_NO = ? AND SEAT_USE = 'Y'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seat);
			rs = pstmt.executeQuery();
			if(rs.next()){
				check = false;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(pstmt);
			util.close(conn);
		}
		return check;
	}

	public void seatUpdate() { //���Ḧ �ȴ����� �� ��� �ڵ����� �¼� N���� �ٲ��ִ� �κ�  
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT seat_no, MAX(r_no) FROM rental WHERE SYSDATE > R_END AND seat_no" 
				+ " IN (SELECT seat_no FROM seat WHERE seat_use = 'Y') GROUP BY seat_no";

		String sql2= "UPDATE SEAT SET seat_use = 'N'"; 
		sql2 = sql2 + " WHERE seat_no IN ( SELECT seat_no FROM RENTAL";
		sql2 =sql2 + "  WHERE r_end < SYSDATE and r_no = ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				pstmt = null;
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, rs.getString("MAX(r_no)"));
				pstmt.executeUpdate();
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(rs);
			util.close(conn);
		}
	}
	public void useOff(String seat) { //�¼� �뿩���� ���� �� �¼�off
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE SEAT SET SEAT_USE = 'N' WHERE SEAT_NO = ?";
		 try {
		    	pstmt = conn.prepareStatement(sql);
		    	pstmt.setString(1, seat);
		    	pstmt.executeUpdate();
		    	
		    } catch (SQLException e) {
				e.printStackTrace();
			} finally {
				util.close(pstmt);
				util.close(conn);
			}
	}
	
	
	

}
