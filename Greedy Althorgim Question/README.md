# 贪心算法逻辑： 如何通过局部最优 ==> 推出整体最优


# Integer.compare() in Java
==> Integer的内置比较方法，可以保证 当出现正负数比较，或者数字值很大时，这个比较的结果不会溢出 

**定义**: 在Java中，Integer.compare()是类中的静态方法Integer，用于比较两个整数值。它是在 Java 7 中引入的。该方法返回一个整数值，该值指示两个输入整数之间的关系。

```Java
public static int compare(int x, int y)

//x：要比较的第一个整数。  y：要比较的第二个整数。
// 如果x等于y，则返回0
// 如果x小于y，则返回负整数（例如-1）
// 如果x大于y，则返回正整数（例如1)
```
