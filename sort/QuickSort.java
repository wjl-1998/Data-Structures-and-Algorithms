package sort2;

public class QuickSort {
	//快排排序
	public static void main(String[] args) {
		int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1,8};
		System.out.println("排序之前：");
	    for (int i = 0; i < a.length; i++) {
	    	System.out.print(a[i]+" ");
	    }
	    quick(a);
	    System.out.println();
	    System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
	    
	}
	
	private static void quick(int[] a) {
		if(a.length>0) {
			quickSort(a,0,a.length-1);
		}
	}
	
	private static void quickSort(int[] a, int low, int high) {
		if(low<high) {
			int middle = getMiddle(a,low,high);
			quickSort(a,0,middle-1);
			quickSort(a,middle+1,high);
		}
	}
	
	private static int getMiddle(int[] a, int low, int high) {
		int temp = a[low]; //基准元素
		while(low<high) {
			while(low<high && a[high] >= temp) {
				high--;
			}
			a[low] = a[high];
			while(low< high && a[low] <= temp) {
				low++;
			}
			a[high] = a[low];
		}
		a[low] = temp;
		return low;
	}

}
