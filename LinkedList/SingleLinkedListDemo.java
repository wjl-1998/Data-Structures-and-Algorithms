package linkedList;

public class SingleLinkedListDemo {
	//测试类
	public static void main(String[] args) {
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
		
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		
		singleLinkedList.add(hero1);
		singleLinkedList.add(hero4);
		singleLinkedList.add(hero3);
		singleLinkedList.add(hero2);
		
		System.out.println("原来链表的情况~~~");
		singleLinkedList.list();
		
		
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero3);
		singleLinkedList.addByOrder(hero2);
		System.out.println("第二次显示");
		singleLinkedList.list();
	}
	

}

class HeroNode{
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;
	
	public HeroNode(int no,String name, String nickname) {
		this.no = no;
		this.name =  name;
		this.nickname = nickname;
	}
	
	public String toString() {
		return "HeroNode [ no = "+ no +", name" + name + ", nickname = "+nickname+"] ";
	}
}

//定义SingleLinkedList 管理数据
class SingleLinkedList{
	//初始化一个头结点，不存放具体数据
	private HeroNode head = new HeroNode(0," "," ");
	//返回头节点
	public HeroNode getHead() {
		return head;
	}
	
	//添加节点到单项链表
	//思路： 当不考虑编号顺序时
	// 1. 找到当前链表的最后节点
	// 2. 将最后一个节点 next 指向 新的节点
	
	public void add(HeroNode heroNode) {
		//因为head 节点不能动，因此我们需要一个辅助遍历 temp
		HeroNode temp = head;
		//遍历链表，找到最后
		while(true) {
			//找到链表的最后
			if(temp.next == null) {
				break;
			}
			
			//如果没有找到最后，将temp 后移
			temp = temp.next;
		}
		
		//当退出while循环时，temp 就指向了链表的最后
		//将最后这个系欸但的next 指向 新的结点
		
		temp = temp.next;
	}
	
	//第二种方式在添加数据时，根据排名的将英雄插入到指定位置
	//（如果有这个排名，则添加失败，并给出提示）
	public void addByOrder(HeroNode heroNode) {
		
		//因为头结点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
		//因为单链表中，我们找到的temp 是位于添加位置的前一个结点，否则插入不了
		HeroNode temp = head;
		boolean flag = false;
		//flag 标志添加的编号是否存在，默认为false
		while(true) {
			if(temp.next == null) {  //说明 temp 已经在链表的最后
				break;
			}
			if(temp.next.no > heroNode.no) {
				//位置找到，就在 temp 的后面插入
				break;
			}else if(temp.next.no == heroNode.no) {
				//说明希望添加的 heroNode 的编号依然存在
				flag = true;  //说明编号存在
				break;
			}
			temp = temp.next;  //后移， 遍历当前列表
		}
		
		// 判断 flag 的值
		if(flag) {  // 不能添加，说明编号存在
			System.out.printf("英雄编号已经存在，不能加入", heroNode.no);
		}else {
			
			//插入到链表中， temp 的后面
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
		
	}
	
	
	//修改节点信息，根据no 编号来修改，即no 编号不能改
	public void update(HeroNode newHeroNode) {
		//判断是否为空
		if(head.next == null) {
			System.out.println("链表为空 ~~~");
			return ;
		}
		
		//找到需要修改的节点， 根据no 编号
		// 定义一个辅助变量
		HeroNode temp = head.next;
		boolean flag = false;  //表示是否找到该节点
		while(true) {
			if(temp == null) {
				break;  // 已经遍历完链表
			}
			if(temp.no == newHeroNode.no) {
				//找到
				flag = true;
				break;
			}
			temp = temp.next;
		}
		//根据flag 判断是否找到要修改的节点
		if(flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		}else { // 没有找到
			System.out.printf("没有找到编号 %d 的节点", newHeroNode.no);
		}
	}
	
	//删除节点
	//思路
	// 1， head 节点不能动， 因此我们需要一个temp 辅助节点找到待删除结点的前一个节点
	// 2，在比较时，时temp.next.no 和 需要删除的 no 节点比较
	public void del(int no) {
		HeroNode temp = head;
		boolean flag = false;  //标志是否找到待删除节点的
		while(true) {
			if(temp.next == null) {  // 已经找到链表的最后
				break;
			}
			if(temp.next.no == no) {
				//找到的待删除节点的前一个节点 temp
				flag = true;
				break;
			}
			temp = temp.next;  // temp 后移，遍历
		}
		
		// 判断 flag
		if(flag) { //找到
			//可以删除
			temp.next = temp.next.next;
		}else {
			System.out.printf("要删除的%d 节点不存在", no);
		}
	}
	
	//显示链表【遍历】
	public void list() {
		//判断链表是否为空
		if(head.next == null) {
			System.out.println("链表为空");
			return;
		}
		
		//因为头节点，不能动，因此我们需要一个辅助变量来遍历
		HeroNode temp = head.next;
		while(true) {
			//判断是否到链表最后
			if(temp == null) {
				break;
			}
			
			//输出节点的信息
			System.out.println(temp);
			//将temp 后移， 一定小心，这里很容易出错
			temp = temp.next;
		}
	}
}