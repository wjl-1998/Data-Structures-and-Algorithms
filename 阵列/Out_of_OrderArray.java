package spareArray;

public class Out_of_OrderArray {
	public static void main(String[] args) {
		int maxSize = 100;
		Array2 arr = new Array2(maxSize);
		arr.insert(16);
        arr.insert(32);
        arr.insert(9);
        arr.insert(5);
        arr.insert(21);
        arr.insert(46);
        arr.insert(101);
        arr.insert(27);
        arr.insert(27);
        arr.display();
        
        int searchKey = 35;
        
        if(arr.find(searchKey)){
            System.out.println("Found" + searchKey);
        }
        else{
            System.out.println("cant find" + searchKey);
        }
        
        arr.delete(101);
        arr.delete(27);
        arr.delete(0);
        
        arr.display();
	}

}


class Array2{
	private long[] a;
	private int nElems;
	
	public Array2(int max) {
		a = new long[max];
		nElems = 0;
	}
	
	public boolean find(long searchKey) {
		int j;
		for(j = 0; j < nElems; j++) {
			if(a[j] == searchKey) {
				break;
			}
		}
		if(j == nElems) {
			return false;
		}else {
			return true;
		}
	}
	
	public void insert(long value) {
		a[nElems] = value;
		nElems++;
	}
	
	public boolean delete(long value) {
		int j;
		for(j = 0; j < nElems; j++) {
			if(value == a[j]) {
				break;
			}
		}
		if(j == nElems) {
			return false;
		}else {
			for(int k = j; k < nElems; k++) {
				a[k] = a[k + 1];
			}
			nElems --;
			return true;
		}
	}
	
	public void display() {
		for(int j = 0; j < nElems; j++) {
			System.out.println(a[j] + " ");
		}
		System.out.println();
	}
}

