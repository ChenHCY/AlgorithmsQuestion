/* 1570. Dot Product of Two Sparse Vectors

Given two sparse vectors, compute their dot product.

Implement class SparseVector:
  
  · SparseVector(nums) Initializes the object with the vector nums
  · dotProduct(vec) Compute the dot product between the instance of SparseVector and vec

A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?

Example 1:
Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8

Example 2:
Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0

Example 3:
Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6
 

Constraints:
n == nums1.length == nums2.length
1 <= n <= 10^5
0 <= nums1[i], nums2[i] <= 100
*/

// v1 = SparseVector(nums1)；
// v2 = SparseVector(nums2)
// v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
//Solution 1: 直接计算，使用arr储存nums的值
//Time: O(n)   Space: O(n)
class SparseVector {
    int[] arr;
    
    SparseVector(int[] nums) {
        arr = nums; //把nums赋值到arr, 方便后面使用
    }
    
	// Return the dotProduct of two sparse vectors
    // v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
    public int dotProduct(SparseVector vec) {
        int sum = 0;

        // this前缀表示v1的arr属性, vec.arr表示传入参数v2的arr属性
        for(int i = 0; i < this.arr.length; i++){
            sum += this.arr[i] * vec.arr[i];
        }

        return sum;
    }
}

//Solution 2: Used HashMap 
//Time: O(N) Space: O(N) ==> 运算时间一定比solution 1小，因为跳过所有值为0的计算
class SparseVector {
    HashMap<Integer, Integer> map;
    
    SparseVector(int[] nums) {
        map = new HashMap<>();
        //使用hashmap跳过一些值为0的计算，节约运算时间
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                map.put(i, nums[i]); //把每个不为0的值 和 对应的index 存入 hashmap
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    // ==> v1.dotProduct(v2)
    public int dotProduct(SparseVector vec) {
        int sum = 0;

        // this.map 表示 v1 的hashmap属性
        for(Integer k : this.map.keySet()){
            //vec.map 表示 传入参数 vec（v2）的hashmap属性
            if(vec.map.containsKey(k)){
                sum += this.map.get(k) * vec.map.get(k);
            }
        }

        return sum;
    }
}


//Solution 2: Not Used HashMap


// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
