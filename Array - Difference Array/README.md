# The Difference Array 差分数组
1. 定义： 差分数组的主要适用场景是频繁对原始数组的某个区间的元素进行增减。

2. 差分数组(the diff array) 相当于 数组前缀和（Prefix Sum）的 逆运算

=》对于一个 差分数组 or 前缀和数组 进行前缀和 or 计算差分，就可以对于这个数组进行 "复原"

3. 对于构造一个 diff 差分数组，diff[i] 就是 nums[i] 和 nums[i-1] 之差：
```Java
//// 构造差分数组
int[] diff = new int[nums.length];
diff[0] = nums[0];
for (int i = 1; i < nums.length; i++) {
    diff[i] = nums[i] - nums[i - 1];
}
```
然后通过使用差分数组 diff，就可以快速进行区间增减的操作，

如果想对区间 nums[i..j] 的元素全部加 3，那么只需要让 diff[i] += 3，然后再让 diff[j+1] -= 3 即可
```
diff[i] += addNum 意味着给 nums[i, ..] 所有的元素都加了 addNum
diff[j + 1] -= addNum 意味着给 nums[j+1, ..] 所有的元素都减去了 addNum
结合起来， 就是对于 nums[i..j] 中的所有元素都加 addNum 了
```

4. 对于差分数组进行加值
```Java
//i 是起始点， j是结束点， val是给【i, j】区域所有数字 add的值
public void increment(int i, int j, int val) {
    diff[i] += val;
    if (j + 1 < diff.length) {
        diff[j + 1] -= val;
    }
}
```

5. 复原差分数组 回 之前的原始数组

==> 差分数组(the diff array) 相当于 数组前缀和（Prefix Sum）的 逆运算

==> 对于一个 差分数组 or 前缀和数组 进行前缀和 or 计算差分，就可以对于这个数组进行 "复原"
```Java
public int[] result() {
  int[] res = new int[diff.length];
  // 根据差分数组构造结果数组
  res[0] = diff[0];
  for (int i = 1; i < diff.length; i++) {
      res[i] = res[i - 1] + diff[i];
  }
  return res;
}
```
