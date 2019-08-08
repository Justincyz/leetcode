/*635. Design Log Storage System
Medium: 
You are given several logs that each log contains a unique id and 
timestamp. Timestamp is a string that has the following format: 
Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. 
All domains are zero-padded decimal numbers.

Design a log storage system to implement the following functions:

void Put(int id, string timestamp): Given a log's unique id and 
timestamp, store the log in your storage system.


int[] Retrieve(String start, String end, String granularity): 
Return the id of logs whose timestamps are within the range from 
start to end. Start and end all have the same format as timestamp. 
However, granularity means the time level for consideration. 
For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", 
granularity = "Day", it means that we need to find the logs within the 
range from Jan. 1st 2017 to Jan. 2nd 2017.

Example 1:
put(1, "2017:01:01:23:59:59");
put(2, "2017:01:01:22:59:59");
put(3, "2016:01:01:00:00:00");
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); 
// return [1,2,3], because you need to return all logs within 2016 and 2017.
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); 
// return [1,2], because you need to return all logs start from 2016:01:01:01 
to 2017:01:01:23, where log 3 is left outside the range.

Note:
There will be at most 300 operations of Put or Retrieve.
Year ranges from [2000,2017]. Hour ranges from [00,23].
Output for Retrieve has no order required.

*/

/*解题思路
这道题让我们设计一个日志存储系统，给了日志的生成时间和日志编号，日志的生成时间是精确到秒的，
然后我们主要需要完成一个retrieve函数，这个函数会给一个起始时间，结束时间，还有一个granularity
精确度，可以精确到任意的年月日时分秒。也就是说我们只要比对到gra给出的精度，后面的都可以不用管。
看了一圈其他的办法，好像基本都是O(n)的解。这道题目首先想用Trie Tree的结构，但是发现
没有很好地办法可以定位到下一层的位置。因为假设精度到秒的话，但是范围在月的时候就分叉了，那么
我们再进入下一层的时候还要重新定位起始位置和结束比对的位置。
这种用O(N)的办法也很快，不需要拆分string了，可以直接用compareTo()的办法比较两个
字符串字典顺序的大小。在范围之内的就储存到结果中。

*/

class LogSystem {
    Map<String, Integer> map = new HashMap<>();
    List<Node> list = new ArrayList<>();
    public LogSystem() {
        map.put("Year", 4);
        map.put("Month", 7);
        map.put("Day", 10);
        map.put("Hour", 13);
        map.put("Minute",16);
        map.put("Second", 19);
    }
    
    public void put(int id, String timestamp) {
        list.add(new Node(timestamp, id));
    }
    
    public List<Integer> retrieve(String s, String e, String gra) {
        int pos = map.get(gra);
        s = s.substring(0, pos);
        e = e.substring(0, pos);
        List<Integer> res = new ArrayList<>();

        for(Node time : list){
            String sub = time.timestamp.substring(0,pos);
            if(sub.compareTo(s)>=0 && sub.compareTo(e)<=0) res.add(time.id);
        }   
        return res;
    }
}

class Node{
    String timestamp;
    int id;
    public Node(String timestamp, int id){
        this.timestamp = timestamp;
        this.id = id;
    }
}
/*
或者也可以用treeMap来辅助，缩小选择的范围。
map.tailMap(start)方法是呈现所有比start大的值
*/



class LogSystem {
    private TreeMap<String, Integer> map;
    Map<String, Integer> mapidx = new HashMap<>();
   
    public LogSystem() {
        map = new TreeMap<>();
        mapidx.put("Year", 4);
        mapidx.put("Month", 7);
        mapidx.put("Day", 10);
        mapidx.put("Hour", 13);
        mapidx.put("Minute",16);
        mapidx.put("Second", 19);
    }
    
    public void put(int id, String timestamp) {
        map.put(timestamp, id);
    }
    
    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> res = new ArrayList<>();
        if (s.compareTo(e) > 0) {
            return res;
        }
        int offset = mapidx.get(gra);
        String start = s.substring(0, offset);
        String end = e.substring(0, offset);
        for (String time : map.tailMap(start).keySet()) {
            if (time.substring(0, offset).compareTo(end) > 0) {
                break;
            }
            res.add(map.get(time));
        }
        return res;
    } 
}


/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */