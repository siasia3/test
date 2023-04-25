package dto;

public class ClientDTO {
	private String cli_id;
	private String cli_pwd;
	private String cli_name;
	private String cli_tel;
	private int cli_time;
	
	public int getCli_time() {
		return cli_time;
	}
	public void setCli_time(int cli_time) {
		this.cli_time = cli_time;
	}
	public ClientDTO(){}
	public ClientDTO(String cid, String cpwd){
		this.cli_id=cid;
		this.cli_pwd=cpwd;
	}
	
	public ClientDTO(String cli_id, String cli_pwd, String cli_name, String cli_tel){
		this.cli_id=cli_id;
		this.cli_pwd=cli_pwd;
		this.cli_name=cli_name;
		this.cli_tel=cli_tel;
	}
	
	public String getCli_tel() {
		return cli_tel;
	}

	public void setCli_tel(String cli_tel) {
		this.cli_tel = cli_tel;
	}


	public String getCli_id() {
		return cli_id;
	}

	public void setCli_id(String cli_id) {
		this.cli_id = cli_id;
	}

	public String getCli_pwd() {
		return cli_pwd;
	}

	public void setCli_pwd(String cli_pwd) {
		this.cli_pwd = cli_pwd;
	}

	public String getCli_name() {
		return cli_name;
	}

	public void setCli_name(String cli_name) {
		this.cli_name = cli_name;
	}
	
	
}


