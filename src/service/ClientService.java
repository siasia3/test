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

	// 1-1. �蛾灠㊣� 詭景
	public void signupMenu() {

		System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("	 S I G N U P");
		System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		boolean vaild = true;
		String cid = "";
		String cname = "";
		String cpwd = "";
		String ctel = "";

		while (vaild) {
			System.out.print("陛殮 嬴檜蛤 殮溘 : ");
			cid = sc.next();
			
			if (cid.length() < 5) {
				System.out.println("式式式式式式式式式式式式螃盟詭衛雖式式式式式式式式式式式式式式");
				System.out.println("嬴檜蛤朝 5旋濠 檜鼻戲煎 殮溘п輿撮蹂.");
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");

			} else if (cid.length() > 10) {
				System.out.println("式式式式式式式式式式式式螃盟詭衛雖式式式式式式式式式式式式式式");
				System.out.println("嬴檜蛤朝 10旋濠 檜ж煎 殮溘п輿撮蹂");
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				
			} else if (cdao.checkId(cid)) {
				System.out.println("檜嘐 襄營ж朝 嬴檜蛤殮棲棻.");
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			} else {
				vaild = false;
			}
		}
		vaild = true;
		while (vaild) {
			System.out.print("\n綠塵廓�� 殮溘 : ");
			cpwd = sc.next();

			if (cpwd.length() < 5) {

				System.out.println("式式式式式式式式式式式式螃盟詭衛雖式式式式式式式式式式式式式式");
				System.out.println("綠塵廓�ㄣ� 5旋濠 檜鼻戲煎 殮溘п輿撮蹂.");
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			} else if (cpwd.length() > 10) {
				System.out.println("式式式式式式式式式式式式螃盟詭衛雖式式式式式式式式式式式式式式");
				System.out.println("綠塵廓�ㄣ� 10旋濠 檜ж煎 殮溘п輿撮蹂");
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			} else {
				vaild = false;
			}
		}
		vaild = true;
		while (vaild) {
			System.out.print("\n檜葷 殮溘 : ");
			cname = sc.next();

			if (cname.length() < 2) {
				System.out.println("式式式式式式式式式式式式螃盟詭衛雖式式式式式式式式式式式式式式");
				System.out.println("檜葷擎 2旋濠 檜鼻戲煎 殮溘п輿撮蹂.");
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			} else if (cname.length() > 10) {
				System.out.println("式式式式式式式式式式式式螃盟詭衛雖式式式式式式式式式式式式式式");
				System.out.println("檜葷擎 10旋濠 檜ж煎 殮溘п輿撮蹂");
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			} else {
				vaild = false;
			}
		}
		vaild = true;
		while (vaild) {
			System.out.println("\n翱塊籀朝 - 貍堅 璋濠虜 殮溘п輿撮蹂.");
			System.out.print("\n翱塊籀 殮溘 : ");
			ctel = sc.next();

			if (ctel.length() > 11) {
				System.out.println("式式式式式式式式式式式式螃盟詭衛雖式式式式式式式式式式式式式式");
				System.out.println(" 瞪�食醽ㄣ� 11濠葬 檜ж戲煎 殮溘п輿撮蹂.");
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			}else if(cdao.checkTel(ctel)){
				System.out.println("檜嘐 襄營ж朝 廓��殮棲棻.");
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			}else {
				dto.setCli_tel(ctel);
				vaild = false;
			}
		}

			dto = new ClientDTO(cid, cpwd, cname, ctel);
			cdao.signUp(dto);
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("STUDY247D曖 �蛾衋� 腎褐匙擊 �紊腎桭炴�");
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
	

   }

	// 1-2. 煎斜檣 詭景
	public void loginMenu() {
		// int no = 0;
		System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println("	L  O  G  I  N");
		System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.print("嬴檜蛤 殮溘 : ");
		String cid = sc.next();

		System.out.print("綠塵廓�� 殮溘 : ");
		String cpwd = sc.next();

		dto = new ClientDTO(cid, cpwd);
		int i = cdao.login(dto); // ≠ login曖 葬欐高擊 i縑 盪濰л
		if (i == 3) {
			loginM(dto); // 婦葬濠 煎斜檣 碳楝螃晦
		} else if (i == 4) {
			choiceMenu(dto); // 堅偌 煎斜檣 碳楝螃晦
		}
	}

	// 1-2. 煎斜檣_婦葬濠 煎斜檣衛 翱唸
	public void loginM(ClientDTO dto) {
		int sel = 0;
		boolean choice = true;
		while (choice) {

			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("式式式式式式式式式式式婦 葬 濠 詭 景式式式式式式式式式式式");
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
			System.out.println("1. 橾滌 衙轎褻��(瞪羹)\n");
			System.out.println("2. 橾滌 衙轎褻��(か薑 橾濠 雖薑)\n");
			System.out.println("3. 錯滌 衙轎褻��(瞪羹)\n");
			System.out.println("4. 錯滌 衙轎褻��(か薑 錯 雖薑)\n");
			System.out.println("5. 堅偌 褻�稞n");
			System.out.println("6. 堅偌薑爾 葬蝶お\n");
			System.out.println("7. 詭檣詭景\n");
			System.out.println("8. 諫瞪謙猿\n");
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");

			System.out.print("濛機廓�� 殮溘 : ");
			sel = sc.nextInt();
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");

			switch (sel) {
			case 1:
				System.out.println("\n橾滌 衙轎褻��(瞪羹)");
				cdao.daySales();

				break;
			case 2:
				System.out.println("\n橾滌 衙轎褻��(か薑 橾濠 雖薑)");
				System.out.print("褻�裔� 橾濠蒂 殮溘ж撮蹂(YYYY-MM-DD) : ");
				String date = sc.next();
				cdao.daySalesSel(date);
				break;
			case 3:
				System.out.println("\n錯滌 衙轎褻��(瞪羹)");
				cdao.monthSales();
				break;
			case 4:
				System.out.println("\n錯滌 衙轎褻��(か薑 錯 雖薑)");
				System.out.print("褻�裔� 錯擊 殮溘ж撮蹂 (YYYY-MM): ");
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
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("	  G O O D B Y E       ");
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				sc.close();
				System.exit(1);

			}
		}

	}

	// 1-2. 煎斜檣_堅偌 煎斜檣衛 翱唸
	public void choiceMenu(ClientDTO dto) {
		int sel = 0;
		StringBuffer sb = new StringBuffer();
		while (sel != 5) {
			sb.append("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
			sb.append("式式式式式式式式式式式�� 錳 詭 景式式式式式式式式式式式式式\n");
			sb.append("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
			sb.append("1. 檜辨掏 塽 鼻ヶ 掘衙\n");
			sb.append("2. 謝戮渠罹\n");
			sb.append("3. 渠罹謙猿\n");
			sb.append("4. 陴擎衛除 �挫垥n");
			sb.append("5. 煎斜嬴醒\n");
			System.out.print(sb);
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.print("濛機廓�� 殮溘 : ");
			sel = sc.nextInt();
			if(1>sel || sel > 5) {
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				System.out.println("          澀跤 殮溘ж樟蝗棲棻         ");
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
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
						System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
						System.out.println("式式式式式式式式式式式式謝 戮 摹 鷗式式式式式式式式式式式式");
						System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
						rser.choiceSeatMenu(dto);
						break;
					} else {
						System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
						System.out.println("           盪濰衛除檜 橈蝗棲棻        \n");
						System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
						break;
					}
				} else {
					System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
					System.out.println("        謝戮擊 檜嘐 餌辨醞殮棲棻       \n");
					System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
					break;
				}
			case 3:
				if (seat.length() > 0) {
					sdao.useOff(seat);
					cdao.returnTime(seat, dto.getCli_id());
					rdao.rentalEnd(seat, dto.getCli_id());
					System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
					System.out.println("      渠罹醞檣 謝戮餌辨擊 謙猿м棲棻     \n");
					System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");

				} else {
					System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
					System.out.println("     餌辨醞檣 謝戮檜 襄營ж雖 彊蝗棲棻    \n");
					System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
				}

				break;
			case 4:
				rdao.checkTime(dto.getCli_id());
				break;
			case 5:
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
				System.out.println("          煎斜嬴醒 腎樟蝗棲棻         \n");
				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
			}

		}
	}

	// 1-3. ID, PW 瓊晦
	public void serchInfo() {
		int sel = 0;
		boolean choice = true;
		while (choice) {
			StringBuffer sb = new StringBuffer();
			sb.append("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式\n");
			sb.append("1. 嬴檜蛤 瓊晦\n");
			sb.append("2. 綠塵廓�� 瓊晦\n");
			sb.append("3. 謙猿\n");
			System.out.print(sb);
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");

			System.out.print("濛機廓�� 殮溘 : ");
			sel = sc.nextInt();
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");

			switch (sel) {
			case 1:
				System.out.println("\n 嬴檜蛤 瓊晦");
				System.out.print("- 翱塊籀 殮溘 : ");
				String ctel = sc.next();
				cdao.serchId(ctel);

				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				break;
			case 2:
				System.out.println("\n 綠塵廓�� 瓊晦");
				System.out.print("嬴檜蛤 殮溘 : ");
				String cld = sc.next();
				System.out.print("翱塊籀 殮溘 : ");
				String ctel2 = sc.next();
				cdao.serchPw(cld, ctel2);

				System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
				break;
			case 3:
				choice = false;
			}
		}
	}

}