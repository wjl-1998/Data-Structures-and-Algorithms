package spareArray;

public class SparseArray3 {
	public static void main(String[] args) {
		int chessArrl[][] = new int[11][11];
		chessArrl[1][2] = 1;
		chessArrl[2][3] = 2;
		chessArrl[4][5] = 2;
		
		System.out.println("原始的二维数组~~~");
		for(int[] row : chessArrl) {
			for(int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
		
		//将二维数组 转 稀疏数组
		// 先遍历二维数组 得到非0 数据的个数
		int sum = 0;
		for(int i = 0;i<11;i++) {
			for(int j = 0;j<11; j++) {
				if(chessArrl[i][j] != 0) {
					sum++;
				}
			}
		}
		
		// 创建对应的稀疏数组
		int sparseArr[][] = new int[sum + 1][3];
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;
		
		//遍历二维数组，将非0 的值存放在 sparseArr 中
		int count = 0;
		for(int i = 0;i < 11; i++) {
			for(int j = 0;j < 11; j++) {
				if(chessArrl[i][j] != 0) {
					count++;
					sparseArr[count][0] = i;
					sparseArr[count][1] = j;
					sparseArr[count][2] = chessArrl[i][j];
				}
			}
		}
		
		//输出稀疏数组的形式
		System.out.println();
		System.out.println("得到稀疏数组为~~~~");
		for(int i = 0; i< sparseArr.length; i++) {
			System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0],
					sparseArr[i][1],sparseArr[i][2]);
		}
		System.out.println();
		
		int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
		
		for(int i = 1; i< sparseArr.length;i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		
		System.out.println();
		System.out.println("恢复后的二维数组");
		
		for(int[] row: chessArr2) {
			for(int data: row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
	}

}
