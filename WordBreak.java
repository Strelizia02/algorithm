import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordBreak {
    /**
     * 给定一个字符串 s 和一个字符串字典wordDict，在字符串s中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。
     *
     * 注意：词典中的同一个单词可能在分段中被重复使用多次。
     *
     * 示例 1：
     *
     * 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
     * 输出:["cats and dog","cat sand dog"]
     * 示例 2：
     *
     * 输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
     * 输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
     * 解释: 注意你可以重复使用字典中的单词。
     * 示例3：
     *
     * 输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
     * 输出:[]
     *
     * "aaaaaaa"
     * ["aaaa","aaa"]
     *
     * ["aaaa aaa","aaa aaaa"]
     */

    public static void main(String[] args) {
        String s = "aaaaaaa";
        List<String> wordDict = Stream.of("aaaa","aaa").collect(Collectors.toList());
        List<String> list = new WordBreak().wordBreak(s, wordDict);
        System.out.println(list);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> results = new ArrayList<>();
        List<String> buffer = new ArrayList<>();
        wordBreakByPoint(s, wordDict, 0, results, buffer);

        return results;
    }

    public void wordBreakByPoint(String s, List<String> wordDict, int start, List<String> result, List<String> words) {
        List<String> buffer = new ArrayList<>(words);
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (wordDict.contains(word)) {
                words.add(s.substring(start, end));
                if (end == s.length()) {

                    StringBuilder sb = new StringBuilder();
                    for (String w: words) {
                        sb.append(w).append(" ");
                    }
                    result.add(sb.substring(0, sb.length() - 1));
                    words.clear();
                    return;
                } else {
                    wordBreakByPoint(s, wordDict, end, result, words);
                }
                words = new ArrayList<>(buffer);
            }
        }
    }
}
