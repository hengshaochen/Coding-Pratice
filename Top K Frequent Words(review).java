class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        
        // 統計
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) {
                map.put(words[i], 1);
            } else {
                map.put(words[i], map.get(words[i]) + 1);
            }
        }
        
        // Min Heap
        Comparator<Map.Entry<String, Integer>> cmp = new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                if (e1.getValue() != e2.getValue()) {
                    return e1.getValue() - e2.getValue();
                } else {
                    return e2.getKey().compareTo(e1.getKey());
                }
            }
        };
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k, cmp);
        
        // 掃描hashmap建立heap
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (pq.size() < k) {
                pq.add(entry);
            } else {
                if (cmp.compare(entry, pq.peek()) > 0) {
                    pq.remove();
                    pq.add(entry);
                }
            }
        }
        
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(pq.remove().getKey());
        }
        Collections.reverse(ans);
        return ans;
        
    }
}