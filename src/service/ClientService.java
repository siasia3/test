package service;

import java.util.Scanner;

import dao.ClientDAO;
import dao.OrderingDAO;
import dao.SeatDAO;
import dao.RentalDAO;
import dto.ClientDTO;

public class ClientService {
	Scanner sc = new Scanner(System.in);
	ClientDAO cdao = new ClientDAO();
	ClientDTO dto = new ClientDTO();
	RentalService rser = new RentalService();
	BuyingService pser = new BuyingService();
	OrderingDAO odao = new OrderingDAO();
	SeatDAO sdao = new SeatDAO();
	RentalDAO rdao = new RentalDAO();

	// 1-1. ȸ������ �޴�
	public void signupMenu() {

		System.out.println("������������������������������������������������������������������");
		System.out.println("	 S I G N U P");
		System.out.println("������������������������������������������������������������������");
		boolean vaild = true;
		String cid = "";
		String cname = "";
		String cpwd = "";
		String ctel = "";

		while (vaild) {
			System.out.print("���� ���̵� �Է� : ");
			cid = sc.next();
			
			if (cid.length() < 5) {
				System.out.println("�����������������������������޽�������������������������������");
				System.out.println("���̵�� 5���� �̻����� �Է����ּ���.");
				System.out.println("������������������������������������������������������������������");

			} else if (cid.length() > 10) {
				System.out.println("�����������������������������޽�������������������������������");
				System.out.println("���̵�� 10���� ���Ϸ� �Է����ּ���");
				System.out.println("������������������������������������������������������������������");
				
			} else if (cdao.checkId(cid)) {
				System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
				System.out.println("������������������������������������������������������������������");
			} else {
				vaild = false;
			}
		}
		vaild = true;
		while (vaild) {
			System.out.print("\n��й�ȣ �Է� : ");
			cpwd = sc.next();

			if (cpwd.length() < 5) {

				System.out.println("�����������������������������޽�������������������������������");
				System.out.println("��й�ȣ�� 5���� �̻����� �Է����ּ���.");
				System.out.println("������������������������������������������������������������������");
			} else if (cpwd.length() > 10) {
				System.out.println("�����������������������������޽�������������������������������");
				System.out.println("��й�ȣ�� 10���� ���Ϸ� �Է����ּ���");
				System.out.println("������������������������������������������������������������������");
			} else {
				vaild = false;
			}
		}
		vaild = true;
		while (vaild) {
			System.out.print("\n�̸� �Է� : ");
			cname = sc.next();

			if (cname.length() < 2) {
				System.out.println("�����������������������������޽�������������������������������");
				System.out.println("�̸��� 2���� �̻����� �Է����ּ���.");
				System.out.println("������������������������������������������������������������������");
			} else if (cname.length() > 10) {
				System.out.println("�����������������������������޽�������������������������������");
				System.out.println("�̸��� 10���� ���Ϸ� �Է����ּ���");
				System.out.println("������������������������������������������������������������������");
			} else {
				vaild = false;
			}
		}
		vaild = true;
		while (vaild) {
			System.out.println("\n����ó�� - ���� ���ڸ� �Է����ּ���.");
			System.out.print("\n����ó �Է� : ");
			ctel = sc.next();

			if (ctel.length() > 11) {
				System.out.println("�����������������������������޽�������������������������������");
				System.out.println(" ��ȭ��ȣ�� 11�ڸ� �������� �Է����ּ���.");
				System.out.println("������������������������������������������������������������������");
			}else if(cdao.checkTel(ctel)){
				System.out.println("�̹� �����ϴ� ��ȣ�Դϴ�.");
				System.out.println("������������������������������������������������������������������");
			}else {
				dto.setCli_tel(ctel);
				vaild = false;
			}
		}

			dto = new ClientDTO(cid, cpwd, cname, ctel);
			cdao.signUp(dto);
			System.out.println("������������������������������������������������������������������");
			System.out.println("STUDY247D�� ȸ���� �ǽŰ��� ȯ���մϴ�");
			System.out.println("������������������������������������������������������������������\n");
	

   }

	// 1-2. �α��� �޴�
	public void loginMenu() {
		// int no = 0;
		System.out.println("������������������������������������������������������������������");
		System.out.println("	L  O  G  I  N");
		System.out.println("������������������������������������������������������������������");
		System.out.print("���̵� �Է� : ");
		String cid = sc.next();

		System.out.print("��й�ȣ �Է� : ");
		String cpwd = sc.next();

		dto = new ClientDTO(cid, cpwd);
		int i = cdao.login(dto); // �� login�� ���ϰ��� i�� ������
		if (i == 3) {
			loginM(dto); // ������ �α��� �ҷ�����
		} else if (i == 4) {
			choiceMenu(dto); // �� �α��� �ҷ�����
		}
	}

	// 1-2. �α���_������ �α��ν� ����
	public void loginM(ClientDTO dto) {
		int sel = 0;
		boolean choice = true;
		while (choice) {

			System.out.println("������������������������������������������������������������������");
			System.out.println("������������������������ �� �� �� ������������������������");
			System.out.println("������������������������������������������������������������������\n");
			System.out.println("1. �Ϻ� ������ȸ(��ü)\n");
			System.out.println("2. �Ϻ� ������ȸ(Ư�� ���� ����)\n");
			System.out.println("3. ���� ������ȸ(��ü)\n");
			System.out.println("4. ���� ������ȸ(Ư�� �� ����)\n");
			System.out.println("5. �� ��ȸ\n");
			System.out.println("6. ������ ����Ʈ\n");
			System.out.println("7. ���θ޴�\n");
			System.out.println("8. ��������\n");
			System.out.println("������������������������������������������������������������������\n");

			System.out.print("�۾���ȣ �Է� : ");
			sel = sc.nextInt();
			System.out.println("������������������������������������������������������������������\n");

			switch (sel) {
			case 1:
				System.out.println("\n�Ϻ� ������ȸ(��ü)");
				cdao.daySales();

				break;
			case 2:
				System.out.println("\n�Ϻ� ������ȸ(Ư�� ���� ����)");
				System.out.print("��ȸ�� ���ڸ� �Է��ϼ���(YYYY-MM-DD) : ");
				String date = sc.next();
				cdao.daySalesSel(date);
				break;
			case 3:
				System.out.println("\n���� ������ȸ(��ü)");
				cdao.monthSales();
				break;
			case 4:
				System.out.println("\n���� ������ȸ(Ư�� �� ����)");
				System.out.print("��ȸ�� ���� �Է��ϼ��� (YYYY-MM): ");
				String month = sc.next();
				cdao.monthSalesSel(month);
				break;
			case 5:
				cdao.clientList();
				break;
			case 6:

				cdao.selectclient(dto);
				break;
			case 7:
				choice = false;
				break;
			case 8:
				choice = false;
				System.out.println("������������������������������������������������������������������");
				System.out.println("	  G O O D B Y E       ");
				System.out.println("������������������������������������������������������������������");
				sc.close();
				System.exit(1);

			}
		}

	}

	// 1-2. �α���_�� �α��ν� ����
	public void choiceMenu(ClientDTO dto) {
		int sel = 0;
		StringBuffer sb = new StringBuffer();
		while (sel != 5) {
			sb.append("������������������������������������������������������������������\n");
			sb.append("����������������������ȸ �� �� ����������������������������\n");
			sb.append("������������������������������������������������������������������\n");
			sb.append("1. �̿�� �� ��ǰ ����\n");
			sb.append("2. �¼��뿩\n");
			sb.append("3. �뿩����\n");
			sb.append("4. �����ð� Ȯ��\n");
			sb.append("5. �α׾ƿ�\n");
			System.out.print(sb);
			System.out.println("������������������������������������������������������������������");
			System.out.print("�۾���ȣ �Է� : ");
			sel = sc.nextInt();
			if(1>sel || sel > 5) {
				System.out.println("������������������������������������������������������������������");
				System.out.println("          �߸� �Է��ϼ̽��ϴ�         ");
				System.out.println("������������������������������������������������������������������\n");
			}
			sb.setLength(0);
			dto.setCli_time(cdao.getTime(dto.getCli_id()));
			String seat = rdao.rentalSeatCheck(dto.getCli_id());

			switch (sel) {
			case 1:
				odao.updateOrder(dto);
				String o_no = odao.getOrderNO();
				pser.choiceProductMenu(o_no, dto);
				break;
			case 2:

				String check = rdao.duplicationSeat(dto.getCli_id());
				if (check.length() < 1) {
					if (dto.getCli_time() > 0) {
						sdao.seatUpdate();
						System.out.println("������������������������������������������������������������������");
						System.out.println("�������������������������� �� �� �æ�����������������������");
						System.out.println("������������������������������������������������������������������");
						rser.choiceSeatMenu(dto);
						break;
					} else {
						System.out.println("������������������������������������������������������������������\n");
						System.out.println("           ����ð��� �����ϴ�        \n");
						System.out.println("������������������������������������������������������������������\n");
						break;
					}
				} else {
					System.out.println("������������������������������������������������������������������\n");
					System.out.println("        �¼��� �̹� ������Դϴ�       \n");
					System.out.println("������������������������������������������������������������������\n");
					break;
				}
			case 3:
				if (seat.length() > 0) {
					sdao.useOff(seat);
					cdao.returnTime(seat, dto.getCli_id());
					rdao.rentalEnd(seat, dto.getCli_id());
					System.out.println("������������������������������������������������������������������\n");
					System.out.println("      �뿩���� �¼������ �����մϴ�     \n");
					System.out.println("������������������������������������������������������������������\n");

				} else {
					System.out.println("������������������������������������������������������������������\n");
					System.out.println("     ������� �¼��� �������� �ʽ��ϴ�    \n");
					System.out.println("������������������������������������������������������������������\n");
				}

				break;
			case 4:
				rdao.checkTime(dto.getCli_id());
				break;
			case 5:
				System.out.println("������������������������������������������������������������������\n");
				System.out.println("          �α׾ƿ� �Ǽ̽��ϴ�         \n");
				System.out.println("������������������������������������������������������������������\n");
			}

		}
	}

	// 1-3. ID, PW ã��
	public void serchInfo() {
		int sel = 0;
		boolean choice = true;
		while (choice) {
			StringBuffer sb = new StringBuffer();
			sb.append("������������������������������������������������������������������\n");
			sb.append("1. ���̵� ã��\n");
			sb.append("2. ��й�ȣ ã��\n");
			sb.append("3. ����\n");
			System.out.print(sb);
			System.out.println("������������������������������������������������������������������");

			System.out.print("�۾���ȣ �Է� : ");
			sel = sc.nextInt();
			System.out.println("������������������������������������������������������������������");

			switch (sel) {
			case 1:
				System.out.println("\n ���̵� ã��");
				System.out.print("- ����ó �Է� : ");
				String ctel = sc.next();
				cdao.serchId(ctel);

				System.out.println("������������������������������������������������������������������");
				break;
			case 2:
				System.out.println("\n ��й�ȣ ã��");
				System.out.print("���̵� �Է� : ");
				String cld = sc.next();
				System.out.print("����ó �Է� : ");
				String ctel2 = sc.next();
				cdao.serchPw(cld, ctel2);

				System.out.println("������������������������������������������������������������������");
				break;
			case 3:
				choice = false;
			}
		}
	}

}