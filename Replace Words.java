class Solution {
class Trie {
    class TrieNode {
        TrieNode[] child;
        boolean is_word;
        TrieNode() {
            child = new TrieNode[26];
            is_word = false;
        }
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int index = (int)(word.charAt(i) - 'a');
            // 不存在該字母的Node就建立該字母的Node
            if (cur.child[index] == null) {
                cur.child[index] = new TrieNode();
            }
            cur = cur.child[index];
        }
        // 最後一個字母的boolean設為true
        cur.is_word = true;
    }
    
    public String findPrefix(String word) {
        TrieNode cur = root;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            int index = (int)(word.charAt(i) - 'a');
            
            // 如果字典有該字的前綴, 例如查詢cattle, 字典有cat, 查到t這個字後, 確認cat是否為一個word, 
            // 如果為word, 就代表當前這是keyword, 回傳sb. 如果不為word, 代表這不是字典中的word, 只是某個word的前綴而已, 因此回傳原本的word
            if (cur.is_word == true) {
                return sb.toString();
            } else if (cur.child[index] == null && cur.is_word == false) {
                return word;
            }
            
            // 找到 把他連到sb上 並且繼續找下去 直到最後一個char才結束
            sb.append(word.charAt(i));
            cur = cur.child[index];
        }
        return sb.toString();
    }
    
}
    public String replaceWords(List<String> dict, String sentence) {
        Trie trie = new Trie();
        for (int i = 0; i < dict.size(); i++) {
            trie.insert(dict.get(i));
        }
        
        String[] sp = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sp.length; i++) {
            sb.append(trie.findPrefix(sp[i]) + " ");
        }
        
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}