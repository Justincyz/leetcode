/*901. Online Stock Span
Medium
链接: https://leetcode.com/problems/online-stock-span/

Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.

The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.

For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].

 
Example 1:
Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
Output: [null,1,1,1,2,1,4,6]
Explanation: 
First, S = StockSpanner() is initialized.  Then:
S.next(100) is called and returns 1,
S.next(80) is called and returns 1,
S.next(60) is called and returns 1,
S.next(70) is called and returns 2,
S.next(60) is called and returns 1,
S.next(75) is called and returns 4,
S.next(85) is called and returns 6.

Note that (for example) S.next(75) returned 4, because the last 4 prices
(including today's price of 75) were less than or equal to today's price.
 

Note:
Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
There will be at most 10000 calls to StockSpanner.next per test case.
There will be at most 150000 calls to StockSpanner.next across all test cases.


*/

/*解题思路 stack
这道题目挺有意思的，告诉我们有这样的一个股票的stream, 会依次被next()方法调用。让我们找到每一个股票之前与当前股票相连并且比当前股票数值小的股票。我们拿例子来讲解一下。

这是输入的数组。[[],[100],[80],[60],[70],[60],[75],[85]]
对于第一个100来说，他这之前没有别的股票出现过，所以结果是1.
对于第二个80来说，他之前虽然出现过100了，但是100比他大，所以结果还是1
对于第三个60来说，原因同上，结果还是1
对于第四个70来说，在这之前60比他小并且可以连在一块，所以结果就是2
对于第五个60来说，他之前的70比他大，所以结果还是他自己，为1
对于第六个75来说，他这之前 60，70，60 这三个都比他小，并且可以连在一块，所以结果就是4
对于第七个85来说，他这之前 80, 60，70，60，75 这五个都比他小，并且可以连在一块，所以结果就是6

因为这个和顺序严重相关，所以我们最简单的办法就是用stack来保持一个从最小单调栈。
这样当我们新进来的元素比栈顶的元素大的时候，说明在栈顶元素到当前元素之前的所有元素都小于当前元素。
那么第二个问题就是我们怎么知道这之间有多少的元素呢？那么我们可以栈中我们可以存入一个数组而不是单纯的整数，数组的第一位代表元素的值是多少，第二位就代表了这个元素在他之前比他小的连续长度有多长。我们用当前的price和栈顶的元素进行比较，如果比栈顶的元素大的话，就把栈顶元素保存的连续较小天数累加到当前的元素中去。我们重复这个步骤直到栈为空或者栈顶的元素比当前的元素大，此时我们将当前元素和这个元素的较小最长连续天数添加到栈顶当中。并且返回这个较小的最长连续天数。

因为元素最多入栈和出栈各一次，所以时间复杂度和空间复杂度都是O(N)

*/


class StockSpanner {
    Stack<int[]> stack;
    
    public StockSpanner() {
        stack = new Stack<>();
    }
    
    public int next(int price) {
        int count = 1;
        while(!stack.isEmpty() && stack.peek()[0] <= price){
            count+=stack.pop()[1];
        }
        
        stack.push(new int[]{price, count});
        return count;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
