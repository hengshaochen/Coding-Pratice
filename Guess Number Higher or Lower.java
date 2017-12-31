/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (guess(mid) == 1) {
                left = mid;
            } else if (guess(mid) == -1) {
                right = mid;
            } else if (guess(mid) == 0) {
                left = mid;
            }
        }
        
        if (guess(left) == 0) {
            return left;
        }
        return right;
    }
}