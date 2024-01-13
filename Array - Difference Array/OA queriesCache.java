/*  check the time index of queries[], and count every time index have how many cache is still running

Given a 2-d array of integers of size n x 2 data, data[i] represents that the ith data point was inserted into the cache at time data[i][0]. It lives in the cache for data[i][1] time. For an array of q queries, queries[q], find the number of data items in the cache at each time query[i].

Example
Suppose n = 3, data = [[105231, 183], [105334, 34], [105198, 543]], q = 2, and queries = [105338, 105410].

Data  |     Index      |     Start Time |    End Time |   In Cache at 105338   |  In Cache at 105410
1     |     105231     |     105231 + 183 = 105414    |     Yes                |     Yes
2     |     105334     |     105334 + 34 = 105368     |      Yes               |     No
3     |     105198     |     105198 + 543 = 105741    |     Yes                |      Yes
All 3 data points are in the cache at 105338, and 2 remain at 105410. Return [3, 2].

Returns
    int arr[q]: the answers to the queries

Constraints:
1 ≤ n ≤ 2 * 10^5
1 ≤ q ≤ 10^5
1 ≤ data[i][0], data[i][1] ≤ 10^9
1 ≤ queries[i] ≤ 10^9

*/

//Time: O(4n)  Space: O(2*n)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static List<Integer> cacheQueries(int[][] data, int[] queries) {
        List<Integer> res = new ArrayList<>();
        int n = data.length;
        int q = queries.length;
        
        //首先我们找到时间线的开始位置
        Arrays.sort(data, (a, b) -> Integer.compare(a[0], b[0])); //按照每个cache的开始时间的升序排序
        int start = data[0][0]; //时间线的开始时间
        
        //我们找到时间线的结束位置
        Arrays.sort(data, (a, b) -> Integer.compare(a[0] + a[1], b[0] + b[1])); //按照每个缓存cache的开始时间的升序排序
        int end = data[n - 1][0] + data[n - 1][1]; //时间线的结束时间
        
        int timeLen = end - start; //需要检查时间的范围
        
        //使用差分数组统计每一个时间点位置正在进行的缓存数量
        int[] diff = new int[timeLen + 1];
        for(int[] d : data){ //主遍历：遍历每一个缓存的开始和结束时间，进行统计
            int s = d[0];
            int e = d[0] + d[1];
            
            diff[s - start] += 1; //有一个缓存开始
            diff[e - start] -= 1; //有一个缓存结束
        }
        
        //然后通过前缀和把差分还原成正常数组
        //可以得到每一个位置的正常进行缓存cache的数量
        int[] cache = new int[timeLen + 1];
        cache[0] = diff[0];
        for(int i = 1; i < diff.length; i++){
            cache[i] = cache[i - 1] + diff[i];
        }
        
        //然后检查queries里面每一个时间点，得到正在进行缓存的数量
        for(int time : queries){
            res.add(cache[time - start]);
        }
        
        return res; //res里面是queries中每个element时间点正在进行缓存的数量
    }
    
    //data[]: 第一个是开始时间点，第二个是进行的时间大小
    //queries[]: 检查缓存cache数量的时间点
    public static void main(String[] args) {
        int[][] data1 = {{5310825, 300}, {5311081, 101}, {5299871, 5239}, {5310025, 1081}};
        int[] queries1 = {5311088, 5299890};

        int[][] data2 = {{105231, 183}, {105334, 34}, {105198, 543}};
        int[] queries2 = {105338, 105410};

        System.out.println(cacheQueries(data1, queries1));
        System.out.println(cacheQueries(data2, queries2));
    }
}
