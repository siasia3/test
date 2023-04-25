package dto;

public class seatDTO {
	private String seat_no;
	private char seat_use;
	public seatDTO() {}
	public seatDTO(String seat_no, char seat_use) {
		super();
		this.seat_no = seat_no;
		this.seat_use = seat_use;
	}
	public String getSeat_no() {
		return seat_no;
	}
	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}

	public char getSeat_use() {
		return seat_use;
	}
	public void setSeat_use(char seat_use) {
		this.seat_use = seat_use;
	}
}
