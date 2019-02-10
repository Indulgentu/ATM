package ok;

import java.util.HashMap;

public class Client {

	private String FirstName;
	private String LastName;
	private String middleName = "";
	private String CNP = "";
	private String registered = "";
	private String clientaddr;
	private int Age;
	private int accID;
	private int Gend;
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
	
	public int getGend() {
		return this.Gend;
	}
	
	public void setGend(int g) {
		this.Gend = g;
	}
	
	public String getClientaddr() {
		return clientaddr;
	}
	
	public void setClientaddr(String clientaddr) {
		this.clientaddr = clientaddr;
	}
	
	public String getCNP() {
		return this.CNP;
	}
	
	public HashMap<String, Integer> getAccounts(){
		return this.accounts;
	}
	
	public void addAccount(String IBAN, int type) {
		this.accounts.put(IBAN, type);
	}

	public void auth(String nextLine) {
		
	}

	public void setMiddleName(String string) {
		this.middleName = string;
	}
	
	public void setCNP(String CNP) {
		this.CNP = CNP;
	}

	public void setRegDate(String date) {
		this.registered = date;
	}

}