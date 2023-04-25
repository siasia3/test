package dto;

public class RentalDTO {
	private String r_no;
	private String r_start;
	private String r_end;
	public RentalDTO() {}
	public RentalDTO(String r_no,String r_start, String r_end) {
		this.r_no = r_no;
		this.r_start = r_start;
		this.r_end = r_end;
	}
	public String getR_no() {
		return r_no;
	}
	public void setR_no(String r_no) {
		this.r_no = r_no;
	}
	public String getR_start() {
		return r_start;
	}
	public void setR_start(String r_start) {
		this.r_start = r_start;
	}
	public String getR_end() {
		return r_end;
	}
	public void setR_end(String r_end) {
		this.r_end = r_end;
	}
}
