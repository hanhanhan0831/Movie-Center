
public class Account {

	private UserType type;
	private String username;
	
	protected Account(UserType type, String username) {
		this.type = type;
		this.username = username;
	}
	
	
	public UserType getType() {
		return type;
	}
	protected void setType(UserType type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	protected void setUsername(String username) {
		this.username = username;
	}
	
	protected String forFileNoPasswordNoEndLine() {
		return username+","+type+",";
	}

}
