import java.util.*;

public class Team{
	int level = 0;
	int count = 1;
	int max = -1;
	Lake lake;
	Person[] members = new Person[1];
	int[] skills = new int[5];
	Random random = new Random();

	public Team(String name, Lake lk){
		members[0] = new Person(name);
		levelUp();
		lake = lk;
	}
	public Team(Person character, Lake lk){
		members[0] = character;
		levelUp();
		lake = lk;
	}
	
	public void install(Lake lk, Install in){
		
	}

	public void clean(Person p){
		int addend = random.nextInt(11)+5;
		lake.clean(addend);
	}
	public void clean(){
		double addend = random.nextInt(11)+5;
		lake.clean(addend);
	}
	
	public int levelUp(){
		level++;
		max += 2;
		Person[] newTeam = new Person[max];
		for (int x = 0 ; x < count ; x++){
			newTeam[x] = members[x];
		}
		members = newTeam;
		return level;
	}

	public int count(){
		return count;
	}
}
