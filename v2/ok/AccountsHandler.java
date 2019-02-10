package ok;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AccountsHandler {
	
	private HashMap<String, Account> accs = new HashMap<>();
	private HashMap<Integer, Account> accsByType = new HashMap<>();
 	private boolean offline = false;
	private boolean err = false;
	private boolean initialized = false;
	
	public void addAccount(Account acc) throws IOException {
		this.accs.put(acc.getIBAN(), acc);
		this.accsByType.put(acc.getType(), acc);
		if(this.isInitialized() == true) {
			this.updateRemote(acc);
		}
	}
	
	public int withdraw(double sum, int accType) {
		try {
			if(sum <= 0 || (sum % 10) != 0) {
				return -1;
			}
			if(this.getAccountByType(accType).getBalance() < sum) {
				return 0;
			}
			this.getAccountByType(accType).setBalance(this.getAccountByType(accType).getBalance() - sum);
			this.updateRemote(this.getAccountByType(accType));
			System.out.println("Withdrew " + sum + ". New balance: " + this.getAccountByType(accType).getBalance());
			return 1;
		}catch(java.lang.NumberFormatException | IOException e) {
			System.out.println("Something went wrong...");
		}
		return -1;
	}
	
	public int deposit(double sum, int accType) {
		try {
			if(sum <= 0 || (sum % 10) != 0) {
				return -1;
			}
			this.getAccountByType(accType).setBalance(this.getAccountByType(accType).getBalance() + sum);
			this.updateRemote(this.getAccountByType(accType));
			System.out.println("Deposited " + sum + ". New balance: " + this.getAccountByType(accType).getBalance());
			return 1;
		}catch(java.lang.NumberFormatException | IOException e) {
			System.out.println("Something went wrong...");
		}
		return -1;
	}
	
	public void updateRemote(Account a) throws IOException {
		try {
			JSONObject ns = new JSONObject();
			ns.put("IBAN", a.getIBAN());
			ns.put("type", a.getType());
			ns.put("accID", a.getAccID());
			ns.put("currency", a.getCurrency());
			ns.put("balance", a.getBalance());
			ns.put("userid", ClientHandler.getClient().getAccID());
			String strrr = "http://alexbam.me/api/update_bank.php";
			URL url = new URL(strrr);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
	        OutputStream os = conn.getOutputStream();
	        os.write(("account=" + ns.toString()).getBytes());
	        os.flush();
	        
	        if (conn.getResponseCode() != 200) {
	        	System.out.println("Could not process request: " + conn.getResponseCode());
	        }else {
		        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	
		        String output;
		        while ((output = br.readLine()) != null) {
		        	try{ 
			        	JSONObject out = new JSONObject(output);
			        	switch(out.getString("result")) {
			        	case "OK": System.out.println("Info for IBAN " + a.getIBAN() + " successfully updated."); break;
			        	case "NEW_OK": System.out.println("Account " + a.getIBAN() + " successfully added to database."); break;
			        	case "NOT_OK": System.out.println("Account " + a.getIBAN() + " could not be added. Error: " + out.getString("error_msg")); break;
			        	default: System.out.println("Something went wrong... " + output);
		        	}
		        	}catch(JSONException e) {
		        		System.out.println(output);
		        	}
		            //System.out.println(output);
		        }
	        }
	        conn.disconnect();

	      } catch (IOException e) {
	    	  e.printStackTrace();
	      } 
	}
	
	public HashMap<String, Account> getAccounts(){
		return this.accs;
	}
		
	public void init() throws JSONException, IOException {
		 String s = "http://alexbam.me/api/accounts.php?userid=" + ClientHandler.getClient().getAccID();
		 URL url = new URL(s);
		 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		 conn.setRequestProperty("User-Agent", "Mozilla/5.0");
		 String str = new String();
		 String inputLine;
		 JSONObject obj = null;
		 // read from the URL
		 try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
			 while ((inputLine = in.readLine()) != null) {
	            str += inputLine;
			 }
			 in.close();
			 obj = new JSONObject(str);
		 }catch(java.io.IOException e) {
			 this.offline = true;
			 this.err = true;
			 System.out.println("An error has occured while reading students from remote server. Now in [OFFLINE] mode.");
			 try(BufferedReader cacheReader = new BufferedReader(new FileReader("accs.txt"))) {
				 String line = cacheReader.readLine();
				 if(line != null) {
					 obj = new JSONObject(line); 
				 }
			 }catch(java.io.FileNotFoundException ee) {
				 ee.printStackTrace();
			 }
			// e.printStackTrace();
		 }

		 if(!obj.isEmpty() && obj.getString("result").equals("OK")){			
			 JSONArray arr = obj.getJSONArray("accounts");
			 for(int i = 0; i < arr.length(); i++) {
				 Long accID = 0L;
				 try { accID = Long.valueOf(arr.getJSONObject(i).getString("accID")); }catch(java.lang.NumberFormatException ee) { accID = 0L; }
				 Account acc = new Account(arr.getJSONObject(i).getString("IBAN"), arr.getJSONObject(i).getInt("type"), accID, arr.getJSONObject(i).getString("currency"));
				 
				 this.addAccount(acc);
				 this.getAccountByIBAN(acc.getIBAN()).setBalance(arr.getJSONObject(i).getDouble("balance"));
			 }
			 try (PrintWriter out = new PrintWriter("accs.txt", "UTF-8")) {
				 out.println(obj);
			 }catch(java.io.FileNotFoundException e) {
				 e.printStackTrace();
			 }
			 System.out.println("Done initializing. " + arr.length() + " accounts were added" + ((this.offline == true) ? " from cache" : "")  + ".");
			 this.initialized = true;
		 }else {
			 this.err = true;
			 System.out.println("An error has occured: " + obj.getString("error_msg"));
		 }
	}
	
	public Account getAccountByIBAN(String IBAN) {
		return this.accs.get(IBAN);
	}
	
	public Account getAccountByType(int type) {
		return this.accsByType.get(type);
	}
	
	public boolean isInitialized() {
		return (initialized == true) ? true : false;
	}
	
}
