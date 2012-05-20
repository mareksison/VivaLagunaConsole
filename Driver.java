import java.util.*;

/**
 * Driver class that is public
 * Main class of Viva La Laguna Java Console
 * Uses the java.util package
 * 
 * @author mareksison
 */
public class Driver{
	
	/**
	 * main method that is public, static, and void
	 * 
	 * @param args   an Array of String arguments
	 */
	public static void main(String[] args){
		//Creating the much needed Scanner object to read in input
		Scanner scan = new Scanner(System.in);
		//Welcomes the player
		System.out.println("Welcome to Viva La Laguna for the Mac Console!");
		System.out.println("-----");
		//Prompts the player to make a new game
		System.out.println("Would you like to create a new game? (\"yes\" or \"no\")");
		String dec = "";
		//Loop to get only "yes" or "no"
		while (true){
			//Input of decision
			dec = scan.next();
			//Break loop if either "yes" or "no" is entered
			if (dec.equalsIgnoreCase("Yes") || dec.equalsIgnoreCase("No")){
				break;
			}
			//If neither is entered, loop will continue until one is
			else{
				System.out.println("Please enter \"yes\" or \"no\"");
			}
		}
		System.out.println("-----");
		//If "yes" is entered, a new game is created
		if (dec.equalsIgnoreCase("Yes")){
			//A new lake is created and the date is starts at 1/1 1:00 AM
			Lake lake = new Lake();
			Date date = new Date();
			String charName = "Character";
			Team team = new Team(charName, lake);
			System.out.println("New Lake created!");
			//Loop the main game to continue until game ends
			while (true){
				//Prepare for the next hour by printing date
				date.printDate();
				//Marking current milliseconds to have the in-game hour last only one minute
				long startingMillis = System.currentTimeMillis();
				//Initialize an indicator for whether the player did any action, marked true
				boolean acted = true;
				//Initialize a counter for the number of actions made
				int actions = 0;
				//Loop to give player time to input actions
				int numberActions = team.count();
				if (numberActions == 1){
					System.out.println("You are the only member of your team,");
					System.out.println("And therefore, you only have one action");
				}
				else{
					System.out.println("There are " + numberActions + " on your team,");
					System.out.println("And therefore, the same number of actions");
				}
				do{
					System.out.println("+--");
					//Print out differing prompts to act depending on number of actions taken
					if (numberActions <= 0){
						System.out.println("You are now out of actions");
					}
					if (actions <= 0){
						//The boolean action indicator at true indicates the very start of the hour
						if (acted){
							System.out.println("Enter \"C\" to clean the lake\nEnter \"H\" for help\nWhat will you do?");
							//Since the start of the hour has passed, give "acted" its true value, "false"
							acted = false;
						}
						//If it's not the start of the hour but no action is made, prompt to hurry player
						else{
							System.out.println("Do something! Time is running (Enter\"H\" for help)");
						}
					}
					else if (actions > 3){
						System.out.println("Wow! You're on a roll! (Enter\"H\" for help)");
					}
					else{
						System.out.println("What else will you do? (Enter\"H\" for help)");
					}
					//Take in input for player's actions
					String action = scan.next();
					if (action.equalsIgnoreCase("I")){
						System.out.printf("Sorry, but installation is yet to be implemented");
						acted = true;
						actions++;
					}
					else if (action.equalsIgnoreCase("C")){
						double oldDirt = lake.dirt();
						team.clean();
						System.out.printf("You clean the lake and eradicate %.0f", (lake.clean().amount()/oldDirt)*100);
						System.out.println("%");
						acted = true;
						actions++;
					}
					else if (action.equalsIgnoreCase("H")){
						System.out.println("Enter \"C\" to clean the lake\nEnter \"I\" to install machines to the lake (unimplemented)\nEnter \"H\" for help");
					}
				}
				while(System.currentTimeMillis()-startingMillis < 60000);
				System.out.println("+--");
				//Loop is finished, so print statement that hour is finished
				System.out.println("One hour has already passed");
				//Simulate Lake and check if game ends while passing through the "acted" argument
				if (!lake.update(acted)){
					//If game ends, game loop breaks
					break;
				}
				//If game does not end, get next hour and loop again
				date.nextHour();
			}
		}
		//If "no" is entered, the console is exited
		else{
			System.out.println("Goodbye!");
		}
	};
	
}