/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 先統計兩個的長度
        // 把較長的那個list先移動 |length.A - length.B| , 接著第一個遇到相同的node就是begin
        
        int lenA = 0;
        int lenB = 0;
        ListNode copyA = headA;
        ListNode copyB = headB;
        while (copyA != null) {
            lenA++;
            copyA = copyA.next;
        }
        while (copyB != null) {
            lenB++;
            copyB = copyB.next;
        }
        
        int diff = Math.abs(lenA - lenB);
        if (lenA > lenB) {
            while (diff > 0) {
                headA = headA.next;
                diff--;
            }
        } else {
            while (diff > 0) {
                headB = headB.next;
                diff--;
            }
        }
        
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
}