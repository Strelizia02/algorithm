import java.util.Stack;

/**
 * 计算括号嵌套最大层级
 * 普通算式1+1，视为0层
 * ()视为1层
 * 1 + ( 1 + (1) + ( 1 + 1))视为2层
 */
public class MaxDepth {
    public static void main(String[] args) {

        String inputStr = "(1+(2*3)+((8)/4))+1";
        System.out.println(getMaxDepth(inputStr));
    }

    public static int getMaxDepth(String inputStr) {
        if (!inputStr.contains("(")) {
            return 0;
        }
        String[] numberArr = inputStr.split("(?<=[/()*+-])|(?=[/()*+-])");
        StringBuilder formula = new StringBuilder();
        for (String num: numberArr) {
            if (num.equals("(") || num.equals(")")) {
                formula.append(num);
            }
        }
        String[] kuoArr = formula.toString().split("(?<=[/()*+-])|(?=[/()*+-])");
        Stack<String> stack = new Stack<>();
        for (String s: kuoArr) {
            stack.push(s);
            if (s.equals(")")) {
                stack.pop();
                int num = 0;
                while (!stack.peek().equals("(")) {
                    num = Math.max(num, Integer.parseInt(stack.pop()));
                }
                stack.pop();
                stack.push("" + (num + 1));
            }
        }

        int num = 0;
        while (!stack.isEmpty()) {
            num = Math.max(num, Integer.parseInt(stack.pop()));
        }
        return num;
    }
}
