/**
 * Michael J. Cusack
 */


/*
 * A method of calculating postfix expressions using a stack for the operands
 */
public class PostfixCalculator {
	
	private Stack<Double> operands;
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Sets operands to a LinkedStack containing doubles
	 */
	public PostfixCalculator() {
		operands = new LinkedStack<Double>();
	}
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Returns the value of keyboard input from the user
	 */
	public String readExpression() throws java.io.IOException {
		java.io.InputStreamReader stream = new java.io.InputStreamReader(System.in);
		java.io.BufferedReader reader = new java.io.BufferedReader(stream);
		String expression = reader.readLine();
		return expression;
	}
	
	/*
	 * Perform the specified operation and return the result
	 * @require
	 * 		operator != null
	 * 		operator is either +, -, *, or /
	 * @ensure
	 * 		if operand.equals("+") then returns operand1 + operand2
	 * 		else if operand.equals("-") then returns operand1 - operand2
	 * 		else if operand.equals("*") then returns operand1 * operand2
	 * 		else if operand.equals("/") then returns operand1 / operand2
	 */
	private double performOperation(double operand1, double operand2, String operator) {
		double result = 0.0;
		
		if (operator.equals("+"))
			result = operand1 + operand2;
		else if (operator.equals("-")) {
			result = operand1 - operand2;
		}
		else if (operator.equals("*")) {
			result = operand1 * operand2;
		}
		else if (operator.equals("/")) {
			result = operand1 / operand2;
		}
		
		return result;
	}
	
	/*
	 * Parse the expression and compute the result
	 * @require
	 * 		expression != null
	 * @ensure
	 * 		Returns "" if there is no exception
	 * 		Returns an exception if one occurs
	 */
	private String parseExpression(String expression) {
		double result = 0.0;
		double operand1 = 0.0;
		double operand2 = 0.0;
		String noException = "";
		
		String[] tokens = expression.split(" ");
		// Parse each element of the string individually
		for (String token: tokens) {
			// Mix of number and operator
			if (token.length() > 1 && (token.endsWith("+") || token.endsWith("-")
					|| token.endsWith("*") || token.endsWith("/"))) {
				return "Invalid expression. You must have a space between " +
						"operators/operands.";
			}
			
			else if (token.equals("")) {
				// Ignore the whitespace
			}
			
			// Perform the specified operation
			else if (token.equals("+") || token.equals("-") || token.equals("*")
					|| token.equals("/")) {
				
				if (operands.isEmpty())
					return "Invalid expression. Missing LHS and RHS operands.";
				
				else {
					operand2 = operands.top();
					operands.pop();
				}
				
				if (operand2 == 0)
					return "Invalid expression. Divide by 0.";
				
				if (operands.isEmpty())
					return "Invalid expression. Missing RHS operand.";
				
				else {
					operand1 = operands.top();
					operands.pop();
					result = performOperation(operand1, operand2, token);
					operands.push(result);
				}
			} 
			// Element is a number, add it to the stack if in range
			else if (token.matches("\\d+")) {
				
				if (Double.parseDouble(token) >= -500000000.0 &&
						Double.parseDouble(token) <= 1000000000.0)
					operands.push(Double.parseDouble(token));
				else
					return "The number must be in the range -500,000,000 to " +
							"1,000,000,000";
			}
			else
				return "Invalid expression. Invalid operator.";
		}
		return noException;
	}
	
	/*
	 * Calculate the result of the postfix expression
	 * @require
	 * 		expression != null
	 * @ensure
	 * 		Returns the result of the expression if no exceptions
	 * 		Returns an exception if one occured
	 */
	public String calculateResult(String expression) {
		String result = parseExpression(expression);
		Double lastOperand = 0.0;
		
		// If no exception pop the top operand
		if (result.equals("")) {
			lastOperand = operands.top();
			operands.pop();
		}
		
		// If no operands are left set result to the last operand
		if (operands.isEmpty() && result.equals(""))
			result = lastOperand.toString();
		
		// If operands remain, but not previous exceptions an operation needs to
		// be performed on the remaining operands
		if (!operands.isEmpty() && result.equals(""))
			result = "Invalid expression. There are two operands left, but no " +
					"operator.";
		
		// The result is out of range, throw an exception
		if (lastOperand < -500000000 || lastOperand > 1000000000)
			result = "The answer is out of the range -500,000,000 to 1,000,000,000";
		
		return result;
	}
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Reads the user's expression and calculates the result
	 * 		Returns the result or an error if one occurred
	 */
	public static void main(String[] args) {
		PostfixCalculator calc = new PostfixCalculator();
		String expression = null;
		
		System.out.println("Enter a postfix expression to calculate: ");
		try {
			expression = calc.readExpression();
		} catch (java.io.IOException e) {			
			System.out.println("Invalid input");
		}
		
		if (expression != null) {
			String result = calc.calculateResult(expression);
			System.out.println(expression + " = " + result);
		}
	}
}
