/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        // 1.先取得每個人Free Time的區間 / MIN_VALUE代表-inf, MAX_VALE代表inf
        TreeMap
        for (int i = 0; i < avails.length; i++) {
            
        }
        
        // 2. 接著把每個人的每個區間和其他人做比較, 找到共同交集
        // 用my calendar的思路, 若回傳false代表兩者間有overlap, 縮小overlap / 若回傳true代表沒overlap, 放棄這個區間
        // 如果每個pair都確認過了, 還是有值, 就把他加入到答案中
    }
}