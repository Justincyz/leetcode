/*686. Repeated String Match
Easy: 

Given two strings A and B, find the minimum number of times A has 
to be repeated such that B is a substring of it. If no such solution, 
return -1.

For example, with A = "abcd" and B = "cdabcdab".

Return 3, because by repeating A three times (“abcdabcdabcd”), B is a 
substring of it; and B is not a substring of A repeated two times ("abcdabcd").


*/

/*解题思路
题目大意是这样的，问我们是否可以将A string重复几次，使得B是A的一个substring。
这道题目一开始我的思路不太对，我用A去遍历B，然后将B中没有和A一模一样的输出，最后检查A。
其实不需要这么麻烦，假设B是A(重复自己几次)之后的substring，那么我们先将A append到
自己后面，直到长度大于B。此时我们可以直接调用 string.contains()函数，来检查A是否包含
了B。假如此时没有的话，我们再增加一个A。原因是我们有可能遇到最后异截没有被包含进去，比如说:
A = 'abc', B = 'abcabcab'。所以考虑到这种情况，如果我们再增加一节A也无法将B包含的话，
那就输出-1, 否则输出 count+1.
因为是substring, 所以B中A出现的顺序也很重要，假设A= 'abcd', 那么B不可能是'abcabcd'，
因为虽然abc是A的substring, 但是如果后面跟了 'abcd'的话，那么前面必须是'abcd', 'bcd','cd'
或者'd'中的一个。

*/

class Solution {
    public int repeatedStringMatch(String A, String B) {
        int m= A.length(), n = B.length(), count = 1;
        String t = A;
        while(t.length()<n){
            t+=A;
            count++;
        }
        if(t.contains(B)) return count;
        t+=A;
        return t.contains(B) ? count+1: -1;
    }
}

/*
跟上面的方法其实一样，但是速度快了不少。原因是用了StringBuffer的 indexOf(String)
的办法。sb.indexOf(String) 会返回stringbuffer中第一次出现String的位置。在这里
的意思就是，如果B在sb中出现的话，那么就说明当前的sb已经包含了B。可以直接返回。
*/
class Solution {
    public int repeatedStringMatch(String A, String B) {
        int m= A.length(), n = B.length(), count = 0;
        String t = A;
        StringBuffer sb = new StringBuffer();
        while(sb.length()<n){
            count++;
            sb.append(A);
        }
        if(sb.indexOf(B)>=0) return count;
      
        sb.append(A);
        return sb.indexOf(B)>= 0? count+1: -1;
       
    }
}


/*
这是一种O(m*n)的解法，虽然速度很慢，但是可以不涉及到内置的indexOf(), contains()
等函数的调用。
*/
class Solution {
    public int repeatedStringMatch(String A, String B) {
        int m= A.length(), n = B.length();
        
        for (int i = 0; i < m; ++i) {
            int j =0;
            while(j< n && A.charAt((i+j)%m) == B.charAt(j)){
                j++;
            }
            if(j == n) return ((i+j-1)/m) +1;
             
        }
       return -1;
    }
}





