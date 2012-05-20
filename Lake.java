import java.util.*;

/**
 * Lake class that is public
 * Represents the lake as an entity
 * 
 * Lake starts with 2 inherent attributes, health and morale
 * Health is a double indicator that is one-tailed, ranging from 0.0 to 100.0
 * Morale is a also a double indicator, but one that is two-tailed, ranging from -100.00 to 100.00
 * At the start, health is at 20.0 and morale is at 0.0
 * If health reaches 5.0, the lake dies and the game is over
 * 
 * Other attributes include a "cycle" attribute which indicates number of hours the player hasn't acted
 * Also, there is a Random object for randomizing numbers
 * 
 * @author mareksison
 */
public class Lake {
	private double health = 20;
	private double morale = 0;
	private double dirt = 100;
	
	private int cycle = 0;
	private int noAct = 0;
	
	private Clean clean = new Clean();
	private Clean dirty = new Clean();
	
	private Random random = new Random();
	
	/**
	 * @return the health of the lake
	 */
	public double health(){
		return health;
	}
	public double morale(){
		return morale;
	}
	public double dirt(){
		return dirt;
	}
	public Clean clean(){
		return clean;
	}
	public Clean dirty(){
		return dirty;
	}
	
	public boolean update(boolean acted){
		double rate = 0;
		if (acted){
			cycle = 0;
			if (noAct > 0){
				noAct--;
			}
		}
		else{
			noAct++;
		}
		printBars();

		if (clean.moved()){
			System.out.println("The community is inspired by your cleaning of the lake");
			rate = clean.amount()*.1;
			if (morale+rate > 100){
				rate = 100-morale;
			}
			if (rate != 0){
				System.out.printf("Your morale increased by %.2f\n", rate);
				morale += rate;
				System.out.printf("Your morale is now %.2f", morale);
				System.out.println();
			}
			clean.moved(false);
			clean.amount(0);
		}
		if (dirty.moved()){
			System.out.println("The community is disgusted by the increasing dirt in the lake");
			rate = dirty.amount()*.1;
			if (morale-rate < -100){
				rate = morale;
			}
			if (rate != 0){
				System.out.printf("Your morale decreased by %.2f\n", rate);
				morale -= rate;
				System.out.printf("Your morale is now %.2f", morale);
				System.out.println();
			}
			dirty.moved(false);
			dirty.amount(0);
		}
		cycle++;
		if (cycle == 3){
			System.out.println("You didn't act for the past hours, and the community isn't pleased");
			rate = noAct*.2;
			if (morale-rate < -100){
				rate = morale;
			}
				if (morale != 0){
				System.out.printf("Your morale decreased by %.2f\n", rate);
				morale -= rate;
				System.out.printf("Your morale is now %.2f", morale);
				System.out.println();
			}
		}
		
		rate = ((morale-1)*.1)*(random.nextDouble()+1);
		if (rate+health < 0){
			rate = health*-1;
		}
		else if (rate+health > 100){
			if (health > 100){
				rate = 0;
			}
			else{
				rate = 100-health;
			}
		}
		if (health > 100){
			System.out.printf("Your health would've increased, but it can't anymore!");
		}
		else if (rate < 0){
			System.out.printf("Your health will decrease by %.2f\n", rate*-1);
		}
		else if (rate > 0){
			System.out.printf("Your health will increase by %.2f\n", rate);
		}
		else{
			System.out.printf("Your health will stay steady at %.2f\n", health);
		}
		if (health + rate >= 100){
			health = 100;
		}
		else{
			health += rate;
		}
		if (health <= 5){
			System.out.print("Your health already reached 5.00% at ");
			System.out.printf("%.2f", health);
			System.out.println("%");
			System.out.println("The Lake is now irreversabily dead\nYOU LOSE");
			return false;
		}
		else if (rate < 0 || rate > 0){
			System.out.printf("Your health is now %.2f", health);
			System.out.println("%");
			return true;
		}
		return true;
	}
	public double clean(double dirtCleaned){
		if (dirt-dirtCleaned < 0){
			dirtCleaned = dirt;
		}
		dirt -= dirtCleaned;
		clean.moved(true);
		clean.amount((100-dirt));
		return dirt;
	}
	public double dirty(double dirtPlaced){
		if (dirt-dirtPlaced < 0){
			dirtPlaced = 100-dirt;
		}
		dirt -= dirtPlaced;
		dirty.moved(true);
		dirty.amount(dirt/100);
		return dirt;
	}
	public String printBars(){
		String hs = "|----------\n|Your health is " + health + "%\n|----------";
		String ms = "|----------\n|Your morale is " + morale + "\n|----------";
		System.out.printf("|----------\n|Your health is %.2f", health);
		System.out.println("%\n|----------");
		System.out.printf("|----------\n|Your morale is %.2f", morale);
		System.out.println("\n|----------");
		return hs + "\n" + ms;
	}
}
