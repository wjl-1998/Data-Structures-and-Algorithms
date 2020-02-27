package spareArray;

public class OrderArray {
	public static void main(String[] args) {
		int maxSize = 100;
		Array arr = new Array(maxSize);
		arr.insert(45);
        arr.insert(13);
        arr.insert(75);
        arr.insert(123);
        arr.insert(1);
        arr.insert(26);
        arr.display();
        
        int searchKey = 75;
        if(arr.find(searchKey) != arr.size()) {
        	System.out.println("found " + searchKey);
        }else {
        	System.out.println("can not found " + searchKey);
        }
        
        arr.delete(26);
        arr.display();
	}

}

class Array{
	private long[] a;   //定义 一个有序数组
	private int nElems;  // 定义数组长度
	public Array(int max) {
		// 构造函数初始化
		a = new long[max];
		nElems = 0;
	}
	
	// size 函数
	public int size() {
		return nElems;
	}
	
	//定义添加函数
	public void insert(long value) {
		// 将value 赋值给数组成员
		a[nElems] = value;
		//然后将数组长度加一
		nElems ++;
		//排序，找到合适的位置
		long temp;
		for(int i = 0; i< nElems -1 ; i++) {
			for(int j = 0; j< nElems -1 ;j++) {
				if(a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j+1];
					a[j + 1] = temp;
				}
			}
		}
	}
	
	//定义查找方法
	public int find(long searchKey) {
		
		int lowerBound = 0;
		int upperBound = nElems -1;
		int curIndex;
		while(true) {
			curIndex = (lowerBound + upperBound) /2;
			if(a[curIndex] == searchKey) {
				return curIndex;
			}
			else if(lowerBound > upperBound) {
				return nElems;
			}
			else {
				if(a[curIndex] > searchKey) {
					upperBound = curIndex -1;
				}else {
					lowerBound = curIndex + 1;
				}
				
			}
		}
	}
	
	// 定义删除方法
	public boolean delete(long value) {
		int index = find(value);
		if(index == size()) {
			return false;
		}else {
			for(int i = index; i < size(); i++) {
				a[i] = a[i + 1];
			}
			nElems --;
			return false;
		}
		
	}
	
	//定义显示方法
	public void display() {
		for(int j = 0; j < nElems; j++) {
			System.out.println(a[j] + " ");
		}
		System.out.println();
	}
}