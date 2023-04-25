package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.ClientDTO;
import util.JDBCTemplate;

public class ClientDAO {
	private JDBCTemplate util = JDBCTemplate.getInstance();
	RentalDAO rdao = new RentalDAO();
	Scanner sc = new Scanner(System.in);

	// 1-1. 회원가입
	public void signUp(ClientDTO dto) {
		Connection conn = util.getConnection();

		PreparedStatement pstmt = null;
		String sql = "INSERT INTO CLIENT VALUES(?,?,?,?,0)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCli_id());
			pstmt.setString(2, dto.getCli_pwd());
			pstmt.setString(3, dto.getCli_name());
			pstmt.setString(4, dto.getCli_tel());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(conn);
		}
	}

	public boolean checkId(String cid) {
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		boolean vaild = true;
		String sql = "SELECT CLI_ID FROM CLIENT WHERE CLI_ID = '" + cid + "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return vaild;
			} else {
				vaild = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(stmt);
			util.close(conn);
		}
		return vaild;
	}

	public boolean checkTel(String ctel) {
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		boolean vaild = true;
		String sql = "SELECT CLI_TEL FROM CLIENT WHERE CLI_TEL = '" + ctel + "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return vaild;
			} else {
				vaild = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(stmt);
			util.close(conn);
		}
		return vaild;
	}

	// 1-1. 로그인
	public int login(ClientDTO dto) {
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String cid = dto.getCli_id();
		String cpwd = dto.getCli_pwd();

		String sql = "SELECT CLI_ID, CLI_PW FROM CLIENT WHERE CLI_ID = '" + cid + "' and CLI_PW = '" + cpwd + "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) { // rs가 현재 null값인지 확인하려고
				if (cid.length() > 10 || cpwd.length() > 10) {
					System.out.println("\n# 글자수 초과_ID/PW를 10자 이하로 입력");
				} else {
					// 관리자
					if (cid.equals(rs.getString("CLI_ID")) && cpwd.equals(rs.getString("CLI_PW"))) {
						if ("MASTER".equals(cid)) { // 관리자
							System.out.println(rs.getString("CLI_ID"));
							return 3;
						} else if (!"MASTER".equals(cid)) { // 고객
							return 4;
						}
					}
				}
			} else {
				System.out.println("\n로그인 실패(ID/PW확인)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
		return 5;
	}

	// 1-1. 회원테이블에 시간 업로드
	public void updateTime(ClientDTO dto, String o_no) {
		Connection conn = util.getConnection();
		String cid = dto.getCli_id();
		PreparedStatement pstmt = null;

		String sql = "UPDATE CLIENT SET CLI_TIME =  COALESCE((SELECT CLI_TIME FROM CLIENT WHERE CLI_ID = ?),0) + ";
		sql = sql + " COALESCE((SELECT 60 * B_AMO FROM BUYING WHERE P_CODE = '1' AND O_NO = ?),0)";
		sql = sql + " WHERE CLI_ID = ?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cid);
			pstmt.setString(2, o_no);
			pstmt.setString(3, cid);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(conn);
		}
	}

	// 1-1. 회원테이블에 저장된 시간 호출
	public int getTime(String id) {
		int time = 0;
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT CLI_TIME FROM CLIENT WHERE CLI_ID = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				time = rs.getInt("CLI_TIME");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(pstmt);
			util.close(conn);
		}

		return time;
	}

	// 1-2. ID 찾기_연락처로 조회
	public void serchId(String ctel) {
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT CLI_ID, CLI_TEL FROM CLIENT WHERE CLI_TEL = '" + ctel + "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (ctel.equals(rs.getString("CLI_TEL"))) {
					System.out.println("\n# 연락처 확인 완료");
					System.out.println("아이디 : " + rs.getString("CLI_ID"));
				}
			} else {
				System.out.println("\n일치하는 정보가 없음");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
	}

	// 1-2. PW 찾기_ID 및 연락처로 조회
	public void serchPw(String cid, String ctel2) {
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT CLI_ID, CLI_PW, CLI_TEL FROM CLIENT ";
		sql = sql + "WHERE CLI_ID = '" + cid + "' and CLI_TEL = '" + ctel2 + "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (cid.equals(rs.getString("CLI_ID")) && ctel2.equals(rs.getString("CLI_TEL"))) {
					System.out.println("\n# 아이디, 연락처 확인 완료");
					System.out.println("비밀번호 : " + rs.getString("CLI_PW"));
				}
			} else {
				System.out.println("\n일치하는 정보가 없음");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
	}

	// 1-2. 관리자 로그인 -> 일별 매출조회(전체 일자)
	public void daySales() {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TO_CHAR(O_DATE, 'YYYY-MM-DD'), SUM(O_AMO) " + "FROM ORDERING "
				+ "GROUP BY TO_CHAR(O_DATE, 'YYYY-MM-DD') " + "ORDER BY TO_CHAR(O_DATE, 'YYYY-MM-DD') ASC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("=================================");
			System.out.println("날짜          일별 매출금액");
			System.out.println("=================================");
			while (rs.next()) {
				String date = rs.getString(1);
				int sales = rs.getInt(2);
				System.out.printf("%s   %,d원%n", date, sales);
				System.out.println("─────────────────────────────────");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(conn);
		}
	}

	// 1-3. 관리자 로그인 -> 일별 매출조회(일자 지정)
	public void daySalesSel(String date) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TO_CHAR(O_DATE, 'YYYY-MM-DD'), SUM(O_AMO) " + "FROM ORDERING "
				+ "WHERE TRUNC(O_DATE) = TO_DATE(?, 'YYYY-MM-DD') " + "GROUP BY TO_CHAR(O_DATE, 'YYYY-MM-DD') "
				+ "ORDER BY TO_CHAR(O_DATE, 'YYYY-MM-DD') ASC";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			rs = pstmt.executeQuery();
			System.out.println("=================================");
			System.out.println(" 일자         일별 매출금액");
			System.out.println("=================================");
			while (rs.next()) {
				String salesDate = rs.getString(1);
				int sales = rs.getInt(2);
				System.out.printf("%s   %,d원%n", salesDate, sales);
				System.out.println("─────────────────────────────────");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(conn);
		}
	}

	// 1-3. 관리자 로그인 -> 월별 매출조회(전체 월)
	public void monthSales() {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TO_CHAR(O_DATE, 'YYYY-MM'), SUM(O_AMO) " + "FROM ORDERING "
				+ "GROUP BY TO_CHAR(O_DATE, 'YYYY-MM') " + "ORDER BY TO_CHAR(O_DATE, 'YYYY-MM') ASC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("=================================");
			System.out.println("월         월별 매출금액");
			System.out.println("=================================");
			while (rs.next()) {
				String month = rs.getString(1);
				int sales = rs.getInt(2);
				System.out.printf("%s   %,d원%n", month, sales);
				System.out.println("─────────────────────────────────");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(conn);
		}
	}

	// 1-3. 관리자 로그인 -> 월별 매출조회(월 지정)
	public void monthSalesSel(String month) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT SUM(O_AMO) " + "FROM ORDERING " + "WHERE TO_CHAR(O_DATE, 'YYYY-MM') = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, month);
			rs = pstmt.executeQuery();
			System.out.println("=================================");
			System.out.println("월         총 매출금액");
			System.out.println("=================================");
			if (rs.next()) {
				int sales = rs.getInt(1);
				System.out.printf("%s   %,d원%n", month, sales);
				System.out.println("─────────────────────────────────");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(conn);
		}
	}

	// 1-4. 관리자 로그인 -> 고객 정보 확인
	public void clientList() {
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT CLI_ID, CLI_PW, CLI_NAME, CLI_TEL, CLI_TIME FROM CLIENT";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("===================================================");
			System.out.println("                  회원 정보 LIST");
			System.out.println("===================================================");
			while (rs.next()) {
				String cid = rs.getString("cli_id");
				String pw = rs.getString("cli_pw");
				String name = rs.getString("cli_name");
				String tel = rs.getString("cli_tel");
				int time = rs.getInt("cli_time");
				System.out.println("회원ID\t비밀번호\t이름\t전화번호\t\t잔여시간");
				System.out.println(cid + "\t" + pw + "\t" + name + "\t" + tel + "\t" + time);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
	}

	// 1-4. 관리자 로그인 -> 선택고객정보확인
	public void selectclient(ClientDTO dto) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("전화번호 입력: ");
		String cli_tel = sc.next();

		String sql = "SELECT * FROM CLIENT WHERE CLI_TEL = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cli_tel);
			rs = pstmt.executeQuery();

			System.out.println("===================================================");
			System.out.println("                    회원 정보 ");
			System.out.println("===================================================");

			if (rs.next()) {
				String cid = rs.getString("cli_id");
				String pw = rs.getString("cli_pw");
				String name = rs.getString("cli_name");
				String tel = rs.getString("cli_tel");
				int time = rs.getInt("cli_time");
				System.out.println("회원ID\t비밀번호\t이름\t전화번호\t\t잔여시간");
				System.out.println(cid + "\t" + pw + "\t" + name + "\t" + tel + "\t" + time);

			} else {
				System.out.println("값 없음");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(pstmt);
			util.close(conn);
		}
	}

	// 대여종료시 시간반환
	public void returnTime(String seat, String id) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT ROUND((R_END - SYSDATE) * 24 * 60) AS MIN";
		sql = sql + " FROM RENTAL WHERE CLI_ID = ? AND SEAT_NO = ?";
		sql = sql + " AND SYSDATE BETWEEN R_START AND R_END";
		String sql2 = "UPDATE CLIENT SET CLI_TIME = CLI_TIME + ? WHERE CLI_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, seat);
			rs = pstmt.executeQuery();
			rs.next();
			pstmt = null;
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, rs.getInt("MIN"));
			pstmt.setString(2, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(rs);
			util.close(conn);
		}

	}

	public void useTime(String id) { // 좌석선택하는 순간 보유시간 0으로 해주는 부분
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;

		String sql = "UPDATE CLIENT SET CLI_TIME = 0 WHERE CLI_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(conn);
		}

	}

		public void buyingList(String p_code, String o_no) { //장바구니 보여지는 부분
				Connection conn = util.getConnection();
				PreparedStatement pstmt = null;
			    ResultSet rs = null;
			    
			   // String p_code = pdto.getP_code(); 
				//String o_no = odto.getO_no();
				
				String sql = "SELECT P.P_NAME, B.B_AMO , B.B_AMO * P.P_PRICE FROM PRODUCT P";
				 sql = sql + "JOIN BUYING B ON P.P_CODE = B.P_CODE";
				 sql = sql + "AND P.P_CODE = ? AND O_NO = ?;";
				 try {
				        pstmt = conn.prepareStatement(sql);
				        pstmt.setString(1, p_code);
				        pstmt.setString(2, o_no);
				        rs = pstmt.executeQuery();
				        System.out.println("==========================");
				        System.out.println("==========================");
				        while (rs.next()) {
				            String p_name = rs.getString(1);
				            int b_amo = rs.getInt(2);
				            int money = rs.getInt(3);
				            System.out.printf("상품명: %s , 수량: %d개 , 금액: %d원 %n", p_name, b_amo, money);  
				            
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
