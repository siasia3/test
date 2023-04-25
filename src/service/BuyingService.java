package service;

import java.util.Scanner;

import dao.BuyingDAO;
import dao.ClientDAO;
import dao.OrderingDAO;
import dto.BuyingDTO;
import dto.ClientDTO;
import dto.ProductDTO;

public class BuyingService {
	Scanner sc = new Scanner(System.in);
	ProductDTO pdto = new ProductDTO();
	BuyingDTO bdto = new BuyingDTO();
	BuyingDAO bdao = new BuyingDAO();
	OrderingDAO odao = new OrderingDAO();
	ClientDAO cdao = new ClientDAO();

	public void choiceProductMenu(String o_no, ClientDTO dto) {
		String select = "";
		int amount = 0;
		boolean choice = true;
		while (choice) {
			int pick = 0;
			System.out.println("─────────────────────────────────");
			System.out.println("	   M  E  N  U               ");
			System.out.println("─────────────────────────────────");
			System.out.println("1.  1시간 대여권  -  1000원\n");
			System.out.println("2.  콜라  -  1000원\n");
			System.out.println("3.  사이다  -  1000원\n");
			System.out.println("4.  제로콜라  - 1000원\n");
			System.out.println("5.  아메리카노  - 1500원\n");
			System.out.println("6.  바닐라라떼  - 2000원\n");
			System.out.println("7.  카라멜마끼아또  - 2000원\n");
			System.out.println("8.  카페모카  - 2000원\n");
			System.out.println("9.  아이스초코  -  2000원\n");
			System.out.println("10. 핫초코  -  2000원\n");
			System.out.println("11. 가나초콜릿  -  1000원\n");
			System.out.println("12. 잠깨는껌  -  3000원\n");
			System.out.println("13. 이즈니휘낭시에  -  1500원\n");
			System.out.println("14. 뒤로가기\n");
			System.out.println("15. 장바구니 확인 및 결제하기\n");
			System.out.println("─────────────────────────────────");
			System.out.print("	선택 번호 입력 : ");
			select = sc.next();
			System.out.println("─────────────────────────────────");
		
			if (select.equals("14")) {
				choice = false;
				bdao.deleteBasket();
			} else if (select.equals("15")) {
				bdao.buyingList(o_no);
				while (1 > pick || pick > 3) {
					System.out.println("─────────────────────────────────");
					System.out.println("1. 결제하기 2. 장바구니 비우기 3. 뒤로가기");
					System.out.println("─────────────────────────────────");
					System.out.print("  선택 번호 입력 : ");
					pick = sc.nextInt();
					if (0 < pick && pick < 4) {
						if (pick == 1) {
							odao.payment(o_no);
							cdao.updateTime(dto, o_no);
							choice = false;
							System.out.println("─────────────────────────────────");
							System.out.println("         결제가 완료되었습니다         ");
							System.out.println("─────────────────────────────────\n");
						} else if (pick == 2) {
							bdao.deleteBasket();
						} else {

						}
					} else {
						System.out.println("번호를 잘못입력하셨습니다. 다시 입력해주세요.");
					}
				}

			} else {
				System.out.print("수량 입력 : ");
				amount = sc.nextInt();

				if (bdao.checkAmount(select, o_no) > 0) {
					bdao.addAmonut(select, amount, o_no);
					System.out.println("장바구니에 수량이 추가되었습니다.");

				} else {
					bdao.insertBasket(select, amount, o_no);
					System.out.println("장바구니에 담겼습니다");

				}

			}

		}
	}
}
