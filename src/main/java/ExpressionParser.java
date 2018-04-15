import java.util.*;

public class ExpressionParser {
    private static String operators = "+-*/^";
    private static String delimiters = "() " + operators;
    public static boolean flag = true;
    private static boolean isDelimiter(String token) {
        if (token.length() != 1) return false;
        for (int i = 0; i < delimiters.length(); i++) {
            if (token.charAt(0) == delimiters.charAt(i)) return true;
        }
        return false;
    }

    private static boolean isOperator(String token) {
        for (int i = 0; i < operators.length(); i++) {
            if (token.charAt(0) == operators.charAt(i)) return true;
        }
        return false;
    }

    private static int priority(String token) {
        if (token.equals("(")) return 1;
        if (token.equals("+") || token.equals("-")) return 2;
        if (token.equals("*") || token.equals("/")) return 3;
        return 4;
    }

    public static List<String> parse(String infix) {
        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
        String prev = "";
        String curr;
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                System.out.println("Incorrect expression!");
                flag = false;
                return postfix;
            }
            if (curr.equals(" ")) continue;
            else if (isDelimiter(curr)) {
                switch (curr) {
                    case "(":
                        stack.push(curr);
                        break;
                    case ")":
                        while (!stack.peek().equals("(")) {
                            postfix.add(stack.pop());
                            if (stack.isEmpty()) {
                                System.out.println("Brackets not matched!");
                                flag = false;
                                return postfix;
                            }
                        }
                        stack.pop();
                        break;
                    default:
                        if (!curr.equals("-") || (!prev.equals("") && (!isDelimiter(prev) || prev.equals(")")))) {
                            while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
                                postfix.add(stack.pop());
                            }

                        }
                        stack.push(curr);
                        break;
                }
            }
            else {
                postfix.add(curr);
            }
            prev = curr;
        }

        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) postfix.add(stack.pop());
            else {
                System.out.println("Brackets not matched!");
                flag = false;
                return postfix;
            }
        }
        return postfix;
    }
}
