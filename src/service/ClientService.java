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

	// 1-1. È¸¿ø°¡ÀÔ ¸Þ´º
	public void signupMenu() {

		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println("	 S I G N U P");
		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		boolean vaild = true;
		String cid = "";
		String cname = "";
		String cpwd = "";
		String ctel = "";

		while (vaild) {
			System.out.print("°¡ÀÔ ¾ÆÀÌµð ÀÔ·Â : ");
			cid = sc.next();
			
			if (cid.length() < 5) {
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¿À·ù¸Þ½ÃÁö¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				System.out.println("¾ÆÀÌµð´Â 5±ÛÀÚ ÀÌ»óÀ¸·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");

			} else if (cid.length() > 10) {
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¿À·ù¸Þ½ÃÁö¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				System.out.println("¾ÆÀÌµð´Â 10±ÛÀÚ ÀÌÇÏ·Î ÀÔ·ÂÇØÁÖ¼¼¿ä");
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				
			} else if (cdao.checkId(cid)) {
				System.out.println("ÀÌ¹Ì Á¸ÀçÇÏ´Â ¾ÆÀÌµðÀÔ´Ï´Ù.");
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			} else {
				vaild = false;
			}
		}
		vaild = true;
		while (vaild) {
			System.out.print("\nºñ¹Ð¹øÈ£ ÀÔ·Â : ");
			cpwd = sc.next();

			if (cpwd.length() < 5) {

				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¿À·ù¸Þ½ÃÁö¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				System.out.println("ºñ¹Ð¹øÈ£´Â 5±ÛÀÚ ÀÌ»óÀ¸·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			} else if (cpwd.length() > 10) {
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¿À·ù¸Þ½ÃÁö¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				System.out.println("ºñ¹Ð¹øÈ£´Â 10±ÛÀÚ ÀÌÇÏ·Î ÀÔ·ÂÇØÁÖ¼¼¿ä");
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			} else {
				vaild = false;
			}
		}
		vaild = true;
		while (vaild) {
			System.out.print("\nÀÌ¸§ ÀÔ·Â : ");
			cname = sc.next();

			if (cname.length() < 2) {
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¿À·ù¸Þ½ÃÁö¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				System.out.println("ÀÌ¸§Àº 2±ÛÀÚ ÀÌ»óÀ¸·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			} else if (cname.length() > 10) {
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¿À·ù¸Þ½ÃÁö¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				System.out.println("ÀÌ¸§Àº 10±ÛÀÚ ÀÌÇÏ·Î ÀÔ·ÂÇØÁÖ¼¼¿ä");
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			} else {
				vaild = false;
			}
		}
		vaild = true;
		while (vaild) {
			System.out.println("\n¿¬¶ôÃ³´Â - »©°í ¼ýÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			System.out.print("\n¿¬¶ôÃ³ ÀÔ·Â : ");
			ctel = sc.next();

			if (ctel.length() > 11) {
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¿À·ù¸Þ½ÃÁö¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				System.out.println(" ÀüÈ­¹øÈ£´Â 11ÀÚ¸® ÀÌÇÏÀ¸·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			}else if(cdao.checkTel(ctel)){
				System.out.println("ÀÌ¹Ì Á¸ÀçÇÏ´Â ¹øÈ£ÀÔ´Ï´Ù.");
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			}else {
				dto.setCli_tel(ctel);
				vaild = false;
			}
		}

			dto = new ClientDTO(cid, cpwd, cname, ctel);
			cdao.signUp(dto);
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("STUDY247DÀÇ È¸¿øÀÌ µÇ½Å°ÍÀ» È¯¿µÇÕ´Ï´Ù");
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
	

   }

	// 1-2. ·Î±×ÀÎ ¸Þ´º
	public void loginMenu() {
		// int no = 0;
		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println("	L  O  G  I  N");
		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.print("¾ÆÀÌµð ÀÔ·Â : ");
		String cid = sc.next();

		System.out.print("ºñ¹Ð¹øÈ£ ÀÔ·Â : ");
		String cpwd = sc.next();

		dto = new ClientDTO(cid, cpwd);
		int i = cdao.login(dto); // ¡Ú loginÀÇ ¸®ÅÏ°ªÀ» i¿¡ ÀúÀåÇÔ
		if (i == 3) {
			loginM(dto); // °ü¸®ÀÚ ·Î±×ÀÎ ºÒ·¯¿À±â
		} else if (i == 4) {
			choiceMenu(dto); // °í°´ ·Î±×ÀÎ ºÒ·¯¿À±â
		}
	}

	// 1-2. ·Î±×ÀÎ_°ü¸®ÀÚ ·Î±×ÀÎ½Ã ¿¬°á
	public void loginM(ClientDTO dto) {
		int sel = 0;
		boolean choice = true;
		while (choice) {

			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡°ü ¸® ÀÚ ¸Þ ´º¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
			System.out.println("1. ÀÏº° ¸ÅÃâÁ¶È¸(ÀüÃ¼)\n");
			System.out.println("2. ÀÏº° ¸ÅÃâÁ¶È¸(Æ¯Á¤ ÀÏÀÚ ÁöÁ¤)\n");
			System.out.println("3. ¿ùº° ¸ÅÃâÁ¶È¸(ÀüÃ¼)\n");
			System.out.println("4. ¿ùº° ¸ÅÃâÁ¶È¸(Æ¯Á¤ ¿ù ÁöÁ¤)\n");
			System.out.println("5. °í°´ Á¶È¸\n");
			System.out.println("6. °í°´Á¤º¸ ¸®½ºÆ®\n");
			System.out.println("7. ¸ÞÀÎ¸Þ´º\n");
			System.out.println("8. ¿ÏÀüÁ¾·á\n");
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");

			System.out.print("ÀÛ¾÷¹øÈ£ ÀÔ·Â : ");
			sel = sc.nextInt();
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");

			switch (sel) {
			case 1:
				System.out.println("\nÀÏº° ¸ÅÃâÁ¶È¸(ÀüÃ¼)");
				cdao.daySales();

				break;
			case 2:
				System.out.println("\nÀÏº° ¸ÅÃâÁ¶È¸(Æ¯Á¤ ÀÏÀÚ ÁöÁ¤)");
				System.out.print("Á¶È¸ÇÒ ÀÏÀÚ¸¦ ÀÔ·ÂÇÏ¼¼¿ä(YYYY-MM-DD) : ");
				String date = sc.next();
				cdao.daySalesSel(date);
				break;
			case 3:
				System.out.println("\n¿ùº° ¸ÅÃâÁ¶È¸(ÀüÃ¼)");
				cdao.monthSales();
				break;
			case 4:
				System.out.println("\n¿ùº° ¸ÅÃâÁ¶È¸(Æ¯Á¤ ¿ù ÁöÁ¤)");
				System.out.print("Á¶È¸ÇÒ ¿ùÀ» ÀÔ·ÂÇÏ¼¼¿ä (YYYY-MM): ");
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
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				System.out.println("	  G O O D B Y E       ");
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				sc.close();
				System.exit(1);

			}
		}

	}

	// 1-2. ·Î±×ÀÎ_°í°´ ·Î±×ÀÎ½Ã ¿¬°á
	public void choiceMenu(ClientDTO dto) {
		int sel = 0;
		StringBuffer sb = new StringBuffer();
		while (sel != 5) {
			sb.append("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
			sb.append("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡È¸ ¿ø ¸Þ ´º¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
			sb.append("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
			sb.append("1. ÀÌ¿ë±Ç ¹× »óÇ° ±¸¸Å\n");
			sb.append("2. ÁÂ¼®´ë¿©\n");
			sb.append("3. ´ë¿©Á¾·á\n");
			sb.append("4. ³²Àº½Ã°£ È®ÀÎ\n");
			sb.append("5. ·Î±×¾Æ¿ô\n");
			System.out.print(sb);
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.print("ÀÛ¾÷¹øÈ£ ÀÔ·Â : ");
			sel = sc.nextInt();
			if(1>sel || sel > 5) {
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				System.out.println("          Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù         ");
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
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
						System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
						System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡ÁÂ ¼® ¼± ÅÃ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
						System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
						rser.choiceSeatMenu(dto);
						break;
					} else {
						System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
						System.out.println("           ÀúÀå½Ã°£ÀÌ ¾ø½À´Ï´Ù        \n");
						System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
						break;
					}
				} else {
					System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
					System.out.println("        ÁÂ¼®À» ÀÌ¹Ì »ç¿ëÁßÀÔ´Ï´Ù       \n");
					System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
					break;
				}
			case 3:
				if (seat.length() > 0) {
					sdao.useOff(seat);
					cdao.returnTime(seat, dto.getCli_id());
					rdao.rentalEnd(seat, dto.getCli_id());
					System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
					System.out.println("      ´ë¿©ÁßÀÎ ÁÂ¼®»ç¿ëÀ» Á¾·áÇÕ´Ï´Ù     \n");
					System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");

				} else {
					System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
					System.out.println("     »ç¿ëÁßÀÎ ÁÂ¼®ÀÌ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù    \n");
					System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
				}

				break;
			case 4:
				rdao.checkTime(dto.getCli_id());
				break;
			case 5:
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
				System.out.println("          ·Î±×¾Æ¿ô µÇ¼Ì½À´Ï´Ù         \n");
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
			}

		}
	}

	// 1-3. ID, PW Ã£±â
	public void serchInfo() {
		int sel = 0;
		boolean choice = true;
		while (choice) {
			StringBuffer sb = new StringBuffer();
			sb.append("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡\n");
			sb.append("1. ¾ÆÀÌµð Ã£±â\n");
			sb.append("2. ºñ¹Ð¹øÈ£ Ã£±â\n");
			sb.append("3. Á¾·á\n");
			System.out.print(sb);
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");

			System.out.print("ÀÛ¾÷¹øÈ£ ÀÔ·Â : ");
			sel = sc.nextInt();
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");

			switch (sel) {
			case 1:
				System.out.println("\n ¾ÆÀÌµð Ã£±â");
				System.out.print("- ¿¬¶ôÃ³ ÀÔ·Â : ");
				String ctel = sc.next();
				cdao.serchId(ctel);

				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				break;
			case 2:
				System.out.println("\n ºñ¹Ð¹øÈ£ Ã£±â");
				System.out.print("¾ÆÀÌµð ÀÔ·Â : ");
				String cld = sc.next();
				System.out.print("¿¬¶ôÃ³ ÀÔ·Â : ");
				String ctel2 = sc.next();
				cdao.serchPw(cld, ctel2);

				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				break;
			case 3:
				choice = false;
			}
		}
	}

}