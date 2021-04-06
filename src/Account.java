
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

	@Override
	public boolean equals(Object input) {
		// TODO Auto-generated method stub
		return username.equalsIgnoreCase((String) input);
	}
	protected String forFileNoPasswordNoEndLine() {
		return username+","+type+",";
	}
}
