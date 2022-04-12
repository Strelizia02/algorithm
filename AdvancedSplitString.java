import java.util.Arrays;

public class AdvancedSplitString {
    public static void main(String[] args) {
        //普通切割字符串，按照某字符串切分，不包含该串。
        String[] split1 = "aaabbbcccaaabbbccc".split("bbb");
        //正向肯定匹配切分，按照匹配到的字符串首位下标进行切分。
        String[] split2 = "aaabbbcccaaabbbccc".split("(?=bbb)");
        //逆向肯定匹配切分，按照匹配到的字符串的下一个字符串的首位下标进行切分。
        String[] split3 = "aaabbbcccaaabbbccc".split("(?<=bbb)");
        //正向和逆向混编，结果就是会按照匹配字符串及下一个字符串的首位下标进行切分，效果就是按照bbb切分，但是保留了bbb。
        String[] split4 = "aaabbbcccaaabbbccc".split("(?=bbb)|(?<=bbb)");

        System.out.println("split1 =" + Arrays.toString(split1));
        System.out.println("split2 =" + Arrays.toString(split2));
        System.out.println("split3 =" + Arrays.toString(split3));
        System.out.println("split4 =" + Arrays.toString(split4));

        /**
         * 特殊正则正向逆向混编，因为正向肯定匹配，匹配到了{字符串的首位下标
         * 所以在逆向正则中，只需要对字符串的}反大括号进行匹配，匹配}的下一个字符串的首位下标进行切分
         * 即可得到，按照大括号括起来的内容切分，又保留了大括号内容本身
         */
        String formula = "xxxx{abc/{asdf}.xzcv}xxxx";
        String[] numberArr = formula.split("(?<=(}))|(?=(\\{))");
        System.out.println("numberArr =" + Arrays.toString(numberArr));
    }
}
