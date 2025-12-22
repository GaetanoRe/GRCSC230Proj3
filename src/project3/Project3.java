package project3;

import java.util.Scanner;

public class Project3 {
	
	public static void main(String args[]){
		
		// Below is code I used to test my linked list for this Hash Table
		/*LinkedList<Integer> intList = new LinkedList<Integer>();
		
		intList.insert(1);
		intList.insert(2);
		intList.insert(3);
		
		System.out.println("The result when I search for 2 is: " + intList.search(2));
		
		System.out.println("The result when I search for 4 is: " + intList.search(4));
		
		System.out.println(intList);
		
		intList.delete(2);
		
		System.out.println(intList);*/
		
		
		// Simple test done on the hash table to see if it was inserting and deleting properly
		/*HashTable<String> names = new HashTable<String>();
		
		names.insert("Jerry");
		names.insert("Gerald");
		names.insert("Patty");
		
		System.out.println(names);
		System.out.println("The current table size is: " + names.length());
		
		names.delete("Jerry");
		System.out.println(names);*/
		
		// Stress test on Hash table to see if it would resize
		/*HashTable<String> names2 = new HashTable<String>();
		
		names2.insert("Tom");
		names2.insert("MOT");
		names2.insert("Mary");
		names2.insert("George");
		names2.insert("Jackson");
		names2.insert("Tommy Lee Jones");
		
		System.out.println(names2);*/
		
		// Now for the program
		System.out.println("Welcome to the Foul Management System! Please input a name or 'l'/ 'list' to get started, or 'q' 'quit' to stop.");
		HashTable<BasketBallPlayer> badPlayers = new HashTable<BasketBallPlayer>();
		Scanner scnr = new Scanner(System.in);
		boolean done = false;
		int maxNumPenalties;
		if(args.length >= 1) {
			maxNumPenalties = Math.abs(Integer.parseInt(args[0]));
		}
		else {
			maxNumPenalties = 5;
		}
		
		
		while(!done) {
			try {
				String answer = scnr.nextLine().trim();
				if(answer.contains(" ")) {
					String [] splitAnswer = answer.split(" ");
					if(splitAnswer.length != 2) {
						throw new RuntimeException();
					}
					else {
						BasketBallPlayer newPlayer = new BasketBallPlayer(splitAnswer[0], splitAnswer[1]);
						if(badPlayers.get(newPlayer) != null) {
							BasketBallPlayer stored = badPlayers.get(newPlayer);
							if(stored.disqualified()) {
								System.out.println("Player is already disqualified");
							}
							else {
								stored.givePenalty();
								System.out.println(stored.getFullName() + " was given another foul. They have " + (maxNumPenalties - stored.getNumPenalties()) + " left.");
								if(stored.getNumPenalties() ==  maxNumPenalties) {
									stored.setDisqualified(true);
								}
							}	
						}
						else {
							badPlayers.insert(newPlayer);
							System.out.println(newPlayer.getFullName() + " was just given their first foul.");
						}
					}
				}
				else if(answer.toLowerCase().equals("q") || answer.toLowerCase().equals("quit")) {
					done = true;
				}
				else if(answer.toLowerCase().equals("l") || answer.toLowerCase().equals("list")) {
					System.out.println(badPlayers);
				}
				else {
					throw new RuntimeException();
				}
				
			}catch (RuntimeException ex) {
		        System.out.println("Invalid Format. Please type a name with a first and last name, 'l' for a list, or 'q' to quit");
		    }

		}
		scnr.close();
		
		
		
	}

}
