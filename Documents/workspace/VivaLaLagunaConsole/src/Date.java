
public class Date{
	private int hour = 1;
	private int monthNumber = 1;
	private int year = 1;
	private String time = "1:00 AM";
	private String monthWord = "January";
	
	public void nextHour(){
		hour++;
		if (hour > 12){
			hour = 1;
			monthNumber++;
			if (monthNumber > 12){
				monthNumber = 1;
				year++;
			}
		}
		if (hour == 1){
			monthWord = "January";
			if (monthNumber == 2){
				monthWord = "February";
			}
			else if (monthNumber == 3){
				monthWord = "March";
			}
			else if (monthNumber == 4){
				monthWord = "April";
			}
			else if (monthNumber == 5){
				monthWord = "May";
			}
			else if (monthNumber == 6){
				monthWord = "June";
			}
			else if (monthNumber == 7){
				monthWord = "July";
			}
			else if (monthNumber == 8){
				monthWord = "August";
			}
			else if (monthNumber == 9){
				monthWord = "September";
			}
			else if (monthNumber == 10){
				monthWord = "October";
			}
			else if (monthNumber == 11){
				monthWord = "November";
			}
			else if (monthNumber == 12){
				monthWord = "December";
			}
		}
		int timeNumber = hour;
		if (hour > 6){
			timeNumber -= 6;
			time = (timeNumber*2-1) + " PM";
		}
		else{
			time = (timeNumber*2-1) + " AM";
		}
	}
	public String printDate(){
		String date = "";
		if (hour == 1){
			if (monthNumber == 1){
				date += "--++$Year "+year+"$++--\n";
			}
			date += "---++"+monthWord+"++---\n";
		}
		date += "-----"+time+"-----\n";
		return date;
	}

	public int hour(){
		return hour;
	}
	public int year(){
		return year;
	}
	public int monthNumber(){
		return monthNumber;
	}
	public String monthWord(){
		return monthWord;
	}
	public String time(){
		return time;
	}
}
