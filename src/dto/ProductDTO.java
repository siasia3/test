package dto;

public class ProductDTO {
	private String p_code;
	private String p_name;
	private int p_price;
	
	public ProductDTO() {}
	public ProductDTO(String p_code, String p_name, int p_price) {
		this.p_code = p_code;
		this.p_name = p_name;
		this.p_price = p_price;
	}
	
	public String getP_code() {
		return p_code;
	}
	
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
}
