import java.util.ArrayList;
import java.util.List;

/**
 * 字符串input都是小写字母
 * 给定一个整数interval，使得重新组合后的字符串，相同字母至少间隔interval
 * 如果无法重新组合，返回“”
 * 答案不唯一，返回任一正确答案即可
 * 例：
 * input = "xxyyzz", interval = 3
 * "xyzxyz"
 */
public class IntervalString {

    public static void main(String[] args) {
        System.out.println(combineChars("xxyyzz", 3));
    }

    public static String combineChars(String input, int interval) {
        char[] chars = input.toCharArray();
        List<Character> charList = new ArrayList<>();
        for (char c: chars) {
            charList.add(c);
        }
        List<Character> str = new ArrayList<>();
        List<Character> rubbish = new ArrayList<>();
        if(isXOK(str ,charList, rubbish, interval)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (char c: str) {
                stringBuilder.append(c);
            }
            return stringBuilder.toString();
        }
        return "";
    }

    public static boolean isXOK(List<Character> str, List<Character> charList, List<Character> rubbish, int interval) {
        if (charList.size() == 0 && rubbish.size() == 0) {
            return true;
        } else if (charList.size() == 0){
            return false;
        }
        int num = -5;
        for (int i = 0; i < str.size(); i++) {
            if (str.get(i) == charList.get(0)) {
                num = i;
            }
        }
        if (str.size() - num >= interval) {
            str.add(charList.get(0));
            charList.remove(0);
            charList.addAll(rubbish);
            rubbish.clear();
            if (isXOK(str, charList, rubbish, interval)) {
                return true;
            } else {
                charList.add(str.get(0));
                str.remove(str.size() - 1);
                return false;
            }
        } else {
            rubbish.add(charList.get(0));
            charList.remove(0);
            return isXOK(str, charList, rubbish, interval);
        }
    }
}
