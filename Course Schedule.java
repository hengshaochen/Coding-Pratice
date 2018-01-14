class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 建圖及統計indegree
        int[] indegree = new int[numCourses];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++;
            map.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        int[] ans = new int[numCourses];
        int count = 0;
        // 找起始點
        for (Integer cur : map.keySet()) {
            if (indegree[cur] == 0) {
                q.add(cur);
            }
        }
        
        // BFS
        while(!q.isEmpty()) {
            int cur = q.remove();
            ans[count++] = cur;
            
            ArrayList<Integer> neighbor_list = map.get(cur);
            for (int i = 0; i < neighbor_list.size(); i++) {
                indegree[neighbor_list.get(i)]--;
                if (indegree[neighbor_list.get(i)] == 0) {
                    q.add(neighbor_list.get(i));
                }
            }
        }
        
        if (count == numCourses) {
            return ans;
        } else {
            return new int[0];
        }
    }
}


// 這個方法超時, 因為每次移除edges都要遍歷整個hashmap
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 思路：
        // Step1: 建圖, 同時必須知道每個點的indegree.
        // Step2: 從indegree = 0的開始走
        // Step3: 把鄰居indegree - 1, 若有變成indegree為0的鄰居就把他加入queue
        // 用HashSet Visited來去重
        //List<List<Integer, Integer>> graph = new ArrayList<>();
        
        
        // Step1
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            if (!map.containsKey(prerequisites[i][0])) {
                HashSet<Integer> neighbor = new HashSet<>();
                neighbor.add(prerequisites[i][1]);
                map.put(prerequisites[i][0], neighbor);
            } else {
                map.get(prerequisites[i][0]).add(prerequisites[i][1]);
            }
        }
        // 補齊沒有被加入的點, 也就是indegree = 0 的點
        for (int i = 0; i < numCourses; i++) {
            if (!map.containsKey(i)) {
                map.put(i, new HashSet<Integer>());
            }
        }
            
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        
        // Step2
        for (Integer cur : map.keySet()) {
            if (map.get(cur).size() == 0) {
                q.add(cur);
                visited.add(cur);
            }
        }
        
        // Step3
        while (!q.isEmpty()) {
            int cur = q.remove();
            ans.add(cur);
            // 遍歷hashmap, 找每個hashset有cur的, 把它移除, 並確認移除後hashset == 0 就可以加入
            for (int node : map.keySet()) {
                if (map.get(node).contains(cur)) {
                    map.get(node).remove(cur);
                }
                if (map.get(node).size() == 0 && !visited.contains(node)) {
                    q.add(node);
                    visited.add(node);
                }
            }
        }
        
        if (ans.size() == numCourses) {
            return true;
        }
        return false;
    }
}