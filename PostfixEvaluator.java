import java.util.*;


public class PostfixEvaluator {

	private static final String OPERATORS = "+-*/";
	private LIFOStack<Integer> operandStack;
	
	private int evalOp(int lhs, char op, int rhs) {
		int result = 0;
		switch (op) {
		case '+': result = lhs + rhs; break;
		case '-': result = lhs - rhs; break;
		case '*': result = lhs * rhs; break;
		case '/': result = lhs / rhs; break;
		}
		return result;
	}
	
	private boolean isOperator(char ch) {
		return OPERATORS.indexOf(ch) != -1;
	}
	
	public int eval(String expression) throws SyntaxErrorException {
		// Assume that the expression is valid
		
		operandStack = new ListStack<Integer>();
		Scanner lineScanner = new Scanner(expression);
		try {
			while (lineScanner.hasNext()) {
				String nextToken = lineScanner.next();
				if (Character.isDigit(nextToken.charAt(0))) {
					int value = Integer.parseInt(nextToken);
					operandStack.push(value);
				}
				else if (isOperator(nextToken.charAt(0))) {
					int rhs = operandStack.pop();
					int lhs = operandStack.pop();
					char op = nextToken.charAt(0);
					int result = evalOp(lhs, op, rhs);
					operandStack.push(result);
				}
				else {
					throw new SyntaxErrorException("Invalid character encountered.");
				}		
			}
			
			int answer = operandStack.pop();
			if (operandStack.isEmpty()) {
				return answer;
			}  else {
				throw new SyntaxErrorException("Syntax Error: Stack should be empty after final answer.");
			}
		} 
		catch (NumberFormatException ex) {
			throw new SyntaxErrorException("Syntax Error: Invalid number in expression.");
		}
		catch (NoSuchElementException ex) {
			throw new SyntaxErrorException("Syntax Error: Pop attempt - stack is empty.");
		}
	}
	
	public static void main(String[] args) {
		
		PostfixEvaluator evaluator = new PostfixEvaluator();

		System.out.println("Input valid postfix expressions for evaluation. Hit ENTER only to quit.");
		System.out.println("Separate each token with one or more spaces");
		System.out.println("Input expression:");
		Scanner scan = new Scanner(System.in);
		String expression = scan.nextLine();
		
		while (expression.length() > 0) {
			try {
				int value = evaluator.eval(expression);
				System.out.println("Value = " + value);
			} catch (SyntaxErrorException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Input expression:");
			expression = scan.nextLine();
		}
		System.out.println("DONE");
				
	}
	
}
