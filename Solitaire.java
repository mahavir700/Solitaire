package solitaire;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author RU NB CS112
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
		
		CardNode cn = this.deckRear;
		//CardNode front = deckRear.next; //the last card in the deck
		
		if (deckRear.cardValue == 27) {
			int tempo = deckRear.cardValue;
			deckRear.cardValue = deckRear.next.cardValue;
			deckRear.next.cardValue = tempo;
			return;
		}
		
		while (cn.next.cardValue != deckRear.cardValue) {
			
			cn = cn.next;
			//front = front.next;
			
			if (cn.cardValue == 27) {
				int temp = cn.cardValue;
				cn.cardValue = cn.next.cardValue;
				cn.next.cardValue = temp;	
				break;
			}
			
			// if the card value is the last one
		}
		

		
		
		
		// COMPLETE THIS METHOD
	}

	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
		
		CardNode cn = this.deckRear;
		
		if (deckRear.next.cardValue == 28) {
			int onetemp = deckRear.next.cardValue;
			int twotemp = deckRear.next.next.cardValue;
			int threetemp = deckRear.next.next.next.cardValue;
			
			deckRear.next.cardValue = twotemp;
			deckRear.next.next.cardValue = threetemp;
			deckRear.next.next.next.cardValue = onetemp;
			return;
		}
		while (cn.next.cardValue != deckRear.cardValue) {
			cn = cn.next;
		if (cn.next.cardValue == 28) {
			int onetemp = cn.next.cardValue;
			int twotemp = cn.next.next.cardValue;
			int threetemp = cn.next.next.next.cardValue;
			
			cn.next.cardValue = twotemp;
			cn.next.next.cardValue = threetemp;
			cn.next.next.next.cardValue = onetemp;
			break;
			//return;
			
		}
		else if (deckRear.cardValue == 28) {
			int onetemp = deckRear.cardValue;
			int twotemp = deckRear.next.cardValue;
			int threetemp = deckRear.next.next.cardValue;
			
			deckRear.cardValue = twotemp;
			deckRear.next.cardValue = threetemp;
			deckRear.next.next.cardValue = onetemp;
			//return;
		}
		
		else if (cn.next.cardValue == 28 && cn.next.next == deckRear) {
			int onetemp = cn.next.cardValue;
			int twotemp = deckRear.cardValue;
			int threetemp = deckRear.next.cardValue;
			
			cn.next.cardValue = twotemp;
			deckRear.cardValue = threetemp;
			deckRear.next.cardValue = onetemp;
			//return;
		}
		}
		

	    // COMPLETE THIS METHOD
	}

	void tripleCut() {
		
		CardNode ptr1 = this.deckRear;
		CardNode ptr2 = deckRear;
		CardNode jokerA = deckRear; 
		CardNode jokerB = deckRear;
		CardNode after = deckRear;
		CardNode prev = deckRear;
		
		if ((deckRear.next.cardValue == 27 && deckRear.cardValue == 28) || (deckRear.next.cardValue == 28 && deckRear.cardValue == 27)) {
			/*CardNode cn = this.deckRear;
			while (cn.next.cardValue != 27 || cn.next.cardValue != 28) {
				cn = cn.next;*/
				return;
			//}
		
		}

		

		while (ptr1.cardValue != 27 || ptr1.cardValue != 28) {
			ptr1 = ptr1.next;

			if (ptr1.next.cardValue == 27 && ptr1.next.next == deckRear && deckRear.cardValue == 28) {
				deckRear = ptr1;
				return;
			}
			
			if (ptr1.next.cardValue == 28 && ptr1.next.next == deckRear && deckRear.cardValue == 27) {
				deckRear = ptr1;
				return;
			}
			
			 if (ptr1.next.cardValue == 27 && ptr1.next.next.cardValue == 28) {
				 ptr1 = ptr1;
				 ptr2 = ptr1.next.next.next;
				 
				 ptr1.next.next.next = deckRear.next;
				 deckRear.next = ptr1.next;
				 deckRear = ptr1;
				 ptr1.next = ptr2;
				 return;
			 }
			 
			 if (ptr1.next.cardValue == 28 && ptr1.next.next.cardValue == 27) {
				 ptr1 = ptr1;
				 ptr2 = ptr1.next.next.next;
				 
				 ptr1.next.next.next = deckRear.next;
				 deckRear.next = ptr1.next;
				 deckRear = ptr1;
				 ptr1.next = ptr2;
				 return;
			 }
			
			 if ((ptr1.next.cardValue == 27 || ptr1.next.cardValue == 28)) {  //ptr1.next.next.cardValue!=28) {
				jokerA = ptr1.next;
				ptr2 = jokerA.next;
				prev = ptr1;
				break;
			}
			 
			 /*if ((ptr1.next.cardValue == 27 && ptr1.next.next.cardValue == 28)) {
			//reset pointers agian	 
				 	jokerA = ptr1.next;
					ptr2 = jokerA.next;
					prev = ptr1;
			//swap values
				deckRear.next = ptr1;
				 ptr2.next = deckRear.next;
				 prev.next =ptr2.next;
				
					
				 	//deckRear = prev;
					

					
					
					return;
				}*/

			
		}
		
		while (ptr2.cardValue !=27||ptr2.cardValue !=28 ) {
			ptr2 = ptr2.next;
			if (ptr2.cardValue == 27 || ptr2.cardValue == 28) {
				jokerB = ptr2;
				after = jokerB.next;
				break;
			}

		}
		
		if (deckRear.cardValue == 27 || deckRear.cardValue == 28) {
			deckRear = ptr1;
			return;
		}
		
		if (deckRear.next.cardValue == 27 || deckRear.next.cardValue == 28) {
			deckRear = ptr1.next;
			return;
		}
		
		
		jokerB.next = deckRear.next;
		prev.next = after;
		deckRear.next = jokerA;
		deckRear = prev;
		// COMPLETE THIS METHOD

	}

	void countCut() {		
		
		CardNode ptr1 = deckRear;
		CardNode ptr2 = deckRear;
		int count = 0;
		
		while (count != deckRear.cardValue) {
			count++;
			ptr1 = ptr1.next;
		}
		
		while (ptr2.next != deckRear) {
			if (ptr2.next == deckRear) {
				break;
			}
			ptr2 = ptr2.next;
			
		}
		
		if (deckRear.cardValue == 28 || deckRear.cardValue == 27) {
			return;
		}

		ptr2.next = deckRear.next;
		deckRear.next = ptr1.next;
		ptr1.next = deckRear;
		
		// COMPLETE THIS METHOD
	}

	int getKey() {
		
		jokerA();
		jokerB();
		tripleCut();
		countCut();
		
		CardNode ptr = deckRear;
		int count = 0;
		int key = 0;
		
		if (deckRear.next.cardValue == 27 || deckRear.next.cardValue == 28) {
			while (count != 27) {
				count++;
				ptr = ptr.next;
				//break;
			}
			
			key = ptr.next.cardValue;
			//System.out.println("first key check: " + key);
			return key;
		}
		
		while (count != deckRear.next.cardValue) {
			count++;
			ptr = ptr.next; 
		}
		
		key = ptr.next.cardValue;
		//System.out.println("second key check: " + key);
		
		if (key == 27 || key == 28) {
			key = getKey();
		} 
		
		//System.out.println(key);
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE 
	    return key;
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	
		
	/*	String en = "";
		
		for (int i = 0; i < message.length(); i++) {
			Character a = message.charAt(i);
			int idk = 0;
			if (Character.isLetter(a)) {
				printList(deckRear);
				jokerA();
				printList(deckRear);
				jokerB();
				printList(deckRear);
				tripleCut();
				printList(deckRear);
				countCut();
				printList(deckRear);
				
				int key = getKey();
				System.out.println(key + " ");
				
				a = Character.toUpperCase(a);
				idk = (int)((char)a - 'A' + 1);
				idk = (int)(idk + key);
				
				while(idk > 26) {
					idk = (int)(idk - 26);
				}
				idk = (int) (idk + 'A' - 1);
				a = (char)idk;
				en += a;
			}
			
			else continue;
		} */
		
		String msg = message.toUpperCase();
		String str = new String();
		
		for (int i = 0; i < message.length(); i++) {
			char ch = msg.charAt(i);
			
			//System.out.println("Current character: " + ch);
			
		if (Character.isLetter(ch)) {
			int c = ch - 'A' + 1;
			
			//System.out.println("Character after converting to decimal form: " + c);
			
			int result = c + getKey();
			
			//System.out.println("Character after adding getKey() method: " + result);
			
			if (result > 26) {
				result = result - 26;
			}
			
			//System.out.println("Character after checking result against 26: " + result);
			
			ch = (char) (result-1+'A');
			
			//System.out.println("Final character: " + ch);
			
			str = str + ch;
			
			}
		} 
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    return str;
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
		
		String msg = message.toUpperCase();
		String str = "";
		
		for (int i = 0; i < message.length(); i++) {
			char ch = msg.charAt(i);
			
			if (Character.isLetter(ch)) {
				
				int c = ch - 'A' + 1;
				int result = c - getKey();
				
				if (result <= 0) {
					result = result + 26;
				}
				
				ch = (char) (result-1+'A');
				
				str = str + ch;
				
			}
		}
		
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    return str;
	}
}

