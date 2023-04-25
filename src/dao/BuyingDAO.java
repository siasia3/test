package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.JDBCTemplate;

public class BuyingDAO {
	private JDBCTemplate util = JDBCTemplate.getInstance();
	
	public void insertBasket(String select, int quantity, String o_no) { //구매목록 장바구니로
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "INSERT INTO BUYING VALUES(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, o_no);
			pstmt.setString(2, select);
			pstmt.setInt(3, quantity);
			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(conn);
		}

	}
	
	public int checkAmount(String select, String o_no) {// 수량을 가져오는 부분
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "SELECT B_AMO FROM BUYING WHERE O_NO = ? AND P_CODE = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, o_no);
			pstmt.setString(2, select);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt("B_AMO");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(rs);
			util.close(conn);
		}
		return num;
	}

	public void addAmonut(String select, int amount, String o_no) { //동일 상품코드 수량추가
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql1 = "SELECT B_AMO FROM BUYING WHERE O_NO = ? AND P_CODE = ?";
		String sql2 = "UPDATE BUYING SET B_AMO = ? WHERE O_NO = ? AND P_CODE = ?";

		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, o_no);
			pstmt.setString(2, select);
			rs = pstmt.executeQuery();
			rs.next();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, rs.getInt("B_AMO") + amount);
			pstmt.setString(2, o_no);
			pstmt.setString(3, select);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(rs);
			util.close(conn);
		}

	}
	
	public void deleteBasket() { //구매취소 장바구니삭제
		Connection conn = util.getConnection();
		Statement stmt = null;
		int res = 0;
		String sql = "DELETE FROM BUYING WHERE O_NO = (SELECT MAX(TO_NUMBER(O_NO)) ";
		sql = sql + "KEEP (DENSE_RANK FIRST ORDER BY (TO_NUMBER(O_NO)) DESC)";
		sql = sql + "  FROM BUYING)";
		try {
			stmt = conn.createStatement();
			res = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(stmt);
			util.close(conn);
		}
	}
	public void buyingList(String o_no) { //장바구니 확인
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	   // String p_code = pdto.getP_code(); 
		//String o_no = odto.getO_no();
		
		String sql = "SELECT P.P_NAME, B.B_AMO , (B.B_AMO * P.P_PRICE) AS MONEY FROM PRODUCT P";
		 sql = sql + " JOIN BUYING B ON P.P_CODE = B.P_CODE";
		 sql = sql + " AND O_NO = ?";
		 try {
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, o_no);
		        rs = pstmt.executeQuery();
		        System.out.println("=================================");
		        System.out.println("=================================");
		        while (rs.next()) {
		            String p_name = rs.getString(1);
		            int b_amo = rs.getInt(2);
		            int money = rs.getInt(3);
		            System.out.printf("상품명: %s , 수량: %d개 , 금액: %d원 \n", p_name, b_amo, money);       
		            
		        }                                         
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        util.close(rs);
		        util.close(pstmt);
		        util.close(conn);
		    }
	}
	
}
