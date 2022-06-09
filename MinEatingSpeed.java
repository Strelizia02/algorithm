public class MinEatingSpeed {
    /**
     * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有piles[i]根香蕉。警卫已经离开了，将在 h 小时后回来。
     *
     * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
     *
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     *
     * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）
     *
     * 输入：piles = [3,6,7,11], h = 8
     * 输出：4
     *
     * 输入：piles = [30,11,23,4,20], h = 5
     * 输出：30
     *
     * 输入：piles = [30,11,23,4,20], h = 6
     * 输出：23
     *
     * [332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184]
     * 823855818
     * 输出 14
     *
     * [1000000000]
     * 2
     * 输出 500000000
     */

    public static void main(String[] args) {
        int i = new MinEatingSpeed().minEatingSpeed(new int[]{30,11,23,4,20}, 6);
        System.out.println(i);
    }

    public int minEatingSpeed(int[] piles, int h) {
        long sum = 0;
        int max = 0;
        for (int pile: piles) {
            sum += pile;
            if (pile > max) {
                max = pile;
            }
        }
        int k;
        if (sum % h == 0) {
            k = (int) (sum / h);
        } else {
            k = (int) (sum / h + 1);
        }
        while (k != max) {
            if (canEatPile(piles, h, k)) {
                max = (k + max) / 2;
            } else {
                k = (k + 1 + max) / 2;
            }
        }
        return max;
    }

    public boolean canEatPile(int[] piles, int h, int k) {
        int num = 0;
        for (int pile : piles) {
            if (pile <= k) {
                num += 1;
            } else if (pile%k == 0) {
                num += pile / k;
            } else {
                num += pile / k + 1;
            }
        }
        return num <= h;
    }
}
