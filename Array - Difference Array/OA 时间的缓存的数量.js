/* Leetcode 

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

const n = 3;
const data = [[5310825, 300],[5311081, 101],[5299871, 5239],[5310025, 1081]];
queries = [5311088, 5299890];

function cacheQueries(data, queries) {
    let res = [];
    data.sort((a, b) => a[0] - b[0]);

    let n = data.length;
    let q = queries.length;
    let start = data[0][0];
    let end = 0;

    // 找到总的截止时间点
    for(let i = 0; i < data.length; i++){
        end = Math.max(data[i][0] + data[i][1], end);
    }

    // 建立差分数组
    let diff = new Array(end + 1).fill(0);
    for(let d of data){
        let s = d[0];
        let e = d[0] + d[1];
        diff[s] += 1;
        diff[e] -= 1;
    }


    // 把差分数组还原成原数组，currCatch就表示每个时间点有多少个正在进行的缓存
    let currCatch = new Array(end + 1).fill(0);
    currCatch[start] = diff[start];
    for(let i = start + 1; i <= end; i++){
        currCatch[i] = currCatch[i - 1] + diff[i]; 
    }

    // 检查queries里面每个时间点，分别有多少个正进行的缓存
    for(let q of queries){
        res.push(currCatch[q]);
    }
  
    return res;
}


console.log(cacheQueries(data, queries));
