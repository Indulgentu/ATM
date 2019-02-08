package atmproj;

public interface Accounts {
	public double balance = 0;
	public String accountID = "";
	public int accountType = 0;
	public String currencyName = "RON";
	
	public void setBalance(double val);
	public double getBalance();
	public int getAccType();
	public String getCurrency();
	public void setCurrency();
}
