class Solution {
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        HashMap<Character, Integer> map = new HashMap<>();
        int number_of_character = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), 1);
                number_of_character += 1;
            } else {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }
        }
        
        // Max Heap
        Comparator<Map.Entry<Character, Integer>> cmp = new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
                // max heap
                return e2.getValue() - e1.getValue();
            }  
        };
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<Map.Entry<Character, Integer>>(number_of_character, cmp);
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.add(entry);
        }
        
        // output ans
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number_of_character; i++) {
            Map.Entry<Character, Integer> cur = pq.remove();
            for (int j = 0; j < cur.getValue(); j++) {
                sb.append(cur.getKey());
            }
        }
        return new String(sb);
    }
}

// 解法2: Bucket sort
class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }
        
        List<Character> [] bucket = new List[s.length() + 1];
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(entry.getKey());
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                // 同個bucket有幾個字母
                for (int j = 0; j < bucket[i].size(); j++) {
                    // 該字母要append幾次
                    for (int k = 0; k < i; k++) {
                        sb.append(bucket[i].get(j));
                    }
                }
            }
        }
        return sb.toString();
    }
}