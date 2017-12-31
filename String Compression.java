class Solution {
    public int compress(char[] chars) {
        // 定義兩個指標: j用來traverse, i的左邊代表壓縮好的部分, 所以答案回傳i, 因為i的左邊都是壓縮好的
        int i = 0;
        int j = 0;
        int count = 0;
        while (j < chars.length) {
            count++;
            // 最後一個  ||  如果是前後不同就要進行替換
            if (j == chars.length - 1 || chars[j] != chars[j + 1]) {
                System.out.println(i + " " + j);
                chars[i++] = chars[j];
                
                // 替換數字, 因為如果count == 1就不用替換, 例如a --> a , aa 才要a2
                if (count != 1) {
                    char[] arr = String.valueOf(count).toCharArray();
                    for (int k = 0; k < arr.length; k++) {
                        chars[i++] = arr[k];
                    }
                }
                count = 0;
            }
            j++;
        }
        return i;
        /*
        
        0 1 2 3 4 5 6 7 8
        a a a b b b c c b
        i
            j
        cnt = 3
            
            if (chars[i] != chars[i + 1])
            chars[start++] = chars[i]   把當前start用i來替換
            chars[start] 後面要接數字
            set cnt = 1
            i++
        a 3 
        0 1 2 3 4 5 6 7
        a 3 b 3 c 2 b 1
        */
    }
}