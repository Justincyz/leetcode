/*981. Time Based Key-Value Store
Medium

Create a timebased key-value store class TimeMap, that supports two operations.

1. set(string key, string value, int timestamp)
Stores the key and value, along with the given timestamp.

2. get(string key, int timestamp)
Returns a value such that set(key, value, timestamp_prev) was called previously,
 with timestamp_prev <= timestamp.
If there are multiple such values, it returns the one with the largest timestamp_prev.
If there are no values, it returns the empty string ("").


Example 1:

Input: 
inputs = ["TimeMap","set","get","get","set","get","get"], 
inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
Output: [null,null,"bar","bar",null,"bar2","bar2"]
Explanation:   
TimeMap kv;   
kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
kv.get("foo", 1);  // output "bar"   
kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at 
··timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
kv.set("foo", "bar2", 4);   
kv.get("foo", 4); // output "bar2"   
kv.get("foo", 5); //output "bar2"   

Example 2:

Input: 
inputs = ["TimeMap","set","set","get","get","get","get","get"], 
inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],
··["love",15],["love",20],["love",25]]
Output: [null,null,null,"","high","high","low","low"]

*/

/*解题思路，二分法
题目的大意就是找到和key对应的value, 这个value对应的timestamp是最接近于并且小于给定Key的timestamp。
做这道题的第一个想法就是用新学的treemap来做，因为treemap有一个功能就是可以在O(logn)的时间找到
最接近给定数值的一个floor value和ceiling value. 通过treemap.floorEntry()和treemap.ceilingEntry()
获得。那下一步就是确定关系，首先因为value有可能重复，所以一个key会对应多个value, 每个value都有一个
独特的timestamp.所以我们要用到hashmap, map的值是我们要构造的treemap, treemap的Key用timestamp
作为唯一的key。 每一次都查找是否有给定key的元素。如果有的话，再获取和key对应的treemap。最后查找
是否有给定timestamp的floorEntry(timestamp)。

这是其中一种用二分法的办法，或者也可以将map的值存为一个有序的List<>。每一次对list<>做二分法来
查找是否有对应的值


*/

class TimeMap {
    Map<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)) map.put(key, new TreeMap());
        map.get(key).put(timestamp, value);
    }
    
     public String get(String key, int timestamp) {
        TreeMap<Integer, String> tm = map.get(key);
        Map.Entry<Integer, String> entry = tm.floorEntry(timestamp);
        if(entry == null) return "";
        else return entry.getValue();
    }
}


class TimeMap {
    Map<String, List<Data>> data;
    public TimeMap() {
        data = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!data.containsKey(key))
            data.put(key, new ArrayList<>());
        data.get(key).add(new Data(value, timestamp));//timestamp是升序的，所以直接插入到尾部
    }
    
    public String get(String key, Integer timestamp) {
        if (!data.containsKey(key)) return "";
        return binarySearch(data.get(key), timestamp);
    }
    
    private String binarySearch(List<Data> list, int timestamp) {
        int left = 0, right = list.size() - 1;
        
        while (left < right) {
            int mid = (left + right) / 2;
            Data d = list.get(mid);
            if (d.timestamp == timestamp)
                return d.value;
            else if (d.timestamp < timestamp) {
                if (list.get(mid+1).timestamp > timestamp) return d.value;
                left = mid + 1;  
            } else right = mid - 1;
        }
                
        return right < 0 ? "" : list.get(right).value;
    
}


public class Data {
    String value;
    int timestamp;
    public Data (String v, int t) {
        value = v;
        timestamp = t;
    }   
}