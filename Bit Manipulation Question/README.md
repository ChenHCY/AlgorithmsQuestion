# >> and << in JAVA

在Java中，`<<` 和 `>>` 运算符 分别用于按位左移和按位右移操作。它们通过向左或向右移动位来操纵整数的二进制表示形式。

1. `<<` 运算符执行按位左移操作。它需要两个操作数：要移位的number 和 向左移位的位数。
```Java
int num = 10;  // Binary representation: 0000 1010
int result = num << 2;  // Shifts 2 positions to the left
System.out.println(result);  // Output: 40 (Binary representation: 0010 1000)
```

2. `>>` 运算符执行按位右移操作。它还需要两个操作数：要移位的值和向右移位的位置数。
```Java
int num = 10;  // Binary representation: 0000 1010
int result = num >> 1;  // Shifts 1 position to the right
System.out.println(result);  // Output: 5 (Binary representation: 0000 0101)
```
