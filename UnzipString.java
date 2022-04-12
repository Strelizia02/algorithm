import java.util.Stack;

/**
 * 这题与 LeetCode 上的 394 【字符串解码】类似 https://leetcode-cn.com/problems/decode-string/
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: (encoded_string)<n>，表示其中括号内部的 encoded_string 正好重复 n 次。注意 n 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 示例1
 *
 * 输入：d(a)<3>(bc)<2>
 * 输出：daaabcbc
 * 示例2
 *
 * 输入：abc(a(cd)<2>)<3>
 * 输出：abcacdcdacdcdacdcd
 */
public class UnzipString {
    public static void main(String[] args) {
        System.out.println(unzipString("(d(a)<3>(bc)<2>a)<2>"));
    }

    public static String unzipString(String zipStr) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < zipStr.length(); i++) {
            // 循环把字符串入栈
            stack.push(zipStr.charAt(i));
            if (zipStr.charAt(i) == '>') { // 当字符串以>结尾时，把最近的()<>提取出来
                Stack<Character> stack1 = new Stack<>();
                while (stack.peek() != '(') {
                    stack1.push(stack.pop());
                }
                stack1.push(stack.pop());
                String subString = getSubString(stack1);
                for (int j = 0; j < subString.length(); j++) {
                    stack.push(subString.charAt(j));
                }
            }
        }

        return stackToString(stack);
    }

    public static String getSubString(Stack<Character> stack) {
        // 根据()<>最小Stack生成字符串
        StringBuilder stringBuilder = new StringBuilder();
        Character[] chars = new Character[stack.size()];
        int n = 0;
        for (int i = 0; i < chars.length; i++) {
            chars[i] = stack.pop();
            if (chars[i] == ')') {
                n = i;
            }
        }

        int cycle;
        StringBuilder sb = new StringBuilder();
        for (int i = n + 2; i < chars.length - 1; i++) {
            sb.append(chars[i]);
        }
        cycle = Integer.parseInt(sb.toString());

        for (int i = 0; i < cycle; i++) {
            for (int j = 1; j < n; j++) {
                stringBuilder.append(chars[j]);
            }
        }

        return stringBuilder.toString();
    }

    public static String stackToString(Stack<Character> stack) {
        // 栈转字符串
        Stack<Character> stack1 = new Stack<>();
        while (!stack.empty()) {
            stack1.push(stack.pop());
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack1.empty()) {
            stringBuilder.append(stack1.pop());
        }
        return stringBuilder.toString();
    }
}
