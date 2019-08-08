/*1054. Distant Barcodes
Medium: 
In a warehouse, there is a row of barcodes, where the i-th barcode 
is barcodes[i].

Rearrange the barcodes so that no two adjacent barcodes are equal.  
You may return any answer, and it is guaranteed an answer exists.
*/

/*解题思路
这道题目跟767: Reorganize String一模一样，而且放宽了条件，告诉我们一定有答案。
第一个做法还是先计算每个数对应的频率，然后用priorityqueue找到每一次最大的频率，
然后每次间隔一个来排列。

*/

class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        int[] res = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i: barcodes) map.put(i, map.getOrDefault(i,0)+1);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[1]-a[1]);
        
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }
        
        int pos =0;
        while(!pq.isEmpty()){
            int[] t = pq.remove();
            int key = t[0];
            int value = t[1];    
            
            while(value-- >0 ){
                if(pos+1 >n) pos = 1;
                res[pos] = key;
                pos+=2;     
            }
        }
              
        return res;
    }
}



/*
其实我们没必要用最大堆来获取每次频率最大的数，我们只需要找到全局最大频率的数，然后先
排列好这个数就好了。因为题目中说了保证有答案，所以全局最大的频率也不会超过总数的1/2
所以这个做法的时间和空间都是O(n)
*/

class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        int[] res = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i: barcodes) map.put(i, map.getOrDefault(i,0)+1);
        
        int key =0, value =0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(entry.getValue() > value){
                key = entry.getKey();
                value = entry.getValue();
            }
        }
        map.remove(key);//把频率最大的拿掉
        int pos =0;
        while(value-- >0 ){
            res[pos] = key;
            pos+=2;     
        }     
        
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            key = entry.getKey();
            value = entry.getValue();
            while(value-- >0 ){
                if(pos+1 >n) pos = 1;
                res[pos] = key;
                pos+=2;     
            }
        }
              
        return res;
    }
}