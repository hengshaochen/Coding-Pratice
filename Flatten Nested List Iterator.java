/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> s = new Stack<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        // 從後往前push到stack中
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            s.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return s.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!s.isEmpty()) {
            NestedInteger cur = s.peek();
            // 如果stack最頂是整數 --> 代表已經準備好next的Integer給next呼叫了
            if (cur.isInteger()) {
                return true;
            }
            // 如果stack最頂是LIST, 要把他flatten, 讓他變成整數給next呼叫
            s.pop();
            for (int i = cur.getList().size() - 1; i >= 0; i--) {
                s.push(cur.getList().get(i));
            }
        }
        return false;
    }
}
        /*
        //System.out.println(nestedList.get(0).getList().get(0).getInteger());
        System.out.println(nestedList.get(0).getInteger());
        System.out.println(nestedList.get(1).getList().get(0).getInteger());
        System.out.println(nestedList.get(1).getList().get(1).getList().get(0).getInteger());
        //System.out.println(nestedList.get(2).getInteger());
        */
        /*
        System.out.println(nestedList.get(1).getList().size());
        System.out.println(nestedList.get(1).getList().get(1).getList().get(0).getInteger());
        System.out.println(nestedList.get(1).getList().get(1).getList().get(1).getInteger());
        */
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */