package linkedList;

import java.util.Scanner;
public class SeqList {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Sqlist L = new Sqlist(1024);
		Oppertation List = new Oppertation();
		List.CreateList(L);
		List.PrintList(L);
		
		
		//测试插入元素
		int data,no;
		System.out.println("请输入你要插入的数据(数据和插入的位置)：");
		data = sc.nextInt();
		no = sc.nextInt();
		List.InsertList(L, data, no);
		List.PrintList(L);
		
		//测试删除元素
		System.out.println("你要删除第几个元素:");
		no = sc.nextInt();
		List.DeleteList(L, no);
		List.PrintList(L);
		
		//定位元素
		System.out.println("输入你要定位的元素:");
		data = sc.nextInt();
		
		int locate = List.LocateList(L, data);
		if(locate == -1)
			System.out.println("你输入的元素不存在！");
		else
			System.out.printf("定位元素的位置为：%d",locate);
		sc.close();
	}

}

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