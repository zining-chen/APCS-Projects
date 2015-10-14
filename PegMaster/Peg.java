/**
 * This class defines a Peg in the game PegMaster
 * 
 * @author Aditya Kalari
 * @version 15 October 2015
 */
 
public class Peg{
	 
	//values 'A' through 'F' and 'X'
	private char letter;
	
	public Peg(){
		letter = 'X';
	}
	
	public Peg (char l){
		letter = l;
	}
	
	public char getLetter (){
		return letter;
	}
	
	public void setLetter (char l){
		letter = l;
	}
	 
}
