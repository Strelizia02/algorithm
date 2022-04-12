import java.util.ArrayList;
import java.util.List;

/**
 * 将单词尽可能的分为更多的连续子串，并且每两个子串之间没有重复的字母，求出每个子串的长度集合。
 * 同Leetcode原题https://leetcode-cn.com/problems/partition-labels/
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 */
public class SplitMoreString {

    public static void main(String[] args) {
        String str = "ababcbacadefegdehijhklij";
        System.out.println(getAllGroup(str));
    }

    public static int getLastChars(String str, char c){
        // 获取字符串某个字符所在的最后一个位置
        for (int i = str.length() - 1; i >= 0; i--){
            if (str.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    public static int getGroup(String str, int firstChars, int lastChars) {
        // 通过循环判断字符串的最后一个位置，判断当前分组是不是最大分组，不是就递归再判断
        for (int i = firstChars; i <= lastChars; i++) {
            if (getLastChars(str, str.charAt(i)) > lastChars){
                return getGroup(str, firstChars, getLastChars(str, str.charAt(i)));
            }
        }
        return lastChars;
    }

    public static List<Integer> getAllGroup(String str) {
        List<Integer> lastList = new ArrayList<>();
        int lastChars = -1;
        while (lastChars < str.length() - 1){
            int firstChars = lastChars;
            lastChars = getGroup(str, lastChars + 1, getLastChars(str, str.charAt(lastChars + 1)));
            lastList.add(lastChars - firstChars);
        }
        return lastList;
    }
}
