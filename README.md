# ATM

**Responsible: Balan Alexandru, Nagy Andrea, Ilie Cristi**

This class should handle everything realted to an actual ATM, such as: 

Planned functionality: 
1. Ability to have multiple accounts (implement an IBAN system, like in a real bank, each account being able to be of different types, like an economies account, a current account etc)
2. Ability to "withdraw" money from the ATM 
3. Ability to consult balance (preferably on a monthly/yearly basis)
4. Ability to send money from an account to another

# Client
**Responsible: Balan Alexandru, Nagy Andrea, Ilie Cristi**

This class should handle client-related operations, such as:

1. Read/save client info from file
2. Storing client information (name, address, age, sex, accounts IDs, etc)

# Main
**Responsible: Rusu Eduard**

1. Initialize everything in here and add a menu. 
2. Maybe add a GUI (?)


# Basic implementation, tips & tricks

1. Class Structure


USE PRIVATE VARIABLES

Ex: `private int number;`

To work with these variables within the class, just call this.variable
ex: `this.number;`

To work with these variables outside the class, you have to create getters and setters:

- Setters: methods which set a private variable 
ex: 
```
public void setNumber(int number){
      this.number = number;
}
```

- Getters: methods which get the value of the desired variable
ex:
```
public int getNumber(){
      return this.number;
}
```

REMARK: FOR A FUNCTION WHICH RETURNS A VALUE YOU HAVE TO USE return BEFORE WHAT YOU WANT THAT FUNCTION TO RETURN.

2. Method implementation


a. Try using clear and meaningful method names (not names like coolfunctionbro). 
For ex, if you write a part of the "withdraw" money functionality, you may need a function that sets the new balance.
A good name for that would be something like "setBalance" or "setNewBalance". 
I recommend starting with a lowercase letter and continuing with uppercase like in the above example.
 
b. Try keeping your code as short and clean as possible. DON'T OVERDO! SHORT CODE IS A BLISS.

Ex: instead of a huge if-else statement, try using the ternary operator ( (condition) ? true : false; ) 
Ex: 
```
if(string != null) {
     System.out.prinln("Not null");
}else{ 
     System.out.println("Null");
}
```
Can be written as:
`System.out.println(((string != null) ? "Not null" : "Null"));`

c. Test everything you implement before pushing your code! ALWAYS test for any possible errors.

3. Error Handling


Your code must have a way to check for errors.
For example:
```
Scanner sc = new Scanner(System.in);
int test = sc.nextInt();
System.out.println(test);
```

In the above code, if a string is input instead of an integer, java.util.InputMismatchException is thrown and the program stops execution.


![exception handling](http://alexbam.me/imgs/1.png)

This is bothersome.
To avoid this, try-catch statments must be used!
For example, to the above code:
```	
Scanner sc = new Scanner(System.in);
//Before scanning for the nextInt, add a try block like this
try{
	int test = sc.nextInt();
	System.out.println(test);
}catch(java.util.InputMismatchException e){
	System.out.println("You must input a number!");
}
```

![exception handling](http://alexbam.me/imgs/2.png)

This way the program doesn't just stop working the second someone thought 2 should be written as "two".
Error codes are found either by TESTING your code and seeing what exception is thrown, or by using your old friend Google.


