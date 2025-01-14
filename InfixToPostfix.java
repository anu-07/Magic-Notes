/* Java implementation to convert
infix expression to postfix*/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Test {


	static int Prec(char ch)
	{
		switch (ch) {
		case '+':
		case '-':
			return 1;

		case '*':
		case '/':
			return 2;

		case '^':
			return 3;
		}
		return -1;
	}


	static String infixToPostfix(String exp)
	{
		// initializing empty String for result
		String result = new String("");

		// initializing empty stack
		Deque<Character> stack
			= new ArrayDeque<Character>();

		for (int i = 0; i < exp.length(); ++i) {
			char c = exp.charAt(i);

			// If the scanned character is an
			// operand, add it to output.
			if (Character.isLetterOrDigit(c))
				result += c;

			// If the scanned character is an '(',
			// push it to the stack.
			else if (c == '(')
				stack.push(c);


			else if (c == ')') {
				while (!stack.isEmpty()
					&& stack.peek() != '(') {
					result += stack.peek();
					stack.pop();
				}

				stack.pop();
			}
			else // an operator is encountered
			{
				while (!stack.isEmpty()
					&& Prec(c) <= Prec(stack.peek())) {

					result += stack.peek();
					stack.pop();
				}
				stack.push(c);
			}
		}

		// pop all the operators from the stack
		while (!stack.isEmpty()) {
			if (stack.peek() == '(')
				return "Invalid Expression";
			result += stack.peek();
			stack.pop();
		}
	
		return result;
	}

	// Driver's code
	public static void main(String[] args)
	{
		String exp = "a+b*(c^d-e)^(f+g*h)-i";
	
		// Function call
		System.out.println(infixToPostfix(exp));
	}
}
