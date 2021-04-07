import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserData {
	private static File data = new File("userData.txt");
	
	protected static void addNewAccount(Account a, String password) {
		try {
			FileWriter writer = new FileWriter(data, true);
			if(accountExists(a)) {
				writer.close();
				throw new InputMismatchException("Account already exists");
			}
			String datum = a.forFileNoPasswordNoEndLine()+password+"\n";
			writer.write(datum);
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(InputMismatchException e) {
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
					reader.close();
					return UserType.valueOf(pieces[1]);
				}
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static boolean accountExists(Account a) {
		try {
			Scanner reader = new Scanner(data);
			String line;
			while(reader.hasNextLine()) {
				line = reader.nextLine();
				String[] pieces = line.split(",");
				if(pieces[0].equals(a.getUsername())) {
					reader.close();
					return true;
				}
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected static void removeAccount(Account a) {
		try {
			StringBuffer sb = new StringBuffer("");
			BufferedReader reader = new BufferedReader(new FileReader(data));
			String line;
			while((line=reader.readLine())!=null) {
				String[] pieces = line.split(",");
				if(!pieces[0].equals(a.getUsername())) {
					sb.append(line);
					sb.append("\n");
				}
			}
			reader.close();
			FileWriter writer=new FileWriter(data);
			writer.write(sb.toString());
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		//Account test = new Account(UserType.USER, "username");
		//addNewAccount(test, "password");
		System.out.println(login("username","password"));
		System.out.println(login("notValid","doesNotExist"));
	}
}
