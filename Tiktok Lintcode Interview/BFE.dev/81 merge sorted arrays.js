/*BEV.dev 81. merge sorted arrays

You are given a list of sorted non-descending integer arrays, write a function to merge them into one sorted non-descending array.

merge(
  [
    [1,1,1,100,1000,10000],
    [1,2,2,2,200,200,1000],
    [1000000,10000001],
    [2,3,3]
  ]
)
// [1,1,1,1,2,2,2,2,3,3,100,200,200,1000,1000,10000,1000000,10000001]
What is time complexity of your solution?
*/

//Solution 1:  Used merge Sort method
//Time:  O(logN * M) N is arrList.length, M is merge sort.
/**
 * @param {number[][]} arrList
 * non-descending integer array
 * @return {number[]} 
 */
function merge(arrList) {
  // your code here
  if(arrList.length == 0){
    return [];
  }
  if(arrList.length == 1){
    return arrList[0];
  }

  if(arrList.length == 2){
    return mergeSort(arrList[0], arrList[1]);
  }
  //划分区域，直到把区域内数组数量缩小到2，然后进行归并排序
  let mid = Math.floor(arrList.length / 2);
  let left = merge(arrList.slice(0, mid)); //slice 左闭右开，取不到右边
  let right = merge(arrList.slice(mid, arrList.length));
  return mergeSort(left, right);
}

const mergeSort = (arr1, arr2) => {
  let i = 0;
  let j = 0;
  let k = 0;
  let res = []; 

  while(i < arr1.length && j < arr2.length){
    if(arr1[i] <= arr2[j]){
      res[k] = arr1[i];
      k++;
      i++;
    } else{
      res[k] = arr2[j];
      k++;
      j++;
    }
  }

  while(i < arr1.length){
    res[k] = arr1[i];
    k++;
    i++;
  }

  while(j < arr2.length){
    res[k] = arr2[j];
    k++;
    j++;
  }

  return res;
}

//Solution 2: Used JS array flat() method and sort method
//falt():  JavaScript 数组中的内置函数，允许您通过将子数组连接到父数组中来展平嵌套数组
//可以设置需要展开的层级，默认值1，只有直接子数组才会被展平。==》可以提供更大的值以depth将嵌套数组展平到更深的级别。
/**
 * @param {number[][]} arrList
 * non-descending integer array
 * @return {number[]} 
 */
function merge(arrList) {
  // your code here
  return arrList.flat().sort((a, b) => a - b);
}
