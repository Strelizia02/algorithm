import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 日志管理系统
 * 输入：
 * ["LogSystem","add","add","add","query","delete","delete","query"]
 * [[],[1,5],[2,5],[3,6],[5,6],[2],[4],[5,6]]
 * 输出：
 * [null,null,null,null,3,0,-1,2]
 * 解释：
 * 第一个操作是初始化，没有返回值；
 * 前三个 Add 操作加入了 ID 为 1、2、3 的日志，timeStamp 分别为 5、5、6；
 * 第一次 Query 操作查询 timeStamp 范围为[5,6]的日志数量，返回 3；
 * 第一次 Delete 操作删除了 ID 为 2 的日志，删除成功，返回 0；
 * 第二次 Delete 操作试图删除 ID 为 4 的日志，没有该日志，操作失败，返回 -1；
 * 最后一次 Query 操作查询 timeStamp 范围为[5,6]的日志数量，由于该范围中已经有一条日志被删除了，故返回 2。
 * 注：输出中的 null 表示此对应函数无输出（等同于 C 语言的 void 类型）。
 */
class LogSystem {

    Map<Integer, Integer> logList;

    public LogSystem() {
        logList = new HashMap<>();
    }

    public void add(int id, int timeStamp) {
        logList.put(id, timeStamp);
    }

    public int delete(int id) {
        if (logList.containsKey(id)) {
            logList.remove(id);
            return 0;
        }else {
            return -1;
        }
    }

    public int query(int startTime, int endTime) {
        AtomicInteger count = new AtomicInteger();
        logList.forEach((k, v) -> {
            if (startTime <= v && v <= endTime){
                count.getAndIncrement();
            }
        });
        return count.intValue();
    }
}
