## 图

1。  为什么要有图

线性表局限于一个直接前驱和一个直接后驱 的关系。

树也只能有一个直接前驱也就是一个父节点

当我们需要表示多对多的关系时，这里我们就用到了图。

---

2  图的基本概念

**图** 是由顶点集合及顶点间的关系集合组成的一种数据结构。顶点之间的关系称为边，一个图 G 记为 G = （V, E） V是顶点 vi 的有限集合，n 为顶点数， E 是边的有限集合.

- 无向图

  无向图中的边没有方向，每条边用两个顶点的无序对表示。

- 有向图

  有向图 中边有方向，每条边用两个顶点的有序对表示，如<vi, vj> 表示从顶点 vi 到 vj 的一条有向边，vi 是边的起点， vj 是边的终点。因此 <vi, vj> 和 <vj, vi>  表示方向不同的两条边。

- 完全图

  **完全图**  是指图的边数达到最大值。n 个顶点的完全图记为 Kn。 无向完全图 Kn 的边数为 n* (n - 1)/2, 有向完全图Kn 的边数为 n*(n-1)。

- 带权图

  带权图是指图中的边具有权值。再不同的应用中，权值有不同的含义。

- 邻接顶点

  若(vi, vj) 是无向图 E（G） 中的一条边，则称 vi 和 vj 互为邻接顶点。

  若<vi, vj> 是有向图 E（G） 中的一条边， 则称顶点 vj， 顶点 vi，邻接自顶点 vi, 边< vi， vj> 与顶点 vi 和 vj 相关联。

3 图的表示和实现

- 边采用顺序存储结构，二维数组存储，称为图的邻接矩阵。
- 边采用链式存储结构，存储行的后继，即矩阵行的单链表，称为图的邻接表。
- 边采用链式存储结构， 存储行和列的后继，即矩阵的十字链表，称为图的邻接多重表。

### 图的深度优先遍历

```java
//核心代码

//深度优先遍历
private void dfs(boolean[] isVisited, int i){
    //首先访问该节点输出
    System.out.print(getValueByIndex(i) +"->");
    //将节点设为已经访问
    isVisted[i] = true;
    //查找结点 i 的第一个临界点 w
    int w = getFirstNeighbor(i);
    while(w != -1){ // 说明有
        if(!isVisted[w]){
            dfs(isVisted,w);
        }
        //如果 w 结点已经被访问过
        w = getNextNeighbor(i,w);
    }
}

//对dfs 进行一个重载，遍历我们所有结点进行 dfs
public void dfs(){
    isVisited = new boolean[vertexList.size()];
    //遍历所有结点，进行 dfs【回溯】
    for(int i = 0; i< getNumOfVertex(); i++){
        if(!isVisited[i]){
            dfs(isVisited,i);
        }
    }
}
```

### 图的广度优先遍历

```java
//对一个结点进行广度优先遍历的方法
private void bfs(bolean[] isVisited, int i){
    int u; //表示队列的头结点对应下标
    int w; // 邻接结点 w
    //队列，记录结点访问的顺序
    LinkedList queue = new LinkedList();
    //访问结点，输出结点信息
    System.out.print(getValueByIndex(i) + "=>");
    //标记为已访问
    isVisited[i] = true;
    //将 结点加入队列
    queue.allLast(i);
    
    while(!queue.isEmpty()){
        u = (Inter)queue.removeFirst();
        w = getFirstNeighbor(u);
        while(w != -1){
            //是否访问过
            if(!isVisted[w]){
                System.out.print(getValueByIndex(w) +"=>");
                //标记为已经访问
                isVisited[w] = true;
                //入队
                queue.addLast(w);
            }
            //以u 为前驱点，找 w 后面的下一个临界点
            w = getNextNeighbor(u,w); //体现出我们的广度优先
        }
    }
}
```

