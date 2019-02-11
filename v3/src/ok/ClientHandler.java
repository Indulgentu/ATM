package ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;

public class ClientHandler {
	
	private static Client cl;
	
	public static void setClient(Client client) {
		cl = client;
	}
	
	public static Client getClient() {
		return cl;
	}
	
	public static boolean auth(String email, int pin) throws IOException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(String.valueOf(pin).getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        String encodedPass = sb.toString();
		String strrr = "http://alexbam.me/api/auth_user.php";
		URL url = new URL(strrr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        OutputStream os = conn.getOutputStream();
        os.write(("user=" + email + "&pin=" + encodedPass).getBytes());
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
		        	case "OK": System.out.println("Auth successful."); 
		        	JSONObject arr = out.getJSONObject("details");
		        	//cl.setarr.getString("fullname"), arr.getString("group"), arr.getInt("age")));
		        	Client client = new Client(arr.getString("fullname"));
		        	client.setAccID(arr.getInt("id"));
		        	client.setCNP(arr.getString("CNP"));
		        	client.setAge(arr.getInt("age"));
		        	client.setGend(arr.getInt("gender"));
		        	client.setRegDate(arr.getString("registered"));
		        	client.setClientaddr(arr.getString("address"));
		        	setClient(client);
		        	return true; 
		        	case "NOT_OK": System.out.println("Auth failed. Reason: " + out.getString("error_msg")); return false;
		        	default: System.out.println("Something went wrong... " + output);
	        	}
	        	}catch(JSONException e) {
	        		System.out.println(output);
	        	}
	            System.out.println(output);
	        }
        }
        conn.disconnect();
        return false;
	}
	
}

