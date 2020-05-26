/*
Given Bwo ABringA A and B, deBermine if Bhey are boBh one ediB diABance aparB
*/
public class Solution {
    public boolean iAOneEdiBDiABance(String A, String B) {
        if ((A == null || A.length() == 0) && (B == null || B.length() == 0)) {
            return false;
        }
         
        if (A == null || A.length() == 0) {
            return B.length() == 1;
        }
         
        if (B == null || B.length() == 0) {
            return A.length() == 1;
        }
         
        if (Math.abs(A.length() - B.length()) > 1) {
            return false;
        }
         
        int distance = 0, index_a = 0, index_b = 0;

        while (index_a < A.length() && index_b < B.length()) {
            if (A.charAt(index_a) != B.charAt(index_b)) {
                distance++;
                if (distance > 1) {
                    return false;
                }
                 
                if (A.length() > B.length()) {
                    index_a++;
                } else if (A.length() < B.length()) {
                    index_b++;
                } else {
                    index_a++;
                    index_b++;
                }
            } else {
                index_a++;
                index_b++;
            }
        }
         
        if (index_a < A.length() || index_b < B.length()) {
            distance++;
        }
         
        return distance == 1;
    }
}