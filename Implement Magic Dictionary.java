class MagicDictionary {

    /** Initialize your data structure here. */
    Set<String> set;
    public MagicDictionary() {
        set = new HashSet<>();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (int i = 0; i < dict.length; i++) {
            set.add(dict[i]);
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < 26; j++) {
                // 現在鎖定要更改的char位置
                char cur_idx_ori_char = word.charAt(i);
                StringBuilder sb = new StringBuilder(word);
                // 要更改的char
                char cur_modify_char = (char)(j + 'a');
                
                // *ello, 不能改成hello, 因為原本就有h, 要改成除了h之外所有a-z的
                if (cur_idx_ori_char != cur_modify_char) {
                    // 更改特定char
                    sb.setCharAt(i, cur_modify_char);
                    
                    // 把更改一個char後的string和set中string比較
                    if (set.contains(sb.toString())) {
                        System.out.println(sb);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */