

import java.util.Stack;


public class DelimiterChecker {

    public static boolean checkDelimiters(String input, boolean showTrace) {
        Stack<Character> stack = new Stack<>();

        if (showTrace) {
            System.out.println("Input: " + input);
        }

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
                if (showTrace) {
                    System.out.println("Read: " + ch);
                    System.out.println("Operation: PUSH");
                    System.out.println("Stack: " + stack);
                }
            } 

            else if (ch == ')' || ch == ']' || ch == '}') {
                if (showTrace) {
                    System.out.println("Read: " + ch);
                }

                if (stack.isEmpty()) {
                    if (showTrace) {
                        System.out.println("Operation: INVALID");
                        System.out.println("Reason: closing delimiter has no matching opening delimiter");
                    }
                    return false;
                }

                char top = stack.peek();
                if (showTrace) {
                    System.out.println("Operation: PEEK -> " + top);
                }

                if (isMatch(top, ch)) {
                    stack.pop();
                    if (showTrace) {
                        System.out.println("Operation: POP");
                        System.out.println("Stack: " + stack);
                    }
                } else {
                    if (showTrace) {
                        System.out.println("Operation: INVALID");
                        System.out.println("Reason: mismatched delimiters");
                    }
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) {
            if (showTrace) {
                System.out.println("Operation: INVALID");
                System.out.println("Reason: unmatched opening delimiter remains");
            }
            return false;
        }

        if (showTrace) {
            System.out.println("Result: VALID\n");
        }
        return true;
    }

    private static boolean isMatch(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
               (opening == '[' && closing == ']') ||
               (opening == '{' && closing == '}');
    }

    public static void main(String[] args) {
        String[] testCases = {
            "()",
            "()[]{}",
            "{[()]}",
            "({[]})",
            "{[(])}",
            "([)]",
            "{[()]",
            "{[()]}]",
            "",
            "((a+b)*[c-d])"
        };

        System.out.println("--- RUNNING TEST CASES ---");
        for (String tc : testCases) {
            boolean result = checkDelimiters(tc, false);
            System.out.println("Input: \"" + tc + "\" | Output: " + (result ? "Valid" : "Invalid"));
        }

        System.out.println("\n--- DEMONSTRATING EXECUTION TRACE FOR {[()]} ---");
        checkDelimiters("{[()]}", true);
    }
}