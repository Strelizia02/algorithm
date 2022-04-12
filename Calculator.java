import java.math.BigDecimal;
import java.util.Stack;

public class Calculator {
    /**
     * 实现一个加减乘除计算器
     * 条件：只有 加减，和括号 四个运算符，包含小数
     * 无需考虑输入不合规情况，表达式中无空格
     * 如：
     * 1.5*(1.7+1.3-1)/3+10/10
     * 输出：2
     *
     * @param args
     */
    public static void main(String[] args) {
        String formula = "1.5 * ( 1.7 + 1.3 - 1 ) / 3 + 10 / ( 3 * ( 2 + 1 ) + 1 )";
        System.out.println(formula + " = " + calculator(formula));
    }

    public static String calculator(String formulaWithSpace) {
        String formula = formulaWithSpace.replace(" ", "");
        Stack<String> number = new Stack<>();

        // 直接切分字符串然后入栈
        String[] numberArr = formula.split("(?<=[/()*+-])|(?=[/()*+-])");

        for (String op : numberArr) {
            number.push(op);
            if (")".equals(op)) {
                // 去掉括号
                Stack<String> noBrackets = new Stack<>();
                number.pop();
                while (!"(".equals(number.peek())) {
                    noBrackets.push(number.pop());
                }
                number.pop();
                number.push(calcNoBracket(noBrackets));
            }
        }

        Stack<String> noBrackets = new Stack<>();
        while (!number.empty()) {
            // 倒手一次再计算
            noBrackets.push(number.pop());
        }

        return calcNoBracket(noBrackets);
    }

    public static String calcNoBracket(Stack<String> noBrackets) { // 无括号算式
        Stack<String> number = new Stack<>();
        while (!noBrackets.empty()) {
            number.push(noBrackets.pop());
            if ("*".equals(number.peek()) || "/".equals(number.peek())) { // 先算乘除法
                String secondNum = noBrackets.pop();
                String ops = number.pop();
                String firstNum = number.pop();

                switch (ops) {
                    case "*":
                        number.push(new BigDecimal(firstNum).multiply(new BigDecimal(secondNum)).toString());
                        break;
                    case "/":
                        number.push(new BigDecimal(firstNum).divide(new BigDecimal(secondNum), 2).toString());
                        break;
                    default:
                        break;
                }
            }
        }

        while (number.size() != 3) {
            String secondNum = number.pop();
            String ops = number.pop();
            String firstNum = number.pop();

            switch (ops) {
                case "-":
                    number.push(new BigDecimal(firstNum).subtract(new BigDecimal(secondNum)).toString());
                    break;
                case "+":
                    number.push(new BigDecimal(firstNum).add(new BigDecimal(secondNum)).toString());
                    break;
                default:
                    break;
            }
        }

        String secondNum = number.pop();
        String ops = number.pop();
        String firstNum = number.pop();

        switch (ops) {
            case "-":
                return new BigDecimal(firstNum).subtract(new BigDecimal(secondNum)).toString();
            case "+":
                return new BigDecimal(firstNum).add(new BigDecimal(secondNum)).toString();
            default:
                return "false";
        }
    }
}
