import java.util.Arrays;

/**
 * leetcode 406. 根据身高重建队列
 * 假设有搅乱顺序的一群儿童成一个队列，每个儿童由一个整数对[w, k]表示
 * w -> 儿童体重
 * k -> 站在这个儿童前面，并且体重大于等于他的儿童数量
 * 要求，按要求把乱序队列还原
 *
 * 输入:
 * [[8,0], [4,4], [8,1], [5,0], [6,1], [5,2]]
 * 输出:
 * [[5,0], [8,0], [5,2], [6,1], [4,4], [8,1]]
 */
public class RebuildByHeight {

    public static void main(String[] args) {
//        int[][] people = {{8, 0}, {4, 4}, {8, 1}, {5, 0}, {6, 1}, {5, 2}};
        // [[5, 0], [8, 0], [5, 2], [6, 1], [4, 4], [8, 1]]
        int[][] people = {{4, 4}, {5, 0}, {7, 0}, {7, 1}, {6, 1}, {5, 2}};
        //[[5, 0], [7, 0], [5, 2], [6, 1], [4, 4], [7, 1]]
//        int[][] people = {{6, 0}, {5, 0}, {4, 0}, {3, 2}, {2, 2}, {1, 4}};
        // [[4, 0], [5, 0], [2, 2], [3, 2], [1, 4], [6, 0]]
//        int[][] people = {{5, 1}, {5, 0}};
        // [[5, 0], [5, 1]]
        System.out.println(Arrays.deepToString(reconstructQueue(people)));
    }

    public static int[][] reconstructQueue(int[][] people) {
        if (people.length == 1){
            return people;
        }

        for (int i = 0; i < people.length; i++) {
            for (int j = i; j < people.length; j++) {
                if (isChildHere(people, i, j)) {
                    break;
                }
            }
        }
        return people;
    }

    public static boolean isChildHere(int[][] people, int q, int i) {
        // 判断i放在q这个位置能不能行
        int number = 0;
        if (q == 0){
            if(people[i][1] == 0) {
                exchangeArray(people, q, i);
                return isChildHere(people, q + 1, q + 1);
            } else {
                return false;
            }
        }

        for (int j = 0; j < q; j++){
            if (people[j][0] >= people[i][0]){
                number++;
            }
        }

        if (i == people.length - 1 || q == people.length - 1){
            // 如果i是最后一个了
            if(number == people[i][1]) {
                exchangeArray(people, q, i);
                return true;
            } else {
                return false;
            }
        }

        if (number == people[i][1]) {
            exchangeArray(people, q, i);
            return isChildHere(people, q + 1, q + 1);
        } else {
            return false;
        }
    }

    public static void exchangeArray(int[][] people, int p, int q) {
        // 交换两个元素位置
        int[] temp = people[p];
        people[p] = people[q];
        people[q] = temp;
    }
}
