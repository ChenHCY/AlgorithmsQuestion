/* 3. implement Array.prototype.flat()

There is already Array.prototype.flat() in JavaScript (ES2019), which reduces the nesting of Array.

Could you manage to implement your own one?

Here is an example to illustrate

const arr = [1, [2], [3, [4]]];

flat(arr)
// [1, 2, 3, [4]]

flat(arr, 1)
// [1, 2, 3, [4]]

flat(arr, 2)
// [1, 2, 3, 4]
follow up

Are you able to solve it both recursively and iteratively?

*/

// This is a JavaScript coding problem from BFE.dev 
/**
 * @param { Array } arr
 * @param { number } depth
 * @returns { Array }
 */
//使用迭代的方法，调用自身
function flat(arr, depth = 1) {
  // your imeplementation here
  let res = [];
  //遍历arr中的所有数字，然后展开
  arr.forEach((item) => {
    //如果遍历到array, 并且是需要展开的子数组层级
    if(Array.isArray(item) && depth > 0){
      //concat() 用于组合两个或多个数组或字符串以创建新的数组或字符串。
      //该方法不会修改原始数组或字符串；相反，它返回一个新的串联数组或字符串。
      res = res.concat(flat(item, depth - 1));
    } else{ //如果是数字，直接加入
      res.push(item);
    }
  });
  return res;
}
