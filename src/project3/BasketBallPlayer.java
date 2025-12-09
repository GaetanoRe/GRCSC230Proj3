package project3;

public class BasketBallPlayer {
	
	private String firstName;
	private String lastName;
	private int numPenalties;
	
	public BasketBallPlayer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.numPenalties = 1;
	}
	
	public void givePenalty() {
		if(numPenalties < 5) {
			numPenalties++;
		}
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public int getNumPenalties() {
		return this.numPenalties;
	}
	
	public boolean disqualified() {
		return numPenalties == 5;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	public int hashCode2() {
		return 0;
	}
	
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
