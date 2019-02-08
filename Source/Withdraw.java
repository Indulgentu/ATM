package atmproj;
import java.util.Scanner;

public abstract class Withdraw implements Accounts  {
	private static Scanner in;
//	private double Balance = balance;
	{
	in= new Scanner(System.in);
	  float amount; 
      System.out.println("Please enter amount to withdraw: "); 
      amount = in.nextFloat();
      if(amount > Accounts.balance || amount == 0){
          System.out.println("You have insufficient funds\n\n"); 
          // ask if they want another transaction
      } else {
          // they have some cash
          // update balance 
          Accounts.balance = Accounts.balance - amount; 
          System.out.println("You have withdrawn "+amount+" and your new balance is "+balance+"\n");
        
      } 
      }
}

      
