/*386. Lexicographical Numbers
链接：https://leetcode.com/problems/lexicographical-numbers/
Medium: 
Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
*/

/*解题思路
这道题目让我们按照字典顺序输出1到n的全部数字。字典顺序指的是先按照每一位数
首位大小排列，然后再看第二位，第三位。这个可以根据例子或者在网上看看。

解法一:
最简单的办法就是自定义一个排序的方法，我们每一位每一位的比较，如果
某一位当中两个数字不一样，就将较小的数字放在前面。如果有这么两位，"1111"
和"111"，这个时候"111"应该在"1111"前面，所以这就到了最后比较两个字符串
长度的statement了。
最后将string都转化成int输出结果。
时间复杂度 O(nlogn)，空间O(n)
速度比较慢

*/
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<String> strOfInt = new ArrayList<>();
        for(int i=1; i<= n; i++){
            strOfInt.add(String.valueOf(i));
        }
       
        Collections.sort(strOfInt, new Comparator<String>(){
            public int compare(String a, String b){
                int len = Math.min(a.length(), b.length());
                for(int i=0; i< len; i++){
                    if(a.charAt(i) != b.charAt(i)){
                        return a.charAt(i)-b.charAt(i);
                    }
                }
                if(a.length() > b.length()) return 1;
                else return -1;
            }
        });
        List<Integer> res = new ArrayList<>();
        for(String s: strOfInt) res.add(Integer.valueOf(s));
        return res;
    }
}

/* dfs
解法二:

这种方式很巧妙，我们用了dfs的办法来做，方法很显而易见就是先把字典顺序小的
数字生成出来。利用了dfs的办法，但是要注意，两个for循环一个是从1开始，一个是
从0开始。

时间& 空间:O(N) 
*/
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for(int i=1; i<=Math.min(n, 9); i++){
            res.add(i);
            dfs(i, n, res);
        }
        return res;
    }
    
    public void dfs(int num, int n, List<Integer> res){
        
        
        for(int i=0; i<=9; i++){
            if(num*10+i > n) return;
            res.add(num*10+i);
            dfs(num*10+i, n, res);
        }
    }
}