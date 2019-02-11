package atmproj;

import java.util.HashMap;

public class Client {

	private String FirstName;
	private String LastName;
	private String middleName = "";
	//Age
	private int Age;
	//account Id (6 digit)
	private int accID;
	//gender (F/M)
	private char Gend;
	//address
	private String clientaddr;
	//Iban - lenght 18 - First two should be letters
	private String IBAN;
	//password (4 digit)
	private int pw;
	//current money available (in RON i guess)
	private double currentmoney;
	private HashMap<String, Integer> accounts = new HashMap<>();
	
	
	public Client(String name) {
		String[] n = name.split(" ");
		if(n.length > 2) {
			this.FirstName = n[0];
			this.middleName = n[1];
			this.LastName = n[2];
		}else {
			this.FirstName = n[0];
			this.LastName = n[1];
		}
	}
	
	// getters and setters
	
	public String getFirstName() {
		return FirstName;
	}
	
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public String getMiddleName() {
		return this.middleName;
	}
	
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public int getAge() {
		return Age;
	}
	
	public void setAge(int age) {
		Age = age;
	}
	
	public int getAccID() {
		return accID;
	}
	
	public void setAccID(int accID) {
		this.accID = accID;
	}
	
	public char getGend() {
		return Gend;
	}
	
	public void setGend(char gend) {
		Gend = gend;
	}
	
	public String getClientaddr() {
		return clientaddr;
	}
	
	public void setClientaddr(String clientaddr) {
		this.clientaddr = clientaddr;
	}
	
	public String getIBAN() {
		return IBAN;
	}
	
	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	
	public int getPw() {
		return pw;
	}
	
	public void setPw(int pw) {
		this.pw = pw;
	}
	
	public double getCurrentmoney() {
		return currentmoney;
	}
	
	public void setCurrentmoney(double currentmoney) {
		this.currentmoney = currentmoney;
	}
	
	public HashMap<String, Integer> getAccounts(){
		return this.accounts;
	}
	
	public void addAccount(String IBAN, int type) {
		this.accounts.put(IBAN, type);
	}

}
