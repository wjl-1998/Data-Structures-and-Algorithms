## 线性表

> 线性表（Linear List）是由n（n≥0）个数据元素（结点）a[0]，a[1]，a[2]…，a[n-1]组成的有限序列。

顺序存储结构和链式存储结构的优缺点比较，以及使用情况。
1 优缺点
① **顺序存储时**，相邻数据元素的存放地址也相邻（逻辑与物理统一）；要求内存中可用存储单元的地址必须是连续的。

- 优点：存储密度大（＝1），易于查找和修改。

- 缺点：插入或删除元素时不方便；存储空间利用率低，预先分配内存可能造成存储空间浪费。


②**链式存储时**，相邻数据元素可随意存放，但所占存储空间分两部分，一部分存放结点值，另一部分存放表示结点间关系的指针

- 优点：插入或删除元素时很方便，使用灵活，存储空间利用率高。
- 缺点：存储密度小（<1），查找和修改需要遍历整个链表。

2 使用情况

1. 
   顺序表适宜于做查找这样的静态操作；链表宜于做插入、删除这样的动态操作。

2. 若线性表的长度变化不大，且其主要操作是查找，则采用顺序表；
3. 若线性表的长度变化较大，且其主要操作是插入、删除操作，则采用链表。

---

代码实现：

### 顺序表：

```java
class Oppertation{
	Scanner sc = new Scanner(System.in);
	
	//创建表
	public void CreateList(Sqlist L) {
		int tempNo = 1, tempData = 0;
		do {
			System.out.printf("请输入第%d 个元素（输入为 -1 结束创建表）", tempNo);
			tempData = sc.nextInt();
			if(tempData != -1) {
				L.data[tempNo -1] = tempData;
				L.last = tempNo -1;
				tempNo++;
			}
		}while(tempNo <= 1024 && tempData != -1);
	}
	
	//打印表
	public void PrintList(Sqlist L) {
		int i;
		for(i = 0; i <=L.last; i++) {
			System.out.printf("%d", L.data[i]);
		}
		System.out.printf("\n");
	}
	
	//清空表
	public void ClearList(Sqlist L) {
		L.last = -1;
	}
	
	//获取表中某一位置数据
	public int GetList(Sqlist L, int no) {
		int tempData = L.data[no - 1];
		return tempData;
	}
	
	//表的长度
	public int LengthList(Sqlist L) {
		int tempL = L.last + 1;
		return tempL;
	}
	
	//插入元素
	public int InsertList(Sqlist L, int data , int no) {
		int tempNo = L.last;
		if(no <0 || no >= 1024) {
			System.out.println("插入的数据超过表的范围");
			return 0;
		}else if(L.last >= 1024) {
			System.out.println("空间不足！");
			return 0;
		}
		while(tempNo +1 != no -1) {
			L.data[tempNo + 1] = L.data[tempNo];
			tempNo--;
		}
		L.data[no-1] = data;
		L.last++;
		return 0;
	}
	
	//删除元素
	public int DeleteList(Sqlist L, int no) {
		if(no <= 0 || no > L.last +1) {
			System.out.println("你指定的删除位置超出了界限！");
			return 0;
		}else {
			for(int i = no-1; i < L.last; i++) 
				L.data[i] = L.data[i + 1];
			L.last--;
			return 0;
		}
	}
	
	//定位元素
	public int LocateList(Sqlist L, int data) {
		int i = 0;
		for(i = 0; i <= L.last; i++) {
			if(L.data[i] == data)
				return i+1;
		}
		return -1;
	}
}

class Sqlist{
	protected int[] data;
	protected int last;
	
	public Sqlist() {
		super();
	}
	public Sqlist(int i) {
		super();
		this.data = new int[i];
	}
}
```



### 单链表

```java

class ListOpertation{
	//初始化头节点，头节点不要动，不存放具体数据
	private ListNode head = new ListNode(0,"","");
	
	//添加节点到单向链表
	//思路，当不考虑编号顺序时
	//1. 找到当前链表的最后节点
	//2. 将最后这个节点的next 指向 新的节点
	public void add(ListNode listNode) {
		//因为 head 节点不能动，因此我们需要一个辅助遍历 temp
		ListNode temp = head;
		//遍历链表，找到最后
		while(true) {
			//找到链表最后
			if(temp.next == null) {
				break;
			}
			//如果没有找到最后，将 temp 后移
			temp = temp.next;
		}
		//当退出while 循环时，temp 就指向链表的最后
		// 将最后一个节点的 next 指向 新的节点
		
		temp.next = listNode;
	}
	
	//第二种方式在添加数据时，根据排名插入指定位置
	//如果有这个排名，则添加失败，并给出提示
	public void addByOrder(ListNode listNode) {
		//因为单链表，因为我们找的temp 是位于添加卫视在前一个系欸按，否则插入不了。
		ListNode temp = head;
		boolean flag = false;  //flag 标志添加的编号是否存在，默认为false
		while(true) {
			if(temp.next == null) { //说明 temp 已经在链表最后
				break;
			}
			if(temp.next.no > listNode.no) {  //位置找到，就在 temp 的后面插入
				break;
			}else if(temp.next.no == listNode.no) {
				flag = true;   //说明编号存在
				break;
			}
			temp = temp.next;  //后移，遍历当前链表
		}
		//判断 flag 的值
		if(flag) {  //不能添加，说明编号存在
			System.out.printf("准备插入数据的编号 %d 已经存在了，不能加入\n", listNode.no);
		}else {
			//插入到链表中，temp 的后面
			listNode.next = temp.next;
			temp.next = listNode;
		}
	}
	
	//修改节点的信息，根据 no 编号来修改， 即no 编号不能改
	//根据newlistNode 的 no 来修改即可
	public void update(ListNode newlistNode) {
		//判断是否空
		if(head.next ==  null) {
			System.out.println("链表为空~~");
			return;
		}
		
		//找到需要修改的节点，根据 no 编号
		//定义一个辅助变量
		ListNode temp = head.next;
		boolean flag = false;  //表示是否找到该节点
		while(true) {
			if(temp == null) {
				break;  //已经遍历完链表
			}
			if(temp.no == newlistNode.no) {
				//找到
				flag = true;
				break;
			}
			temp = temp.next;
		}
		//根据flag 判断是否找到要修改的节点
		if(flag) {
			temp.name = newlistNode.name;
			temp.address = newlistNode.address;
		}else {  //没有找到
			System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newlistNode.no);
		}
	}
	
	//删除节点
	//思路
	//1. head 不能动， 因此我们需要一个 temp 辅助节点找到待删除结点 的前一个结点
	//2. 说明我们在比较时，是temp.next.no 和  需要删除的节点的 no 比较
	public void del(int no) {
		ListNode temp = head;
		boolean flag = false;  //标志是否找到待删除节点的
		while(true) {
			if(temp.next == null) { //已经到链表的最后
				break;
			}
			if(temp.next.no == no) {
				//找到的待删除结点前一个节点 temp
				flag = true;
				break;
			}
			temp = temp.next; //temp 后移 ， 遍历
		}
		//判断 flag
		if(flag) { //找到
			//可以删除
			temp.next = temp.next.next;
		}else {
			System.out.printf("要删除的 %d 节点不存在 \n", no);
		}
	}
	
	//显示链表【遍历】
	public void list() {
		//判断链表是否为空
		if(head.next == null) {
			System.out.println("链表为空");
			return;
		}
		//因为头结点，不能动，因此我们需要一个辅助变量来遍历
		ListNode temp = head.next;
		while(true) {
			//判断是否到链表最后
			if(temp == null) {
				break;
			}
			//输出节点的信息
			System.out.println(temp);
			//将 temp 后移，一定小心
			temp = temp.next;
		}
	}
	
}
class ListNode{
	public int no;
	public String name;
	public String address;
	
	public ListNode next;  //指向下一个结点
	public ListNode(int no, String name, String address) {
		this.no = no;
		this.name = name;
		this.address = address;
	}
	//为了显示方法，我们重新toString
	@Override
	public String toString() {
		return "Node [no=" + no + ", name=" + name + ", address=" + address + "]";
	}
}
```



### 双链表

```java
//创建一个双链表类
class DoubleLinkedList{
	//先初始化一个头结点，头结点不要动，不存放具体数据
	private HeroNode2 head = new HeroNode2(0," "," ");
	
	//返回头节点
	public HeroNode2 getHead() {
		return head;
	}
	
	//遍历双向链表的方法
	//显示链表【遍历】
	public void list() {
		//判断链表是否为空
		if(head.next == null) {
			System.out.println("链表为空");
			return;
		}
		
		//因为头结点，不能动，因此我们需要一个辅助变量来遍历
		HeroNode2 temp = head.next;
		while(true) {
			//判断是否到链表最后
			if(temp == null) {
				break;
			}
			
			//输出节点的信息
			System.out.println(temp);
			//将temp 后移， 一定小心
			temp = temp.next;
		}
	}
	
	//添加一个节点到双向链表的最后
	public void add(HeroNode2 heroNode) {
		
		//因为head 节点不能动，因此我们需要一个辅助遍历 temp
		HeroNode2 temp = head;
		//遍历链表，找到最后
		while(true) {
			//找到链表的最后
			if(temp.next == null) {
				break;
			}
			//如果没有找到最后，将temp 后移
			temp = temp.next;
		}
		
		//当退出while循环时，temp就指向了链表的最后
		//形成一个双向链表
		temp.next = heroNode;
		heroNode.pre = temp;
	}
	
	//修改一个节点的内容，可以看到双向链表的节点内容修改和点链表一样
	//只是节点类型改成了 HeroNode2
	public void update(HeroNode2 newHeroNode) {
		if(head.next == null) {
			System.out.println("链表为空 ~~~");
			return;
		}
		
		HeroNode2 temp = head.next;
		boolean flag = false;
		
		while(true) {
			if(temp == null) {
				break;
			}
			if(temp.no == newHeroNode.no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		
		if(flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.name;
		}else {
			System.out.printf("没有找到编号 %d 的节点 不能修改 \n", newHeroNode.no);
		}
	}
	
	//从双向链表中删除一个节点
	//对于双向链表，我们可以直接找到要删除的这个节点
	//找到后自我删除即可
	public void del(int no) {
		if(head.next == null) {
			System.out.println("链表为空，无法删除");
			return;
		}
		
		HeroNode2 temp = head.next;  //辅助变量
		boolean flag = false;        //标志是否找到待删除结点的
		
		while(true) {
			if(temp == null) {   //已经找到链表的最后
				break;
			}
			if(temp.no == no) {
				//找到的待删除节点的前一个结点temp
				flag = true;
				break;
			}
			
			temp = temp.next;  //temp 后移，遍历
		}
		//判断 flag
		if(flag) {  //找到
			//可以删除
			temp.pre.next = temp.next;
			//如果时最后一个结点，就不需要执行下面这句话，否则出现空指针
			if(temp.next != null) {
				temp.next.pre = temp.pre;
			}
		}else {
			System.out.printf("要删除的 %d 节点不存在 \n", no);
		}
	}
}
```



例子：

Josephus环 问题

```java
public class Josepfu {
	public static void main(String[] args) {
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.addBoy(125);
		circleSingleLinkedList.showBoy();
		
		circleSingleLinkedList.countBoy(10, 20, 125);
	}

}

class Boy{
	private int no;
	private Boy next;
	
	public Boy(int no) {
		this.no = no;
	}
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public Boy getNext() {
		return next;
	}
	
	public void setNext(Boy next) {
		this.next = next;
	}
}

class CircleSingleLinkedList{
	private Boy first = null;
	
	public void addBoy(int nums) {
		if(nums < 1) {
			System.out.println("nums 的值不正确");
			return;
		}
		Boy curBoy = null;
		
		for(int i = 1;i <= nums; i++) {
			Boy boy = new Boy(i);
			
			if(i == 1) {
				first = boy;
				first.setNext(first);
				curBoy = first;
			}else {
				curBoy.setNext(boy);
				boy.setNext(first);
				curBoy = boy;
			}
		}
	}
	
	public void showBoy() {
		if(first == null) {
			System.out.println("没有任何小孩~~~");
			return;
		}
		
		Boy curBoy = first;
		while(true) {
			System.out.printf("小孩的编号 %d \n", curBoy.getNo());
			if(curBoy.getNext() == first) {
				break;
			}
			curBoy = curBoy.getNext();
		}
	}
	
	public void countBoy(int startNo, int countNum, int nums) {
		if(first == null || startNo < 1 || startNo > nums) {
			System.out.println("参数输入有误，请重新输入");
			return;
		}
		Boy helper = first;
		
		while(true) {
			if(helper.getNext() == first) {
				break;
			}
			helper = helper.getNext();
		}
		
		for(int j = 0; j < startNo - 1; j++) {
			first = first.getNext();
			helper = helper.getNext();
		}
		
		while(true) {
			if(helper == first) {
				break;
			}
			
			for(int j = 0; j < countNum -1; j++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			
			System.out.printf("小孩 %d 出圈\n", first.getNo());
			
			first =  first.getNext();
			helper.setNext(first);
		}
		
		System.out.printf("最后留在圈里的小孩编号 %d \n ", first.getNo());
	}
}
```

多项式加法与乘法：

```java
public class Polynomial {
	public static void main(String[] args) {
		//多项式p1
        PolyList p1=new PolyList();
        p1.insert(new PolyNode(2,2));
        p1.insert(new PolyNode(100,3));
        p1.insert(new PolyNode(45,5));
        p1.insert(new PolyNode(3,20));
        System.out.println("p1="+p1.printS());
       
      //多项式p2
        PolyList p2=new PolyList();
        p2.insert(new PolyNode(8,2));
        p2.insert(new PolyNode(7,3));
        p2.insert(new PolyNode(4,4));
        p2.insert(new PolyNode(6,18));
        p2.insert(new PolyNode(7,20));
        System.out.println("p2="+p2.printS());
        
       //相加
       PolyList resultList1= PolyList.add(p1, p2);
       System.out.println("p1+p2="+resultList1.printS());
       
       System.out.println();
       
     //多项式p3
       PolyList p3=new PolyList();
       p3.insert(new PolyNode(2,1));
       p3.insert(new PolyNode(3,2));
       p3.insert(new PolyNode(4,3));
       System.out.println("p3="+p3.printS());
       
       
     //多项式p4
       PolyList p4=new PolyList();
       p4.insert(new PolyNode(5,1));
       p4.insert(new PolyNode(1,2));
       System.out.println("p4="+p4.printS());
       
       //相乘
       PolyList resuList2=PolyList.multiply(p3, p4);
       System.out.println("p3*p4="+resuList2.printS());
	}

}

class PolyList{
	PolyNode head;
	PolyNode current;
	
	public PolyList() {
		head = new PolyNode();
		current = head;
		head.next = null;
	}
	
	public boolean isEmpty() {
		return head.next == null;
	}
	
	public void insert(PolyNode node) {
		current.next = node;
		current = node;
	}
	
	public String printS() {
		StringBuilder s=new StringBuilder("");
		StringBuilder a=new StringBuilder("");
		StringBuilder i=new StringBuilder("");
		StringBuilder theOne=new StringBuilder("");
		
		current = head.next;
		while(current != null) {
			a.delete(0, a.length());
			i.delete(0, i.length());
			theOne.delete(0, theOne.length());
			
			if(current.getA() == 1)
				a.append("");
			else
				a.append(String.valueOf(current.getA()));
			
			if(current.getI() == 1) {
				i.append("");
				theOne.append(a.toString()).append("x").append(i.toString());
			}else {
				i.append(String.valueOf(current.getI()));
				theOne.append(a.toString()).append("x^").append(i.toString());
			}
			
			if(current == head.next)
				s.append(theOne.toString());
			else
				s.append("+").append(theOne.toString());
			current = current.next;
		}
		return s.toString();
	}
	
	//加法运算
	public static PolyList add(PolyList p1, PolyList p2) {
		PolyList result = new PolyList();
		p1.current = p1.head.next;
		p2.current = p2.head.next;
		while(p1.current != null && p2.current != null) {
			if(p1.current.getI() == p2.current.getI()) {
				result.insert(new PolyNode(p1.current.getA()+ p2.current.getA(),p1.current.getI()));
				p1.current = p1.current.next;
				p2.current = p2.current.next;
			}
			
			else if(p1.current.getI() < p2.current.getI()) {
				result.insert(p1.current);
				p1.current = p1.current.next;
			}else {
				result.insert(p2.current);
				p2.current = p2.current.next;
			}
		}
		
		while(p1.current != null) {
			result.insert(p1.current);
			p1.current = p2.current.next;
		}
		while(p2.current != null) {
			result.insert(p2.current);
			p2.current = p2.current.next;
		}
		
		return result;
	}
	
	//乘法算法
	public static PolyList multiply(PolyList p1, PolyList p2) {
		
		PolyList result = new PolyList();
		p1.current = p1.head.next;
		p2.current = p2.head.next;
		while(p1.current != null) {
			while(p2.current != null) {
				int a = p1.current.getA()*p2.current.getA();
				int i = p1.current.getI() + p2.current.getI();
				
				result.insert(new PolyNode(a, i));
				p2.current = p2.current.next;
			}
			
			p1.current = p1.current.next;
			p2.current = p2.head.next;
		}
		
		//合并同类项
		result.current = result.head.next;
		PolyNode tempPrevious = result.current;
		PolyNode temp = result.current.next;
		while(result.current.next != null) {
			while(temp != null) {
				if(temp.getI() != result.current.getI()) {
					temp = temp.next;
					tempPrevious = tempPrevious.next;
				}else {
					result.current.setA(result.current.getA()+temp.getA());
					tempPrevious.next=temp.next;
					temp=temp.next;
				}
			}
			result.current = result.current.next;
			tempPrevious = result.current;
			temp = result.current.next;
		}
		return result;
	}
	
}

class PolyNode{
	private int a;
	private int i;
	PolyNode next;
	
	public PolyNode(int a, int i) {
		this.a = a;
		this.i = i;
		this.next = null;
	}
	
	public PolyNode() {
		this(0,0);
	}
	
	public int getA() {
		return a;
	}
	
	public int getI() {
		return i;
	}
	
	public void setA(int a) {
		this.a = a;
	}
	
	public void setI(int i) {
		this.i = i;
	}
	
}
```



