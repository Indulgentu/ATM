package ok;

public interface Accounts {
	
		String countryCode = "RO";
		public String bankID = "AESB";
		public double balance = 0;
		public long accountID = 1980222420; //9 first digits of CNP + Account type
		public int accountType = 0;
		public String currencyName = "RON";
		
		public void setBalance(double val);
		public double getBalance();
		public String getAccType();
		public String getCurrency();
		public void setCurrency(String c);
}
