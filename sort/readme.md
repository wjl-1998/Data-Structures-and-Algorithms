## 排序的基本知识

* 定义：排序就是将原本无序的序列重新排列成有序的序列。
* 排序的稳定性
  * 如果待排序表中有两个元素Ri、Rj，其对应的关键字keyi=keyj，且在排序前Ri在Rj前面，如果使用某一排序算法排序后，Ri仍然在Rj的前面，则称这个排序算法是稳定的，否则称排序算法是不稳定的。

### 内排序的分类：

1. 插入排序：直接插入排序，二分法插入排序，希尔排序
2. 交换排序：冒泡排序，快速排序
3. 选择排序： 简单选择排序，堆排序
4. 归并排序
5. 基数排序

---

### 插入类排序

* 直接插入排序
  * 直接插入排序：首先以一个元素为有序的序列，然后将后面的元素依次插入到有序的序列中合适的位置直到所有元素都插入有序序列。
  * 时间复杂度为O(n)
  * 直接插入排序是稳定性是稳定的。
  
  ```java
          //直接插入排序
          for (int i = 1; i < a.length; i++) {
              //待插入元素
              int temp = a[i];
              int j;
              for (j = i-1; j>=0; j--) {
                  //将大于temp的往后移动一位
                  if(a[j]>temp){
                      a[j+1] = a[j];
                  }else{
                      break;
                  }
              }
              a[j+1] = temp;
          }
  ```
  
  
  
* 二分法插入排序
  * 二分法插入排序将比较和移动这两个操作分离出来，也就是先利用二分法查找找到插入的位置，然后一次性移动元素，再插入该元素。
  * 二分法插入排序的时间复杂度为O(n^2)
  * 稳定性：和直接插入排序稳定性相同，是稳定的。
  
  ```java
  for (int i = 0; i < a.length; i++) {
              int temp = a[i];
              int left = 0;
              int right = i-1;
              int mid = 0;
              while(left<=right){
                  mid = (left+right)/2;
                  if(temp<a[mid]){
                      right = mid-1;
                  }else{
                      left = mid+1;
                  }
              }
              for (int j = i-1; j >= left; j--) {
                  a[j+1] = a[j];
              }
              if(left != i){
                  a[left] = temp;
              }
          }
      }
  ```
  
* 希尔排序
  * 希尔排序的基本思想：希尔排序本质上还是插入排序，只不过是把待排序序列分成几个子序列，再分别对这几个子序列进行直接插入排序。
    * ①先以增量5来分割序列，也就是下标为0,5,10,15...的关键字分成一组，下标为1,6,11,16..分成一组,然后对这些组分别进行直接插入排序，这就完成了一轮希尔排序。
    * ②缩小增量(d1=n/2，di+1= [di/2]，比如10个数据序列，第一次增量d1=10/2=5,第二次增量d2= [d1/2]= [5/2]=2,并且最后一个增量等于1),所以第二轮以增量为2进行类似的排序过程。
    * ③接下来的第三轮，第四轮...都是类似的过程，直到最后一轮以增量为1。此时就是前面所说的直接插入排序。
    * 概要:  
  * 时间复杂度：...  希尔排序的时间复杂度约为O(n^1.3)    在最坏情况下希尔排序的时间复杂度为O(n^2)
  * 空间复杂度：希尔排序的空间复杂度为O(1)
  * 稳定性：不稳定，由于不同的增量可能就会把相等的关键字划分到两个直接插入排序中进行排序， 可能就会造成相对顺序变化。
  
  ```java
  //希尔排序
          int d = a.length;
          while(true){
              d = d / 2;
              for(int x=0;x<d;x++){
                  for(int i=x+d;i<a.length;i=i+d){
                      int temp = a[i];
                      int j;
                      for(j=i-d;j>=0&&a[j]>temp;j=j-d){
                          a[j+d] = a[j];
                      }
                      a[j+d] = temp;
                  }
              }
              if(d == 1){
                  break;
              }
          }
  ```

### 选择类排序

* 简单选择排序

  *  
  *  空间复杂度：需要额外的存储空间仅为交换元素时借助的中间变量，所以空间复杂度是O(1)
  *  时间复杂度：
     关键操作在于交换元素操作，整个算法由双重循环组成，外层循环从0到n-2一共n-2+1=n-1次，
     对于第i层外层循环，内层循环执行n-1-(i+1)+1=n-i-1次。
                     当i=0,内层循环执行n-1次，当i=n-2,内层循环执行1次，所以是一个等差数列求和,一共为(1+n-1)(n-1)/2=n(n-1)/2 ,所以时间复杂度为O(n^2)
  *  稳定性：不稳定   原因就在于交换部分会打破相对顺序

  ```java
  //简单的选择排序
          for (int i = 0; i < a.length; i++) {
              int min = a[i];
              int n=i; //最小数的索引
              for(int j=i+1;j<a.length;j++){
                  if(a[j]<min){  //找出最小的数
                      min = a[j];
                      n = j;
                  }
              }
              a[n] = a[i];
              a[i] = min;
              
          }
  ```

* 堆排序

  * 什么是堆？

    * 堆是一棵完全二叉树，而且满足任何一个非叶结点的值都不大于(或不小于)其左右孩子结点的值。
      * 如果是每个结点的值都不小于它的左右孩子结点的值，则称为大顶堆。
      * 如果是每个结点的值都不大于它的左右孩子结点的值，则称为小顶堆。

  * 什么是堆排序？

    * 我们知道对于一个堆来说，它的根结点是整个堆中所有结点的值的最大值(大顶堆)或者最小值(小顶堆)。所以堆排序的思想就是每次将无序序列调节成一个堆，然后从堆中选择堆顶元素的值，这个值加入有序序列，无序序列减少一个，再反复调节无序序列，直到所有关键字都加入到有序序列。
    * 时间复杂度：
      堆排序的总时间可以分为①建堆部分+②n-1次向下调整堆
    
    堆排序的时间复杂度为O(n)+O(nlog2n)=O(nlog2n)

     * 堆排序不稳定

  ```java
  //对data数组从0到lastIndex建大顶堆
      public static void buildMaxHeap(int[] data, int lastIndex){
           //从lastIndex处节点（最后一个节点）的父节点开始 
          for(int i=(lastIndex-1)/2;i>=0;i--){
              //k保存正在判断的节点 
              int k=i;
              //如果当前k节点的子节点存在  
              while(k*2+1<=lastIndex){
                  //k节点的左子节点的索引 
                  int biggerIndex=2*k+1;
                  //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                  if(biggerIndex<lastIndex){  
                      //若果右子节点的值较大  
                      if(data[biggerIndex]<data[biggerIndex+1]){  
                          //biggerIndex总是记录较大子节点的索引  
                          biggerIndex++;  
                      }  
                  }  
                  //如果k节点的值小于其较大的子节点的值  
                  if(data[k]<data[biggerIndex]){  
                      //交换他们  
                      swap(data,k,biggerIndex);  
                      //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值  
                      k=biggerIndex;  
                  }else{  
                      break;  
                  }  
              }
          }
      }
      //交换
      private static void swap(int[] data, int i, int j) {  
          int tmp=data[i];  
          data[i]=data[j];  
          data[j]=tmp;  
      } 
  ```

### 交换类排序

* 冒泡排序

  * 假设待排序表长为n，从后往前（或从前往后）两两比较相邻元素的值，若为逆序（即A[i-1]>A[i]），则交换它们，直到序列比较完。我们称它为一趟冒泡，结果将最小的元素交换到待排序列的第一个位置。下一趟冒泡时，前一趟确定的最小元素不再参与比较，待排序列减少一个元素，每趟冒泡的结果把序列中的最小元素放到了序列的最终位置，……，这样最多做n-1趟冒泡就能把所有元素排好序。
  * 空间复杂度：交换时开辟了存储空间来存储中间变量，所以空间复杂度为O(1)
  * 时间复杂度
  * 稳定性：当两个关键字相等，if判断条件不成立，所以不会发生数据移动。所以是稳定的。

  ```java
  //冒泡排序
          for (int i = 0; i < a.length; i++) {
              for(int j = 0; j<a.length-i-1; j++){
                  //这里-i主要是每遍历一次都把最大的i个数沉到最底下去了，没有必要再替换了
                  if(a[j]>a[j+1]){
                      int temp = a[j];
                      a[j] = a[j+1];
                      a[j+1] = temp;
                  }
              }
          }
  ```

* 快速排序

  * 快速排序是一种基于分治法的排序方法。
    每一趟快排选择序列中任一个元素作为枢轴(pivot)(通常选第一个元素)，将序列中比枢轴小的元素都移到枢轴前边，比枢轴大的元素都移到枢轴后边。
  * 时间复杂度：
    最好情况下时间复杂度为O(nlogn) ,待排序序列越无序，算法效率越高。
     最坏情况下时间复杂度为O(n^2)，待排序序列越有序，算法效率越低。
  * 空间复杂度：
    由于快速排序是递归的，需要借助一个递归工作栈来保存每一层递归调用的必要信息，其容量应与递归调用的最大深度一致。
    最好情况下为 ⌈log2(n+1)⌉(每次partition都很均匀)递归树的深度O(logn)
    最坏情况下，因为要进行n-1次递归调用，所以栈的深度为O(n)；
  * 稳定性：快速排序是不稳定的，是因为存在交换关键字。

```java
private static void quick(int[] a) {
        if(a.length>0){
            quickSort(a,0,a.length-1);
        }
    }

    private static void quickSort(int[] a, int low, int high) {
        if(low<high){ //如果不加这个判断递归会无法退出导致堆栈溢出异常
            int middle = getMiddle(a,low,high);
            quickSort(a, 0, middle-1);
            quickSort(a, middle+1, high);
        }
    }

    private static int getMiddle(int[] a, int low, int high) {
        int temp = a[low];//基准元素
        while(low<high){
            //找到比基准元素小的元素位置
            while(low<high && a[high]>=temp){
                high--;
            }
            a[low] = a[high]; 
            while(low<high && a[low]<=temp){
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
    }
```



### 归并排序

* 假定待排序表含有n个记录，则可以看成是n个有序的子表，每个子表长度为1，然后两两归并，得到 ⌈n/2⌉个长度为2或1的有序表；再两两归并，……如此重复，直到合并成一个长度为n的有序表为止，这种排序方法称为2-路归并排序。
* 例如：49 38 65 97 76 13 27
  * ①首先将整个序列的每个关键字看成一个单独的有序的子序列
  * ②两两归并，49和38归并成{38 49} ，65和97归并成{65 97}，76和13归并成{13 76}，27没有归并对象
  * ③两两归并，{38 49}和{65 97}归并成{38 49 65 97}，{13,76}和27归并成{13 27 76}
  * ④两两归并，{38 49 65 97}和{13 27 76}归并成{13 27 38 49 65 76 97}
* 时间复杂度：O(nlog2n)
* 空间复杂度:因为需要将这个待排序序列转存到一个数组，所以需要额外开辟大小为n的存储空间，即空间复杂度为O(n)
* 稳定性：稳定

```java
private static void mergeSort(int[] a, int left, int right) {
        if(left<right){
            int middle = (left+right)/2;
            //对左边进行递归
            mergeSort(a, left, middle);
            //对右边进行递归
            mergeSort(a, middle+1, right);
            //合并
            merge(a,left,middle,right);
        }
    }

    private static void merge(int[] a, int left, int middle, int right) {
        int[] tmpArr = new int[a.length];
        int mid = middle+1; //右边的起始位置
        int tmp = left;
        int third = left;
        while(left<=middle && mid<=right){
            //从两个数组中选取较小的数放入中间数组
            if(a[left]<=a[mid]){
                tmpArr[third++] = a[left++];
            }else{
                tmpArr[third++] = a[mid++];
            }
        }
        //将剩余的部分放入中间数组
        while(left<=middle){
            tmpArr[third++] = a[left++];
        }
        while(mid<=right){
            tmpArr[third++] = a[mid++];
        }
        //将中间数组复制回原数组
        while(tmp<=right){
            a[tmp] = tmpArr[tmp++];
        }
    }
```

### 基数排序

* 基数排序(也叫桶排序)是一种很特别的排序方法，它不是基于比较进行排序的，而是采用多关键字排序思想（即基于关键字各位的大小进行排序的），借助“分配”和“收集”两种操作对单逻辑关键字进行排序。基数排序又分为最高位优先（MSD）排序和最低位优先（LSD）排序。
* 例子：53, 3, 542, 748, 14, 214, 154, 63, 616
  * 补充位数：053, 003, 542, 748, 014, 214, 154, 063, 616
  * 桶实际是一个队列，先进先出(从桶的上面进，下面出)
  * 关键字数量为n,关键字的位数为d,比如748 d=3，r为关键字的基的个数，就是组成关键字的数据的种类，比如十进制数字一共有0至9一共10个数字，即r=10
* 空间复杂度：需要开辟关键字基的个数个队列，所以空间复杂度为O(r)
* 时间复杂度：需要进行关键字位数d次"分配"和"收集"，一次"分配"需要将n个关键字放进各个队列中，一次"收集"需要将r个桶都收集一遍。所以一次"分配"和一次"收集"时间复杂度为O(n+r)。d次就需要O(d(n+r))的时间复杂度。
* 稳定性：由于是队列，先进先出的性质，所以在分配的时候是按照先后顺序分配，也就是稳定的，所以收集的时候也是保持稳定的。即基数排序是稳定的排序算法。

```java

    private static void sort(int[] array) {
        //找到最大数，确定要排序几趟
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if(max<array[i]){
                max = array[i];
            }
        }
        //判断位数
        int times = 0;
        while(max>0){
            max = max/10;
            times++;
        }
        //建立十个队列
        List<ArrayList> queue = new ArrayList<ArrayList>();
        for (int i = 0; i < 10; i++) {
            ArrayList queue1 = new ArrayList();
            queue.add(queue1);
        }
        //进行times次分配和收集
        for (int i = 0; i < times; i++) {
            //分配
            for (int j = 0; j < array.length; j++) {
                int x = array[j]%(int)Math.pow(10, i+1)/(int)Math.pow(10, i);
                ArrayList queue2 = queue.get(x);
                queue2.add(array[j]);
                queue.set(x,queue2);
            }
            //收集
            int count = 0;
            for (int j = 0; j < 10; j++) {
                while(queue.get(j).size()>0){
                    ArrayList<Integer> queue3 = queue.get(j);
                    array[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }
    }
```



### 外部排序

* 需要将待排序的记录存储在外存上，排序时再把数据一部分一部分的调入内存进行排序。在排序过程中需要多次进行内存和外存之间的交换，对外存文件中的记录进行排序后的结果仍然被放到原有文件中。这种排序的方法就叫做外部排序。
* 如何得到初始的归并段
  * 置换选择排序：解决排序段放入内存的问题
* 如何减少多个归并段的归并次数
  * 最佳归并树：最少的归并次数（I/O次数）
* 如何每次m路归并快速得到最小的关键字
  * 败者树：减少比较次数
* 概要: 内存容量无法容纳大量数据
