// 乾淨版本
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");

        int length = Math.max(levels1.length, levels2.length);
        for (int i=0; i<length; i++) {
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0) {
                return compare;
            }
        }

        return 0;
    }
}

// 我的版本
class Solution {
    public int compareVersion(String version1, String version2) {
        if (version1.compareTo("") == 0 && version2.compareTo("") == 0) { return 0; }
        if (version1.compareTo("") != 0 && version2.compareTo("") == 0) {
            return 1;
        } else if (version1.compareTo("") == 0 && version2.compareTo("") != 0) {
            return -1;
        }
        
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        int s1_ptr = 0;
        int s2_ptr = 0;
        
        // 當兩個都還能比的時候
        while (s1_ptr < s1.length && s2_ptr < s2.length) {
            int cur_s1 = Integer.parseInt(s1[s1_ptr++]);
            int cur_s2 = Integer.parseInt(s2[s2_ptr++]);
            System.out.println(cur_s1 + " " + cur_s2);
            if (cur_s1 > cur_s2) {
                return 1;
            } else if (cur_s1 < cur_s2) {
                return -1;
            }
        }
        
        // 比到最後相等
        if (s1_ptr == s1.length && s2_ptr == s2.length) {
            return 0;
        } else if (s1_ptr < s1.length) {
            // s1還沒跑完但s2跑完, 代表s1大, 但有可能最後幾位都是0, 就要return 相等
            while (s1_ptr < s1.length) {
                if (Integer.parseInt(s1[s1_ptr++]) != 0) {
                    return 1;
                }
            }
            return 0;
        } else {
            while (s2_ptr < s2.length) {
                if (Integer.parseInt(s2[s2_ptr++]) != 0) {
                    return -1;
                }
            }
            return -0;
        }
    }
}