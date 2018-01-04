class Solution {
    public String[] findWords(String[] words) {
        String[] strs = {"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"};
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                map.put(strs[i].charAt(j), i);
            }
        }
        
        List<String> ans = new ArrayList<>();
        
        for (int i = 0; i < words.length; i++) {
            String cur = words[i].toUpperCase();
            int row = map.get(cur.charAt(0));
            boolean valid = true;
            for (int j = 1; j < words[i].length(); j++) {
                if (row != map.get(cur.charAt(j))) {
                    valid = false;
                    break;
                }
            }
            if (valid == true) {
                ans.add(words[i]);
            }
        }
        
        return ans.toArray(new String[0]);
    }
}