/*1209. Remove All Adjacent Duplicates in String II
链接：https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
Medium: 
Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.
Return the final string after all such duplicate removals have been made.
It is guaranteed that the answer is unique.

 

Example 1:
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"

Example 3:
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"
 

Constraints:
1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters.
*/

/*解题思路
这道题给我们一个字符串和一个数字K, 让我们把字符串中所有连续的相同的K个字符
去除掉。像消消乐一样，如果去除掉中间字符串之后，前后的字符拼接在一起还能组合出K
个连续的字符，我们要继续消除直到最终结果。
第一种方法就是最原始的办法，我们不停地遍历字符串，把临近相同的K个元素消除。然后将其余
字符存入stringbuffer变量，带到下一个循环中继续消除。

*/

class Solution {
    public String removeDuplicates(String s, int k) {
        StringBuffer sb = new StringBuffer(s);
        boolean flag = true;
        while(flag){
            StringBuffer next = new StringBuffer();
            int fast = 0, slow = 0;//用two pointer的办法来做
            while(fast < sb.length()){
                if(sb.charAt(fast) != sb.charAt(slow)){
                    //只会更新slow指针的值
                    slow = checkDuplication(slow, fast,k, sb, next);
                }
                fast++;
            }
            slow = checkDuplication(slow,fast, k, sb, next);
            
            if(next.length() == sb.length()){
                flag = false;
            }
            sb = next;
        }
        return sb.toString();
    }
    
    public int checkDuplication(int slow, int fast,int k, StringBuffer curLevel, StringBuffer nextLevel){
        if(fast-slow >= k){//说明slow到fast之前的一位全是重复的，我们直接跳掉K位
            slow+=k;
        }
        //将slow到fast直接的元素添加进next中
        while(slow <fast){
            nextLevel.append(curLevel.charAt(slow++));
        }
        return slow;
    }
}


/*
这道题目还可以用stack来做，这是我自己写的，因为题目说全部都是小写的
英文字母，所以我可以直接用数字来表示英文字母。

时间复杂度O(N)，空间复杂度O(N)
*/

class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Integer> stack = new Stack<>();//栈中先放重复个数，再放元素
        for(char c: s.toCharArray()){
        	//获取对应字符的数字表示
            int charToInt = c-'a';
            if(!stack.isEmpty()&& stack.peek() == charToInt){
                int top = stack.pop();//上一个元素是什么
                int updatedFreq = stack.peek()+1; //上一个元素连续出现的频率+1
                if(updatedFreq == k){
                    stack.pop();
                }else{//个数小于k的情况,更新此元素频率
                    stack.pop();
                    stack.push(updatedFreq);
                    stack.push(top);
                }
            }else{
            	//这里是两个情况，第一个是数组为空的情况，第二个是栈顶元素和当前元素不一样
                stack.push(1);
                stack.push(c-'a');
            }
        }
        StringBuffer sb = new StringBuffer();
        while(!stack.isEmpty()){
            int c = stack.pop(), freq = stack.pop();
            while(freq-- >0) sb.append((char)(c+'a'));
        }
        return sb.reverse().toString();
    }
}


/*
这一种办法和上面我写的其实一样，但是用了和python里面差不多的一种
结构是我原来不知道的，可以直接调用javafx.util.Pair 这个包来组合
出[整数，字符]pair来
*/
import javafx.util.Pair;
class Solution {
    public String removeDuplicates(String s, int k) {
        Deque<Pair<Integer, Character>> stack = new ArrayDeque<>();
        stack.push(new Pair(0, '#'));

        for (char c : s.toCharArray()) {
            if (stack.peek().getValue() != c) {
                stack.push(new Pair(1, c));
            } else {
                int count = stack.peek().getKey() + 1;
                stack.pop();
                if (count != k)
                    stack.push(new Pair(count, c));
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            for (int i = 0; i < stack.peek().getKey(); i++) {
                stringBuilder.append(stack.peek().getValue());
            }
            stack.pop();
        }

        return stringBuilder.reverse().toString();
    }
}