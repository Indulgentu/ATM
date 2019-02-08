package atmproj;

import java.util.Scanner;

public class Main {
	
	
	private static boolean running = false;
	public static String command;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Please enter your name");
		Client cl = new Client(sc.nextLine());
		System.out.println("Welcome, " + cl.getFirstName() + " " + cl.getLastName() + " " + cl.getMiddleName());
		running = true;
		
		while(running){
			command = sc.nextLine();
			switch(command) {
			case "Exit" : running = false;
			break;
			
			case "Balance" : 
				System.out.println("Balance for current account: " + cl.getCurrentmoney() + Accounts.currencyName);
			break;	
			
			case "Transfer" : 
			break;
			
			case "Withdraw" :
			break;
			
			default: break;
			
			
			
			}
			
		}
	}
	
}
