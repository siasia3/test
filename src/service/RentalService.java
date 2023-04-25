package service;

import java.util.Scanner;

import dao.SeatDAO;
import dao.ClientDAO;
import dao.RentalDAO;
import dto.ClientDTO;
import dto.seatDTO;

public class RentalService {
	seatDTO sdto = new seatDTO();
	SeatDAO sdao = new SeatDAO();
	RentalDAO rdao = new RentalDAO();
	ClientDAO cdao = new ClientDAO();

	public void choiceSeatMenu(ClientDTO dto) { // �¼� ���ø޴�
		String[] cliInfo = new String[3];
		String seatNum;
		String[][] seat = new String[4][10];
		Scanner sc = new Scanner(System.in);
		seat = sdao.seatCheck();
		boolean possible = true;
		System.out.println("   1  2  3  4  5  6  7  8  9  10");
		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				System.out.print("A");
			} else if (i == 1) {
				System.out.print("B");
			} else if (i == 2) {
				System.out.print("C");
			} else {
				System.out.print("D");
			}
			for (int j = 0; j < 10; j++) {
				System.out.print("  "+seat[i][j]); 
			}
			System.out.println();
		}
		
		System.out.println("\n\u25A0�� �����, \u25A1�� ��� ����(�Է¿���: A01,B05)");
		System.out.println("������������������������������������������������������������������");
		while (possible) {
			System.out.print("�¼����� : ");
			seatNum = sc.next();
			sdto.setSeat_no(seatNum);

			if (sdao.UsingCheck(seatNum)) {
				rdao.rental(dto, seatNum);
				cdao.useTime(dto.getCli_id());
				System.out.println();
				System.out.println("������������������������������������������������������������������\n");
				System.out.println("       " + seatNum + " �¼��� �뿩�Ǿ����ϴ�.\n");
				System.out.println("������������������������������������������������������������������");
				possible = false;
			} else {
				System.out.println("������������������������������������������������������������������\n");
				System.out.println("        �̹� ������� �ڸ��Դϴ�.       \n");
				System.out.println("        �¼��� �ٽ� �������ּ���.      \n");
				System.out.println("������������������������������������������������������������������");
			}
		}

	}

}
