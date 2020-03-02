package sort2;
import java.util.Arrays;
public class HeapSort {
	//堆排序
	public static void main(String[] args) {
		int[]  a = {49,38,65,97,76,13,27,49,78,34,12,64};
		int arrayLength = a.length;
		//循环建堆
		for(int i=0; i< arrayLength-1; i++) {
			//键堆
			buildMaxHeap(a,arrayLength-1-i);
			swap(a,0,arrayLength-1-i);
			System.out.println("第  "+i+"  排序！");
			System.out.println(Arrays.toString(a));
		}
	}
	
	public static void buildMaxHeap(int[] data, int lastIndex) {
		for(int i=(lastIndex-1)/2; i>=0; i--) {
			int k=i;
			while(k*2+1 <= lastIndex) {
				int biggerIndex = 2*k+1;
				if(biggerIndex< lastIndex) {
				    if(data[biggerIndex]< data[biggerIndex+1]) {
				    	biggerIndex++;
				    }
				}
				
				if(data[k]<data[biggerIndex]) {
					swap(data,k, biggerIndex);
					k= biggerIndex;
				}else {
					break;
				}
				
			}
		}
		
	}
	
	private static void swap(int[] data,int i,int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

}
