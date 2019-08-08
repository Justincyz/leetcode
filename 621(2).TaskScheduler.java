/*621. Task Scheduler
Medium: 
Given a char array representing tasks CPU need to do. It contains 
capital letters A to Z where different letters represent different 
tasks. Tasks could be done without original order. Each task could 
be done in one interval. For each interval, CPU could finish one 
task or just be idle.

However, there is a non-negative cooling interval n that means 
between two same tasks, there must be at least n intervals that 
CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will 
take to finish all the given tasks.

Example:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
*/

/*解题思路, 详细例子讲解可以参考底部的链接
这道题让我们安排CPU的任务，规定在两个相同任务之间至少隔n个时间点。由于题目中规定了两个相同
任务之间至少隔n个时间点，那么我们首先应该找到出现次数最多的那个任务，因为高频任务在最终结果
的时间段内肯定需要处理的次数是最多的，所以当我们保证了一个高频任务的间隔最起码是n之后，那么
低频任务一定可以在这之间的空闲时间片内拍好且保证同一个任务间隔起码是n。如果任
务A的出现频率最高，为k次，那么我们需要n个空位将每两个F分隔开，然后我们按顺序加入其他低频的
任务。如果高频任务出现了多次，比如说例子一中的A和B都出现了三次，我们就把这两个高频任务当做一个
整体来处理。
首先我们统计最高频率的任务出现了多少次，然后统计所有的高频任务有多少。
如果一组高频任务的处理区间大于n了，说明cpu就不会有空闲时间。比如说 [A,A,A,B,B,B,C,C,C],n=2
一组高频任务 ABC的长度为3，那么CPU不需要空闲时间，可以将处理顺序变为 [A,B,C,A,B,C,A,B,C]

如果高频任务组数目小于n，那么就有两个情况。第一，高频任务组之间的idle CPU片段可以满足所有
低频事物的处理. 换句话说，就是会出现idle的情况，因为事物填不满所有CPU时间。
[A,A,A,B,B,B,X,X,X], n=3. 我们这里的X代表所有的低频事物。
那么排列完其中之一的组合是 [A,B,X,X,A,B,X,idle,A,B]
这种情况下总共有 (max-1)个间隔，每个高频任务加上n时间段的冷却时间为(n+1)。
最后尾部粘连上一组高频事物。因为之后没有冷却时间的需求了，因为所有低频任务已经完成。

第二种情况是低频任务可以占用所有的CPU时间，且在高频任务所有结束完之后还没有全部完成。
[A,A,A,B,B,B,X,X,X,X], n=2
那么排列完其中之一的组合是 [A,B,X,A,B,X,A,B,X,X]
那么这样的一组任务就需要 tasks.length的时间来完成。之所以可以保证最后的低频事物处理
间隔起码是n,是因为低频任务出现的最大频率不会超过(高频-1)，所以利用之前出现的空位就
一定可以放得下(详细例子参考链接)

*/

class Solution {
    public int leastInterval(char[] tasks, int n) {
        if(n == 0) return tasks.length;
        int[] count = new int[26];
        for(char c: tasks){
            count[c-'A']++;
        }
        int max = 0;
        char topFreq = 'A';
        for(int i=0; i<26; i++){
            if(count[i]>max){
                max = count[i];
                topFreq = (char)(i+'A');
            }
        }
        int maxCount =1;
        for(int i=0; i<26; i++){
            if(count[i]==max && topFreq !=(char)(i+'A')){
                maxCount++;
            }
        }
        if(maxCount>n ) return tasks.length;

        int res = (n+1)*(max-1) + maxCount;
        return res>tasks.length ? res: tasks.length;
    }
}



class Solution {
    public int leastInterval(char[] tasks, int n) {
        if(n == 0) return tasks.length;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> b-a);
        int[] count = new int[26];
        for(char c: tasks)  count[c-'A']++;
        for(int c : count){
            if(c!=0) pq.add(c);
        } 
           
        int cycle = n+1, res =0;
        while(!pq.isEmpty()){
            int cnt = 0;
            int[] t = new int[cycle];
            for(int i=0; i<cycle; i++){
                if(!pq.isEmpty()){
                    int top = pq.remove();
                    t[cnt++] = top;
                }
            }
            
            for(int d: t){
                if(--d >0) pq.add(d);
            }
            res+= pq.isEmpty() ? cnt : cycle;
        }

        return res;
    }
}


//https://www.cnblogs.com/grandyang/p/7098764.html




