package dao;

import java.sql.Statement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dto.ClientDTO;
import util.JDBCTemplate;

public class OrderingDAO {
	private CallableStatement cstmt = null;

	private JDBCTemplate util = JDBCTemplate.getInstance();
	Scanner sc = new Scanner(System.in);

	public void updateOrder(ClientDTO dto) { // 자리대여
		PreparedStatement pstmt = null;
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		int res = 0;
		int num = 0;

		String cid = dto.getCli_id();

		String sql1 = "INSERT INTO ORDERING (O_NO,O_DATE,O_AMO,CLI_ID) VALUES((SELECT MAX(O_NO+1) FROM ORDERING), SYSDATE , 0 , ? )";
		try {

			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, cid);
			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(conn);
		}
	}

	public String getOrderNO() { // O_NO 값을 가져오는 부분
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String o_no = "";
		String sql = "SELECT O_NO FROM ORDERING WHERE O_NO = (SELECT MAX(TO_NUMBER(O_NO))";
		sql = sql + "KEEP (DENSE_RANK FIRST ORDER BY (TO_NUMBER(O_NO)) DESC)";
		sql = sql + "  FROM ORDERING)";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				o_no = rs.getString("O_NO");
				System.out.println(rs.getString("O_NO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
		return o_no;
	}
	
	// 결제하기 누르면 ordering테이블 총금액 업데이트
	public void payment(String o_no) { 
		Connection conn = util.getConnection();
		
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "UPDATE ORDERING SET O_AMO = (SELECT SUM(B_AMO*P_PRICE) FROM BUYING";
		sql = sql + " JOIN PRODUCT ON BUYING.P_CODE = PRODUCT.P_CODE";
		sql = sql + " WHERE BUYING.O_NO = ORDERING.O_NO) WHERE O_NO = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, o_no);
			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(conn);
		}

	}
}
