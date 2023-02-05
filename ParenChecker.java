import java.util.*;
public class ParenChecker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String leftparens = "{[(";
		String rightparens = "}])";
		
		System.out.println("Please input your parenthetical expression");
		Scanner scan = new Scanner(System.in);
		String expression = scan.nextLine();
		
		LIFOStack<Character> s = new ArrayStack<Character>();
		
		Scanner linescan = new Scanner(expression);
		boolean valid = true;
		
		while (valid && linescan.hasNext()) {
			char paren = linescan.next().charAt(0);
			if (leftparens.indexOf(paren) != -1)
				s.push(paren);
			else if (rightparens.indexOf(paren) != -1) {
				if (s.isEmpty())
					valid = false;
				else {
					if (leftparens.indexOf(s.pop()) != rightparens.indexOf(paren))
						valid = false;
				}
			}
		}
		
		if (!s.isEmpty())
			valid = false;
		
		System.out.println("Nested Parentheses Valid: " + valid);
		
	}

}
