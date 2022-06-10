public class ZResetWord {
    /**
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
     *
     * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     *
     * 请你实现这个将字符串进行指定行数变换的函数：
     *
     * 示例 1：
     *
     * 输入：s = "PAYPALISHIRING", numRows = 3
     * 输出："PAHNAPLSIIGYIR"
     * 示例 2：
     * 输入：s = "PAYPALISHIRING", numRows = 4
     * 输出："PINALSIGYAHRPI"
     * 解释：
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     * 示例 3：
     *
     * 输入：s = "A", numRows = 1
     * 输出："A"
     */

    public static void main(String[] args) {
        System.out.println(new ZResetWord().convert("PAYPALISHIRING", 3));
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int j = i;
            while (j < s.length()) {
                sb.append(s.charAt(j));
                j = nextCycle(j, numRows);
            }
        }

        return sb.toString();
    }

    public int nextCycle(int row, int numRows) {
        int cycle = 2 * numRows - 2;
        int x = row % cycle;
        if (x == 0 || x == numRows - 1) {
            return row + cycle;
        } else if (x < numRows - 1) {
            return row + cycle - 2 * x;
        } else {
            return row + 2 * cycle - 2 * x;
        }
    }
}
