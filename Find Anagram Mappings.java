// 760. Find Anagram Mappings
class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i < B.length; i++) {
            map.put(B[i], i);
        }
        
        int[] mapping = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            mapping[i] = map.get(A[i]);
        }
        return mapping;
    }
}