## 栈

栈的英文为（stack）

- 栈是一个先入后出的有序列表
- 栈（stack）是限制线性表中元素的插入和删除只能在线
- 栈是限制线性表中元素的插入和删除只能在线性表的通一端进行一种特殊线性表。允许插入和删除的一段，为变化的一端，成为栈顶（top）,另一端为固定一段，称为栈底（Bottom）.
- 根据栈的定义可知，最先放入栈中元素在栈底，最后放入的元素在栈顶，而删除元素刚好相反，最后放入的元素最先删除，最先放入的元素最后删除。

### 栈的应用场景

1. 子程序的调用： 在跳往子程序前，会先将下一个指令的地址存到堆栈中，知道子程序执行完后再将地址取出，以回到原来的程序中。
2. 处理递归调用：和子程序的调用类似，只是除了存储下一个指令的地址外，也将参数，区域变量等数据存入堆栈中。
3. 表达式的转换【中缀表达式转后缀表达式】与求职（实际解决）。
4. 二叉树的遍历。
5. 图形的深度优先搜索法。

---

代码实现。

栈的基本功能：

```java

//定义一个 ArrayStack 表示栈
class ArrayStack{
	private int maxSize;  //栈的大小
	private int[] stack;  // 数组，数组模拟栈，数据就方在该数组
	private int top = -1; // top表示栈顶，初始化为 -1
	
	// 构造器
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}
	// 栈满
	public boolean isFull() {
		return top == maxSize - 1;
	}
	// 栈空
	public boolean isEmpty() {
		return top == -1;
	}
	// 入栈 - push
	public void push(int value) {
		//先判断栈是否满
		if(isFull()) {
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}
	//出栈-pop, 将栈顶的数据返回
	public int pop() {
		//先判断栈是否空
		if(isEmpty()) {
			//抛出一场
			throw new RuntimeException("栈空，没有数据~~");
		}
		int value = stack[top];
		top--;
		return value;
	}
	//显示栈的情况【遍历栈】，遍历时，需要从栈顶开始显示数据
	public void list() {
		if(isEmpty()) {
			System.out.println("栈空，没有数据~~~");
			return;
		}
		//需要从栈顶开始显示数据
		for(int i = top; i>= 0; i--) {
			System.out.printf("Stack[%d]=%d\n", i,stack[i]);
			}
	}
	
}
```

（未完待续……）
