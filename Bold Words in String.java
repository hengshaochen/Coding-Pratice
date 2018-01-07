class Solution {
    public String boldWords(String[] words, String S) {
        // 用一個boolean[]來表示這個區間是否符合
        boolean[] match = new boolean[S.length()];
        for (int i = 0; i < words.length; i++) {
            // current words length
            int cur_length = words[i].length();
            for (int j = 0; j < S.length() - cur_length + 1; j++) {
                int cur_start = j;
                int cur_end = cur_start + cur_length - 1;
                //System.out.println(cur_start + " " + cur_end + " " + words[i] + " " + S.substring(cur_start, cur_end + 1));
                if (words[i].compareTo(S.substring(cur_start, cur_end + 1)) == 0) {
                    for (int k = cur_start; k <= cur_end; k++) {
                        match[k] = true;
                    }
                }
            }
        }
        /*
        for (int i = 0; i < S.length(); i++) {
            System.out.print(match[i] + " ");
        }
        */
        
        // 串起答案
        boolean isFirst = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (isFirst == true && match[i] == true) {
                sb.append("<b>");
                isFirst = false;
            } else if (isFirst == false && match[i] == false) {
                sb.append("</b>");
                isFirst = true;
            }
            sb.append(S.charAt(i));
        }
        // 代表最後一個是match, 但到結束都還沒有封閉 補上</b>
        if (isFirst == false) {
            sb.append("</b>");
        }
        return sb.toString();
    }
}