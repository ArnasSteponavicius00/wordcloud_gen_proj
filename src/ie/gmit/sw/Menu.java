package ie.gmit.sw;

import java.util.*;

/* Author: Arnas Steponavicius | Student ID: G00361891
 * Reads in user input and determines what method user wants to call
 * 
 */

public class Menu {
	
	private Scanner scan = new Scanner(System.in);
	private boolean askOption = true;
	private String fileOrUrl;
	private String fileName;
	private String url;
	private int maxWords;

	/* start() - method that keeps looping over and printing the menu
	 * until user inputs "4" in the handle method to change
	 * askOption to false and stop the loop
	 */
	public void show() throws Exception {

		//loop will keep going until user terminates it
		while(askOption) {
			//call method to print the menu options
			printMenu();

			//take in user input
			String option = scan.next();

			//send user input to method handle()
			handle(option);
		}
	}//start()

	//printMenu() - method that prints out the menu options
	public void printMenu() {
		
		System.out.println("========================================================");
		System.out.println("||              WORD CLOUD GENERATOR                  ||");
		System.out.println("========================================================");
		System.out.println("||             Please pick your option                ||");
		System.out.println("========================================================");
		System.out.println("|| Option 1: Enter name of File OR Enter URL link     ||");
		System.out.println("|| Option 2: Exit program                             ||");
		System.out.println("========================================================");
		System.out.print("|| Enter: ");

	}//printMenu()

	/* handle() - method that compares user input with hard-coded values and if they match
	 * calls the method for that option or terminates the program by changing
	 * askOption to false;
	 */
	public void handle(String sc) throws Exception {

		if(sc.equals("2")) {
			askOption = false;
		}
		else if(sc.equals("1")) {
			
			//do while
			do {
				//user chooses between file or url
				System.out.print("\nPlease enter either: (f) File OR (u) URL: ");
				fileOrUrl = scan.next().toLowerCase();	
				
				try {
					//user enters name of file to parse and the amount of words to display
					if(fileOrUrl.equals("f")) {
						System.out.print("\nPlease enter the name of the file you wish to parse: ");	
						fileName = scan.next();
						
						System.out.print("\nPlease enter the amount of words you wish to place: ");	
						maxWords = scan.nextInt();

						new Parser().parseFile(fileName, maxWords);
						break;

					//user enter url of website they want to parse and the amount of words to display
					}else if(fileOrUrl.equals("u")) {
						System.out.println("\nPlease enter URL of the website you wish to parse\n");	
						url = scan.next();
						
						System.out.print("\nPlease enter the amount of words you wish to place: ");	
						maxWords = scan.nextInt();
						
						new Parser().parseUrl(url, maxWords);
						break;
					}
				}catch(Exception e){
					System.out.println("\n========================================================");
					System.out.println("\nInput file name and its extension e.g 'book.txt'\n"
							+ "OR\n"
							+ "Enter full URL e.g 'https://www.gmit.ie'\n");
				}
			}while(!fileOrUrl.equals("f") || !fileOrUrl.equals("u"));
		}
		else {
			System.out.println("\nInvalid input\n");
		}
	}//handle()
	
}//Menu

