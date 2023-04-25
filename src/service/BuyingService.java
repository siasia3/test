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
			System.out.println("������������������������������������������������������������������");
			System.out.println("	   M  E  N  U               ");
			System.out.println("������������������������������������������������������������������");
			System.out.println("1.  1�ð� �뿩��  -  1000��\n");
			System.out.println("2.  �ݶ�  -  1000��\n");
			System.out.println("3.  ���̴�  -  1000��\n");
			System.out.println("4.  �����ݶ�  - 1000��\n");
			System.out.println("5.  �Ƹ޸�ī��  - 1500��\n");
			System.out.println("6.  �ٴҶ��  - 2000��\n");
			System.out.println("7.  ī��Ḷ���ƶ�  - 2000��\n");
			System.out.println("8.  ī���ī  - 2000��\n");
			System.out.println("9.  ���̽�����  -  2000��\n");
			System.out.println("10. ������  -  2000��\n");
			System.out.println("11. �������ݸ�  -  1000��\n");
			System.out.println("12. ����²�  -  3000��\n");
			System.out.println("13. ������ֳ��ÿ�  -  1500��\n");
			System.out.println("14. �ڷΰ���\n");
			System.out.println("15. ��ٱ��� Ȯ�� �� �����ϱ�\n");
			System.out.println("������������������������������������������������������������������");
			System.out.print("	���� ��ȣ �Է� : ");
			select = sc.next();
			System.out.println("������������������������������������������������������������������");
		
			if (select.equals("14")) {
				choice = false;
				bdao.deleteBasket();
			} else if (select.equals("15")) {
				bdao.buyingList(o_no);
				while (1 > pick || pick > 3) {
					System.out.println("������������������������������������������������������������������");
					System.out.println("1. �����ϱ� 2. ��ٱ��� ���� 3. �ڷΰ���");
					System.out.println("������������������������������������������������������������������");
					System.out.print("  ���� ��ȣ �Է� : ");
					pick = sc.nextInt();
					if (0 < pick && pick < 4) {
						if (pick == 1) {
							odao.payment(o_no);
							cdao.updateTime(dto, o_no);
							choice = false;
							System.out.println("������������������������������������������������������������������");
							System.out.println("         ������ �Ϸ�Ǿ����ϴ�         ");
							System.out.println("������������������������������������������������������������������\n");
						} else if (pick == 2) {
							bdao.deleteBasket();
						} else {

						}
					} else {
						System.out.println("��ȣ�� �߸��Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
					}
				}

			} else {
				System.out.print("���� �Է� : ");
				amount = sc.nextInt();

				if (bdao.checkAmount(select, o_no) > 0) {
					bdao.addAmonut(select, amount, o_no);
					System.out.println("��ٱ��Ͽ� ������ �߰��Ǿ����ϴ�.");

				} else {
					bdao.insertBasket(select, amount, o_no);
					System.out.println("��ٱ��Ͽ� �����ϴ�");

				}

			}

		}
	}
}
