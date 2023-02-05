import java.util.*;

public class InfixToPostfixGenerator {

	private static final String OPERATORS = "+-*/()";
	private static final int[] PRECEDENCE = {1, 1, 2, 2, -1, -1};
	private LIFOStack<Character> operatorStack;
	private StringBuilder postfix;
	
	private int precedence(char op) {
		return PRECEDENCE[OPERATORS.indexOf(op)];
	}
	
	private boolean isOperator(char ch) {
		return OPERATORS.indexOf(ch) != -1;
	}
	
	private void processOperator(char nextOp) {
		
		// If there are no operators on the stack to compare to or the next operator
		// is a left parenthesis (everything that follows is of higher precedence),
		// push the next operator on the stack and we're done.
		if (operatorStack.isEmpty() || nextOp == '(') {
			operatorStack.push(nextOp);
		}
		else {
			// Remove all operators from the stack that have a higher or equal
			// precedence, and then push the next operator on the stack as long
			// as it is not a right parenthesis.
			char topOp = operatorStack.peek();
			if (precedence(nextOp) > precedence(topOp)) {
				operatorStack.push(nextOp);
			}
			else {
				while (!operatorStack.isEmpty() && precedence(nextOp) <= precedence(topOp)) {
					topOp = operatorStack.pop();
					if (topOp == '(') {
						break;				// '(' is the lowest precedence but it doesn't go in the postfix string, so exit loop immediately
					}
					postfix.append(topOp);
					postfix.append(' ');
					if (!operatorStack.isEmpty()) {
						topOp = operatorStack.peek();
					}
				}

				if (nextOp != ')') {
					operatorStack.push(nextOp);
				}
			}
		}
	}
	
	public String convert(String infix) throws SyntaxErrorException {
		operatorStack = new ListStack<Character>();
		postfix = new StringBuilder();
		Scanner linescanner = new Scanner(infix);
		try {
			while (linescanner.hasNext()) {
				String nextToken = linescanner.next();
				char firstChar = nextToken.charAt(0);
				if (isOperator(firstChar)) {
					// If the next token is an operator, process it with the stack
					processOperator(firstChar);
				}
				else if (Character.isDigit(firstChar)) {
					// If the next token is a number, append it to the postfix string immediately
					postfix.append(nextToken);
					postfix.append(' ');
				}
				else {
					throw new SyntaxErrorException("Unexpected character encountered: " + firstChar);
				}
			}
			
			// Remove all remaining operators from the stack and append to the postfix string to complete the answer
			while (!operatorStack.isEmpty()) {
				char op = operatorStack.pop();
				if (op == '(') {
					throw new SyntaxErrorException("Unmatched opening parenthesis");
				}
				postfix.append(op);
				postfix.append(' ');
			}
			return postfix.toString();
		}
		catch(NoSuchElementException ex) {
			throw new SyntaxErrorException("Syntax error: Pop attempted, stack is empty.");
		}
	}
	
	public static void main(String[] args) {
		
		InfixToPostfixGenerator generator = new InfixToPostfixGenerator();

		System.out.println("Input valid infix expressions for conversion. Hit ENTER only to quit.");
		System.out.println("Separate each token with one or more spaces");
		System.out.println("Input infix expression:");
		Scanner scan = new Scanner(System.in);
		String expression = scan.nextLine();
		
		while (expression.length() > 0) {
			try {
				String newExpression = generator.convert(expression);
				System.out.println("Postfix: " + newExpression);
			} catch (SyntaxErrorException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Input infix expression:");
			expression = scan.nextLine();
		}
		System.out.println("DONE");
				
	}
}
