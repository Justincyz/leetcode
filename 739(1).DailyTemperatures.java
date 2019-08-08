/*739. Daily Temperatures
Medium: 
Given a list of daily temperatures T, return a list such that, 
for each day in the input, tells you how many days you would have 
to wait until a warmer temperature. If there is no future day 
for which this is possible, put 0 instead.

For example, given the list of temperatures 
T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be 
[1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. 
Each temperature will be an integer in the range [30, 100].
*/

/*解题思路
题目让我们找到每个位置数字的下一位比他大的数，和这一位数字的距离是多少。其实这是一道
很简单的问题，可是一开始想的太复杂了。我们用一个stack()来储存一个递减数列(储存的是
数组中数字的位置，不是数值本身)。我们从头开始遍历数组，如果stack不为空且当前数字比
stack的top还大，那么说明当前数字是第一个比stack top大的数。那我们就把stack中最上面
这一位取出来，计算和当前位之间的距离，放在新建的 array中相对应的位置。我们一直把stack
中所有比当前位小的都拿出来并且计算距离，直到stack为空或者有比当前位大的数，此时我们就
把当前位放入stack中。总体思路是这样，我们看如何操作例子中的数组。
 0
[73,74,75,71,69,72,76,73], res=[0,0,0,0,0,0,0,0], stack -> 0 (记录的是位置，比较的是位置上面的值)
     1
[73,74,75,71,69,72,76,73], res [1,0,0,0,0,0,0,0], stack -> 1
        2
[73,74,75,71,69,72,76,73], res [1,1,0,0,0,0,0,0], stack -> 2
          3
[73,74,75,71,69,72,76,73], res [1,1,0,0,0,0,0,0], stack -> 2, 3
              4
[73,74,75,71,69,72,76,73], res [1,1,0,0,0,0,0,0], stack -> 2, 3, 4
                 5
[73,74,75,71,69,72,76,73], res [1,1,0,2,1,0,0,0], stack -> 2, 5
                    6
[73,74,75,71,69,72,76,73], res [1,1,4,2,1,1,0,0], stack -> 6
                       7
[73,74,75,71,69,72,76,73], res [1,1,4,2,1,1,0,0], stack -> 6, 7
最后res[]没有被填满的数字说明不存在比空位温度还高的值
*/

class Solution {
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> s = new Stack();
        int[] res = new int[T.length];
        for(int i=0; i<T.length; i++){
            while(!s.isEmpty()){
                if(T[i]> T[s.peek()]){
                    res[s.peek()] = i -s.pop();
                }else{
                    break;
                }
            }
            s.push(i);
        }
        return res;
    }
}