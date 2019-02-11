package ok;

import java.text.DecimalFormat;

public class Account implements Accounts {
	
	private double Balance = balance;
	private long accID = accountID;
	private String IBAN = "";
	private int AccountType = accountType;
	private String accountCurrency = currencyName;
	
	public Account(String IBAN, int type, long accID, String currency) {
		this.accID = (accID == 0L) ? this.genNewID() : accID;
		this.IBAN = (IBAN.isEmpty() || IBAN == null || IBAN == "0") ? this.genNewIBAN() : IBAN;
		this.AccountType = type;
		this.accountCurrency = currency;
	}
	
	private long genNewID() {
		String CNP = ClientHandler.getClient().getCNP().substring(0, 9) + this.AccountType;
		System.out.println("Generating new ID " + CNP);
		return Long.valueOf(CNP);
	}
	
	public long getAccID() {
		return this.accID;
	}

	@Override
	public void setBalance(double val) {
		this.Balance = val;
	}

	@Override
	public double getBalance() {
		return Double.valueOf(new DecimalFormat("##.##").format(this.Balance));
	}
	
	@Override
	public String getAccType() {
/* Checking account: A checking account offers easy access to your money for your daily transactional needs and helps keep your cash secure. Customers can use a debit card or checks to make purchases or pay bills. Accounts may have different options or packages to help avoid certain monthly service fees. To determine the most economical choice, compare the benefits of different checking packages with the services you actually need.
Savings account: A savings account allows you to accumulate interest on funds you’ve saved for future needs. Interest rates can be compounded on a daily, weekly, monthly, or annual basis. Savings accounts vary by monthly service fees, interest rates, method used to calculate interest, and minimum opening deposit. Understanding the account’s terms and benefits will allow for a more informed decision on the account best suited for your needs.
Certificate of Deposit (CD): Certificates of deposit, or CDs, allow you to invest your money at a set interest rate for a pre-set period of time. CDs often have higher interest rates than traditional savings accounts because the money you deposit is tied up for the life of the certificate – which can range from a few months to several years. Be sure you do not need to draw on those funds before you open a CD, as early withdrawals may have financial penalties. 
Money market account: Money market accounts are similar to savings accounts, but they require you to maintain a higher balance to avoid a monthly service fee. Where savings accounts usually have a fixed interest rate, these accounts have rates that vary regularly based on money markets. Money market accounts can have tiered interest rates, providing more favorable rates based on higher balances. Some money market accounts also allow you to write checks against your funds, but on a more limited basis.
Individual Retirement Accounts (IRAs): IRAs, or individual retirement accounts, allow you to save independently for your retirement. These plans are useful if your employer doesn’t offer retirement benefits or you want to save more than your employer-sponsored plan allows. These accounts come in two types: the traditional IRA and Roth IRA. The Roth IRA is popular because the funds can be withdrawn tax-free in many situations. Others prefer traditional IRAs because these contributions are tax-deductible. Both accounts have contribution limits and other requirements you may need to discuss with your tax advisor before choosing your account.*/
	
		switch(this.AccountType) {
			case 0: return "Current";
			case 1: return "Savings";
			case 2: return "Certificate of Deposit";
			case 3: return "Money market account";
			case 4: return "Individual Retirement Accounts";
			default: return "NONE";
		}
	}
	
	public int getType() {
		return this.AccountType;
	}

	@Override
	public String getCurrency() {
		return this.accountCurrency;
	}

	@Override
	public void setCurrency(String c) {
		this.accountCurrency = c;
	}
	
	public String getIBAN() {
		return this.IBAN;
	}
	
	public boolean validateIBAN() {
		if((genCheckDigit(this.getIBAN().substring(0, 2) + "00" + this.getIBAN().substring(4)) + 98) != 1) {
			System.out.println(genCheckDigit(this.getIBAN().substring(0, 2) + "00" + this.getIBAN().substring(4)) + 98);
			return false;
		}
		return true;
	}
	
	public String genNewIBAN() {
		System.out.println("Generating new IBAN for " + this.getAccID());
		String initial = countryCode + "00" + bankID + "000000" + this.accID;// "RO03BAMB0000001234567890";
		String rest = initial.substring(0, 2) + "00" + initial.substring(4);
		int checkDigit = genCheckDigit(rest);
		String cD = (checkDigit < 10) ?  "0" + String.valueOf(checkDigit) : String.valueOf(checkDigit);
		String IBAN = countryCode + cD + bankID + "000000" + this.accID;
		return IBAN;
	}
	
	public int genCheckDigit(String c) {
		String rest2 = c.substring(4) + c.substring(0, 4);
		long finalCheck = 0;
		for(int i = 0; i < rest2.length(); i++) {
			finalCheck = (Character.getNumericValue(rest2.charAt(i)) > 9 ? finalCheck * 100 : finalCheck * 10) + Character.getNumericValue(rest2.charAt(i));
            if (finalCheck > 999999999) {
            	finalCheck = finalCheck % 97;
            }
		}
		return (98 - (int)(finalCheck % 97));
	}
	
	
}