import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserData {
	private static File data = new File("userData.txt");
	
	protected static void addNewAccount(Account a, String password) {
		try {
			FileWriter writer = new FileWriter(data, true);
			String datum = a.forFileNoPasswordNoEndLine()+password+"\n";
			writer.write(datum);
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	protected static UserType login(String username, String password) {
		try {
			Scanner reader = new Scanner(data);
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] pieces = line.split(",");
				//index 0 is username, index 1 is type, index 2 is password
				if(pieces[0].equals(username)&&pieces[2].equals(password)) {
					return UserType.valueOf(pieces[1]);
				}
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static void main(String[] args) {
		//Account test = new Account(UserType.USER, "username");
		//addNewAccount(test, "password");
		System.out.println(login("username","password"));
		System.out.println(login("notValid","doesNotExist"));
	}
}
