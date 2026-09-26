/* There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
A province is a group of directly or indirectly connected cities and no other cities outside of the group.
You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
Return the total number of provinces.

Example 1:
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

Example 2:
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3

Constraints:
1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
*/

//  Union Find 解法
// 时间复杂度是排序的 O(M * N^2) ⇒ Union-Find 部分接近 O(M)。
/**
 * @param {number[][]} isConnected
 * @return {number}
 */
var findCircleNum = function(isConnected) {
    const parent = new Map();
    let n = isConnected.length; // how many cities
    let totalProvinces = n;

    for(let i = 1; i <= n; i++){
        parent.set(i, i);
    }

    // 查找这个city group的源头
    const find = (city) => {
        if(parent.get(city) !== city){
            parent.set(city, find(parent.get(city)));//递归找到这个group的源头city
        }
        return parent.get(city);
    }

    //union(cityA, cityB): 检查cityA 和 cityB 是否在同一个group
    const union = (cityA, cityB) => {
        const rootA = find(cityA);
        const rootB = find(cityB);

        if(rootA === rootB){
            return false; //本来就是同一个 group, 不需要再次链接
        }

        parent.set(rootA, rootB);//如果是不同的group, 则从root链接 merge
        totalProvinces--; //成功合并，表示totalProvinces 可以减去1
        return true;
    }

    // O(N^2)
    for(let i = 0; i < n; i++){
        for(let j = 0; j < n; j++){
            if(isConnected[i][j] === 1){
                union(i, j);
            }
        }
    }

    return totalProvinces;
};
