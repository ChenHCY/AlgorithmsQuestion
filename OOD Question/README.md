# Object Oriented Design (OOD)

# The key concepts in OOD:

1. Object(对象): 对象是表示具有 属性properties and 方法 methods 的 实体类(class).

==> Every object has its own state(变量), and can interact（交互）with 其他的 object

2. Classes(类): Classes define the blueprint for creating objects.  

类定义了创建对象的蓝图。 它们封装了class 的 object 将拥有的 data 和 method。 class 允许代码重用并为设计软件系统提供模块化结构。

3. Inheritance (继承): Inheritance 允许 class 从其他 class 继承 属性properties and 方法 methods

4. Encapsulation (封装): Encapsulation refers to the bundling of data and methods within an object.

==> 封装是指将数据和方法捆绑在一个对象中。

5. Polymorphism (多态性)： Polymorphism allows objects of different classes to be treated as objects of a common superclass.

==> 多态性允许将不同类的对象视为公共超类的对象。

名为“Shapes”的超类有一个名为“area()”的方法。“形状”的子类可以是“三角形”、“圆形”、“矩形”等。每个子类都有其计算面积的方法。

==> 使用Encapsulation (封装) 和 Polymorphism (多态性) 意味着，子类可以使用“area()”方法来找到该形状的面积公式。

6. Abstraction (抽象):  Data abstraction is the process of hiding certain details and showing only essential information to the user.
Abstraction can be achieved with either abstract classes or interfaces.

==> Java 中的抽象是向用户隐藏实现细节并仅向用户显示功能的过程。它可以通过使用抽象类、方法和接口来实现。抽象类是一个不能自己实例化的类，只能由具体类继承。

  · Abstract class: is a restricted class that cannot be used to create objects (to access it, it must be inherited from another class).
   ==> 是一个受限制的类，不能用来创建对象（要访问它，必须从另一个类继承）
  
  · Abstract method: can only be used in an abstract class, and it does not have a body. The body is provided by the subclass (inherited from).
  ==> 只能在抽象类  abstract class 中使用，没有主体。正文由子类（inherited）提供。

7.  Association (关联)：关联是两个独立类之间的关系，它通过它们的对象建立。关联可以是一对一、一对多、多对一、多对多。

8. Composition (组合) and Aggregation (聚合): 在面向对象的编程中，一个对象与另一个对象通信以使用该对象提供的功能和服务。组合和聚合是关联的两种形式。
 
==> 聚合意味着孩子可以独立于父母而存在的关系。比如Bank和Employee，删掉Bank，Employee还存在。 ==> 聚合是弱关联

==> 而组合意味着孩子不能独立于父母而存在的关系。示例：人和心，心不存在于人之外 ==> 组合是强关联

![7e1d1468388e856a40aca0b9324fd24](https://github.com/ChenHCY/AlgorithmsQuestion/assets/60770401/052cd68a-3078-4603-abe1-27144d46a903)

# Each principle within the SOLID acronym
1. Single Responsibility Principle (SRP): 单一职责原则 (SRP)指出一个class 应该只有一个改变的理由。 

==> 这意味着一个class 应该有 single responsibility 或 purpose，而不应该负责多个不相关的 tasks。

2. Open-Closed Principle (OCP): 开闭原则 (OCP) 规定软件实体（class、modules、function）应该对 extension open，对 modification修改关闭。 

==> 这意味着可以在不修改其现有代码的情况下扩展软件组件的行为。

3. Liskov Substitution Principle (LSP): Liskov 替换原则 (LSP) 指出superclass的 object 应该可以用其子类的对象替换，而不影响程序的正确性。

==> 这意味着子类应该能够与其基类互换使用。 该原则确保继承层次结构的设计方式能够保留超类的行为契约。

4. Interface Segregation Principle (ISP): 接口隔离原则 (ISP)：ISP 声明不应强迫客户端依赖于它们不使用的接口。 

==> 这意味着不应强制类使用不需要的方法来实现接口。

5. Dependency Inversion Principle (DIP): 依赖倒置原则（DIP）指出高层modules不应该依赖低层modules； 两者都应该依赖于Abstraction抽象。 

==> 这意味着calss 应该依赖于 Abstraction抽象（interfaces or abstract classes), 而不是具体的实现(concrete implementations)。
