/*399. Evaluate Division
Medium: 
Equations are given in the format A / B = k, where A and B are variables 
represented as strings, and k is a real number (floating point number). 
Given some queries, return the answers. If the answer does not exist, 
return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, 
vector<pair<string, string>> queries , where equations.size() == values.size(), 
and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
*/

/*解题思路  Union Find
题目给出了很多组变量和变量之间的比值关系，给定一组数组，问数组中元素代表变量的比值关系。
这道题虽然网上的答案有不少，但是这个答案是自己写的。其实这道题目还是union find的一种
变种。假设多组变量的比值有连乘关系，那么我们都可以用其中一个变量来代表这一组的root, 然后
每个变量都可以根据root来找到相互之间的关系。假设 a/b = 3, a/c = 2, b/d = 1.5。
那么这三个都可以以a 为root, 对于b来说 b = a/3, c = a/2, d = b/(1.5) = 2a。
所以我们就用union find的方式给每一组cluster找到一个root。每一个节点到root的比值是从
这个节点到path所有节点的乘积，比如说前面给出的例子中 d = b/1.5, 然后 b = a/3， 所以总共是
(1/1.5)*(3)

*/

class Solution {
    Map<String, String[]> map;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        map = new HashMap<>();
        
        for(int i=0; i< values.length; i++){
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);

            //首先每个节点只能到达自己，和自己的比值是1
            if(!map.containsKey(a)) map.put(a, new String[]{"1.0",a});
            if(!map.containsKey(b)) map.put(b, new String[]{"1.0",b});
            helper(a, b, values[i]);//这里call helper只是为了将a,b连在一起
        }
        
        double[] res = new double[queries.size()];
        for(int i=0; i<queries.size(); i++){
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if(!map.containsKey(a) || !map.containsKey(b)){
                res[i] = -1.0; //假设
            }else{
            	//这里call helper是为了找到两个数字的比值,用-1纯粹是标记需要一个返回值的flag
                res[i] = helper(a, b, -1);
            }
        }
        
        return res;
    }
    
    
    public double helper(String a, String b, double x){
    	//需要两个double值一路从当前string取到root的比值
        double r1 = Double.valueOf(map.get(a)[0]), r2 = Double.valueOf(map.get(b)[0]);
        String copyA = a, copyB = b;
        while(!a.equals(map.get(a)[1])){
            a = map.get(a)[1];
            //这个r1只在最后找比值的时候需要而已
            r1 *=Double.valueOf(map.get(a)[0]);
        }
        
        while(!b.equals(map.get(b)[1])){
            b = map.get(b)[1];
            r2 *=Double.valueOf(map.get(b)[0]);
        }
        
        
        if(x == -1 && a.equals(b)) return r1/r2;
        else if(x == -1 && !a.equals(b)) return -1;
        double m = (r2/r1)*x;
        //将 a 和 b连接在一起，注意这里的a,b已经是原始a，b的root了
        map.put(a, new String[]{String.valueOf(m), b});
        return m; //这行无用,只是为了返回
    }
}

