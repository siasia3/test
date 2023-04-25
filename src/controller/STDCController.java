package controller;

import java.util.Scanner;
import java.util.List;
import dao.ClientDAO;
import dao.RentalDAO;
import dao.SeatDAO;
import dto.ClientDTO;
import service.ClientService;

public class STDCController {
	private static Scanner sc = new Scanner(System.in);
	private static ClientService cser = new ClientService();

	public static int mainMenu() {
		String choice = "";
		boolean valid = true;
		int select = 0;
		System.out.println("─────────────────────────────────");
		System.out.println("	S T U D Y 2 4 7       ");
		System.out.println("─────────────────────────────────");
		System.out.println();
		System.out.println();
		System.out.println("	 1. 회 원 가 입 ");
		System.out.println();
		System.out.println("	 2. 로 그 인    ");
		System.out.println();
		System.out.println("	 3. 아이디 및 비밀번호 찾기");
		System.out.println();
		System.out.println();
		System.out.println("─────────────────────────────────");
		while (valid) {
			System.out.print("	 메뉴 선택 : ");
			choice = sc.next();
			if (choice .equals("1") || choice .equals("2") || choice .equals("3")) {
				valid = false;
			} else {
				System.out.println("─────────────────────────────────\n");
				System.out.println("       잘못된 값이 입력되었습니다.      \n");
				System.out.println("─────────────────────────────────\n");
			}
		}
		select = Integer.parseInt(choice);
		return select;
	}

	public static void main(String[] args) {
		int no = 0;
		boolean choice = true;
		while (choice) {

			no = mainMenu();
			switch (no) {
			case 1:
				cser.signupMenu();
				break;
			case 2:
				cser.loginMenu();
				break;
			case 3:
				cser.serchInfo();
				break;
			}
		}
	}

}
