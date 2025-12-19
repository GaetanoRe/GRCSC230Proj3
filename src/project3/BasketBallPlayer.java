package project3;
/**
 * <p>Title: BasketBallPlayer class</p>
 * <p>Description: Creates an object that represents a member of the basketball team. 
 * It also comes with two different hashing functions.</p>
 * @author Gaetano Re
 */
public class BasketBallPlayer {
	
	private String firstName; // The first name of the player
	private String lastName; // The last name of the player
	private int numPenalties; // The number of penalties this player has gotten.
	
	/**
	 * parameterized constructor - this will create an object that represents a player, it takes a first
	 * name and a last name. It is assumed whoever was added to the list has a penalty, so the object starts 
	 * with one penalty
	 * @param firstName
	 * @param lastName
	 */
	public BasketBallPlayer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.numPenalties = 1;
	}
	
	/**
	 * givePenalty method - gives the object an additional penalty only if the player doesn't
	 * exceed the limit
	 */
	public void givePenalty() {
		if(numPenalties < 5) {
			numPenalties++;
		}
	}
	
	/**
	 * getFirstName method - this returns the player's first name
	 * @return the first name of the player
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * getLastName method - returns the last name of the player.
	 * @return the last name of the player
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * getNumPenalties method - this returns the number of penalties the player has accumulated.
	 * @return
	 */
	public int getNumPenalties() {
		return this.numPenalties;
	}
	
	/**
	 * disqualified boolean method - this returns true if the player is supposed to be disqualified.
	 * @return
	 */
	public boolean disqualified() {
		return numPenalties == 5;
	}
	
	/**
	 * hashCode method - returns a hash that was created utilizing the basketball player's last name 
	 * and using the multiplicative hashing method
	 */
	@Override
	public int hashCode() {
		int result = 0;
		int x = 53;
		for(int i = lastName.length(); i > 0; i--) {
			result += lastName.charAt(i) * Math.pow(x, i);
		}
		return Math.abs(result);
	}
	
	/**
	 * hashCode2 method - this returns a hash that was created utilizing the mid-square hashing method.
	 * @return
	 */
	public int hashCode2() {
		int sum = 0;
		for(int i = 0; i < lastName.length(); i++) {
			sum += lastName.charAt(i);
		}
		long squaredResult = (long) Math.pow(sum, 2);
		String str = Long.toString(squaredResult);
		int len = str.length();
		int mid = len/2;
		if(len < 2) {
			return Integer.parseInt(str);
		}
		return Integer.parseInt(str.substring(mid-1, mid+1));
	}
	
	/**
	 * toString method - this returns the player object as a string
	 */
	public String toString() {
		StringBuilder strbld = new StringBuilder();
		strbld.append("Name: ");
		strbld.append(firstName);
		strbld.append(" ");
		strbld.append(lastName);
		strbld.append("  ");
		strbld.append("# of Penalties: ");
		strbld.append(numPenalties);
		
		return strbld.toString();
	}

}
