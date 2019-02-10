package ok;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/ok/FXML/Main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("ＡＥＳＴＨＥＴＩＣ　ＢＡＮＫ v0.1.5");
        System.out.print("****************************************************\n                                          .* *...,.,,..,.                                          \r\n" + 
        		"                                   .....,,        .  ..,,..                                        \r\n" + 
        		"                                ...,. ......... .  ..  ...*..                                      \r\n" + 
        		"                               ... .  . ......     *.   . .,..*,                                   \r\n" + 
        		"                           ...  ,     ....       ,,.  ,  .  /....,                                 \r\n" + 
        		"                          . ... . .     ...             ,. *,,   ,.                                \r\n" + 
        		"                          .. .. . ..    ..,..*(/,,*// .,,*.,,.*,.,*/                               \r\n" + 
        		"                        .... /,     .. ,. ,.,*.,//,.,/***,***.     /*                              \r\n" + 
        		"                        ,.,.. ...     *,.   ,**.......,,,,,,.,....**,                              \r\n" + 
        		"                          ,,,..***,.,*,.             .  ....,  ****/,                              \r\n" + 
        		"                        ,,*...   .**,,.,            ..  . ..,**,**                                 \r\n" + 
        		"                        .,***,*. ..........,.    .. . ....**,**/                                   \r\n" + 
        		"                        ,,***///,*,....,...,,,,,,,....*//,,**,*.                                   \r\n" + 
        		"                           ,,/**/,...   .,  ,,.. ..  *,....,..*                                    \r\n" + 
        		"                            ,,,,*....    ..  .   ... ,, ..,,..,                                    \r\n" + 
        		"                            .,/,.*,....           .. ,,......,                                     \r\n" + 
        		"                              /*.,.,,....       .... .,.....,,                                     \r\n" + 
        		"                              ,,*/,,,......    .. .   ,....,*.                                     \r\n" + 
        		"                              *,,*,,,,........  ,,/,,,*,..,**                                      \r\n" + 
        		"                               ,,,,,,,,......       .,*,..,*.                                      \r\n" + 
        		"                                 ..*,,,........*,,/,*,*..,**                                       \r\n" + 
        		"                                   ,,*,,,,.......,,*,,,.,*,                                        \r\n" + 
        		"                                    .,***,,....      ..,,**.                                       \r\n" + 
        		"                                    ,..,,,***,,......,******                                       \r\n" + 
        		"                                    *..,...,,*****,,,***,,,*                                       \r\n" + 
        		"                                     ,.,,.......,,,****,,.,,*,                                     \r\n" + 
        		"                                     ,,.,,........,,,,.....,,..,,,,..,                             \r\n" + 
        		"                                      ,..,,..,,............,...........,,,,                        \r\n" + 
        		"                                     ,*...,...............................,*                       \r\n" + 
        		"                                  ,,,,,,..,,,...........................,*.                        \r\n" + 
        		"                              .,,,..,,,*...,,,,.......................**                           \r\n" + 
        		"                             ,,,,.,,,*.,....,,.....................,*.                             \r\n" + 
        		"                             *,,,,,,,..............................*                               \r\n" + 
        		"                             *,,,,,,....,........................,*                                \r\n" + 
        		"                              ,,,.............................*.,*                                 \r\n" + 
        		"                               ,,............................. ./                                  \r\n" + 
        		"                                .,...........................///*                                  \r\n" + 
        		"                                  .................... .,((//////                                  \r\n" + 
        		"                                   ,,*(,.........../(#((/////#/.                                   \r\n" + 
        		"                                   ,,,,,,,,,.,...,,*(((//*                                         \r\n" + 
        		"                                             ,,..,        "
        		+ "\n"
        		+ "\n"
        		+ "\n"
        		+ " _  _      _  _____  _  ____  _     _  ____  _  _      _____\r\n" + 
        		"/ \\/ \\  /|/ \\/__ __\\/ \\/  _ \\/ \\   / \\/_   \\/ \\/ \\  /|/  __/\r\n" + 
        		"| || |\\ ||| |  / \\  | || / \\|| |   | | /   /| || |\\ ||| |  _\r\n" + 
        		"| || | \\||| |  | |  | || |-||| |_/\\| |/   /_| || | \\||| |_//\r\n" + 
        		"\\_/\\_/  \\|\\_/  \\_/  \\_/\\_/ \\|\\____/\\_/\\____/\\_/\\_/  \\|\\____\\"
        		+ "\n****************************************************");
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("/res/css/Main.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
       /* primaryStage.setOnCloseRequest(e -> {
            // Do smthg
        });*/
    }

    public static void main(String[] args) {
        launch(args);
    }

}


/*
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.json.JSONException;

import iHateLife.Student;

import java.util.Scanner;

public class Main {
	
	
	private static boolean running = false;
	private static String command;
	private static Scanner sc = new Scanner(System.in);
	private static AccountsHandler ah = new AccountsHandler();
	private static ClientHandler ch = new ClientHandler();
	
	public static void main(String[] args) throws JSONException, IOException, NoSuchAlgorithmException {
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
		    @Override
		    public void run()
		    {
		    	System.out.println("Uploading data to cloud...");
		    	for(Map.Entry<String, Account> a : ah.getAccounts().entrySet()) {
					try {
						ah.updateRemote(a.getValue());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		    }
		});
		
		
		System.out.println("Welcome to BamBank! Please enter your CLIENT ID: ");
		Client cl = new Client(sc.nextLine());
		ch.setClient(cl);
		System.out.println("Welcome back, " + cl.getFirstName() + ". Please enter your 4 number pin: ");
		ch.auth(sc.nextInt());

		ah.setClient(cl);
		ah.init();
		
		System.out.println("Welcome, " + cl.getFirstName() + " " + cl.getLastName() + " " + cl.getMiddleName());
		running = true;
		
		while(running){
			System.out.println("Enter a command: [Balance] to check balance, [Accounts] to check your accounts, [Transfer] to transfer to another account, [Withdraw] to withdraw");
			command = sc.nextLine();
			switch(command) {
			case "Exit" : running = false;
			break;
			
			case "Balance" : 
				System.out.println("Balance for current account: " + cl.getCurrentmoney() + Accounts.currencyName);
			break;	
			
			case "Accounts" : 
				 for(Map.Entry<String, Account> a : ah.getAccounts().entrySet()) {
					 System.out.println("Account " + a.getValue().getIBAN() + ": " + a.getValue().getAccType() + " account having a total of " + a.getValue().getBalance() + a.getValue().getCurrency());
				 }
			break;	

			
			case "Transfer" : 
			break;
			
			case "Withdraw" :
			break;
			
			default: System.out.println("Invalid command.");
			break;
			
			
			
			}
			
		}
	}
	
}*/