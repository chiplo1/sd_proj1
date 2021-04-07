package ex;
import java.util.*; 

public class aula1_1 {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println("Enter a string");
		String s = sc.nextLine();
		
		Stack<Character> stack = new Stack<Character>();
		LinkedList<Character> fifo = new LinkedList<Character>();
		
		for(int i=0;i<s.length();i++) {
			Character current = s.charAt(i);
			stack.add(current);
			fifo.add(current);
		}
		
		if(stack.size()!=fifo.size()) {
			System.out.println("Stack and Fifo sizes not matching.");
			System.exit(0);
		}
		
		for(int i=0;i<stack.size();i++) {
			if(stack.pop()!=fifo.pop()) {
				System.out.println("The given string is NOT a palindrome.");
				System.exit(0);
			}
		}
		
		System.out.println("The given string is a palindrome.");
		
	}

}
