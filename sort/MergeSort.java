package sort2;

public class MergeSort {
	//归并排序
	public static void main(String[] args) {
		int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1,8};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        mergeSort(a,0,a.length-1);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
	}
	
	
	private static void mergeSort(int[] a, int left, int right) {
		if(left< right) {
			int middle = (left+right)/2;
			mergeSort(a,left,middle);
			mergeSort(a,middle+1,right);
			merge(a,left,middle,right);
		}
	}
	
	private static void merge(int[] a, int left, int middle,int right) {
		int[] tmpArr = new int[a.length];
		int mid = middle+1;
		int tmp = left;
		int third = left;
		while(left <= middle && mid<= right) {
			if(a[left]<= a[mid]) {
				tmpArr[third++] = a[left++];
			}else {
				tmpArr[third++] = a[mid++];
			}
		}
		
		while(left<= middle) {
			tmpArr[third++] = a[left++];
		}
		while(mid <= right) {
			tmpArr[third++] = a[mid++];
		}
		
		while(tmp <= right) {
			a[tmp] = tmpArr[tmp++];
		}
	}

}
