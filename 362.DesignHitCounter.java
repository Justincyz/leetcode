/*362. Design Hit Counter
Medium
Design a hit counter which counts the number of hits received in
 the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity)
 and you may assume that calls are being made to the system in 
 chronological order (ie, the timestamp is monotonically increasing).
You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:

HitCounter counter = new HitCounter();
// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301); 
*/

/*解题思路
没想到这种很简单的办法速度也很快，一开始问题想复杂了。

*/

class HitCounter {
    List<Integer> list ;
    /** Initialize your data structure here. */
    public HitCounter() {
        list = new ArrayList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        list.add(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        check(timestamp);
        return list.size();
    }
    
    public void check(int ts){        
        int len = 0;
        for(int i=0; i< list.size(); i++){
            if((ts-list.get(i))>= 300) len++;
            else break;
        }
        list = list.subList(len, list.size());
    }
}



/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */