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
	private boolean disqualified;
	
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
		this.disqualified = false;
	}
	
	/**
	 * givePenalty method - gives the object an additional penalty only if the player doesn't
	 * exceed the limit
	 */
	public void givePenalty() {
		if(!disqualified()) {
			numPenalties++;
			if(disqualified()) {
				System.out.println(firstName + " " + lastName + "Is now disqualified...");
			}
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
	
	public String getFullName() {
		return this.firstName + " " + this.lastName;
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
		return disqualified;
	}
	
	public void setDisqualified(boolean is) {
		disqualified = is;
	}
	
	/**
	 * hashCode method - returns a hash that was created utilizing the basketball player's last name 
	 * and using the multiplicative hashing method utilizing Horner's method
	 */
	@Override
	public int hashCode() {
		int x = 53;
	    int h = 0;

	    for (int i = 0; i < lastName.length(); i++) {
	        h = h * x + lastName.charAt(i);
	    }
	    return h;
	}
	
	/**
	 * hashCode2 method - this returns a hash that was created utilizing the mid-square hashing method.
	 */
	public int hashCode2() {
		int sum = 0;
		for(int i = 0; i < lastName.length() - 1; i++) {
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
	
	@Override 
	public boolean equals(Object o) {
		if (this == o) return true; // If the object itself is the same exact object, return true
	    if (o == null) return false; // If the object is null, return false
	    if (o.getClass() != this.getClass()) return false; // If the class is different from the object, then return false.

	    BasketBallPlayer other = (BasketBallPlayer) o; // cast the object as a basketball player

	    return firstName.toLowerCase().equals(other.firstName.toLowerCase())
	        && lastName.toLowerCase().equals(other.lastName.toLowerCase()); // return if both the first and last names match
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
