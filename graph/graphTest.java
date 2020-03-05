package graph;
import java.util.*;

/**
 * 图中节点值唯一标识这个节点
 *
 */

public class graphTest {

    static Map<Integer,LinkedList<Integer>> undirectedGraph = new LinkedHashMap<>();
    static Map<Integer,Boolean> visited = new HashMap();
    static Iterator<Map.Entry<Integer,LinkedList<Integer>>> it;
    /**
     * 递归深度优先遍历
     * @param start
     */
    static void DFS(Integer start){
        System.out.print(start+" ");
        visited.put(start,true);
        LinkedList<Integer>  neighbor = undirectedGraph.get(start);
        for(Integer i : neighbor){
            if(!visited.get(i)){
                DFS(i);
            }
        }
    }

    /**
     * 非递归深度优先遍历
     * @param start
     */
    static void DFSwithStack(Integer start){
        Stack<Integer> st = new Stack();
        System.out.print(start+" ");
        st.push(start);
        visited.put(start,true);
        while(!st.isEmpty()){
            int node = st.peek();
            LinkedList<Integer>  neighbor = undirectedGraph.get(node);
            boolean flag = false;
            if(neighbor.size() != 0) {
                for (Integer next : neighbor) {
                    if (!visited.get(next)) {
                        st.push(next);
                        System.out.print(next + " ");
                        visited.put(next, true);
                        flag = true;
                        break;
                    }
                }
            }
            if(!flag) {
                st.pop();
            }
        }
    }

    /**
     * 广度优先遍历
     */
    static void BFS(Integer start){
        Queue<Integer> q = new LinkedList();
        q.offer(start);
        System.out.print(start+" ");
        visited.put(start,true);
        while(!q.isEmpty()){
            int node = q.poll();
            LinkedList<Integer> neighbor = undirectedGraph.get(node);
            for(Integer i : neighbor){
                if(!visited.get(i)){
                    q.offer(i);
                    System.out.print(i+" ");
                    visited.put(i,true);
                }
            }
        }
    }

    static void initBeforeTravel(){
         Set<Integer> keys = visited.keySet();
         for(Integer key:keys){
             visited.put(key,false);
         }
        it = undirectedGraph.entrySet().iterator();
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入图中节点个数:");
        int sumOfNode = sc.nextInt();
        System.out.println("请输入图中" + sumOfNode + "个节点:");
        for(int i=0;i<sumOfNode;i++){
            int value = sc.nextInt();
            undirectedGraph.put(value,new LinkedList());
            visited.put(value,false);
        }
        System.out.println("请输入图中所有边:");

        String line = sc.nextLine();

        while(sc.hasNextLine()){
            line = sc.nextLine();
            if("".equals(line)){
                break;
            }
            String[] edge = line.split(" ");
            int node1 = Integer.parseInt(edge[0]);
            int node2 = Integer.parseInt(edge[1]);
            undirectedGraph.get(node1).add(node2);
            undirectedGraph.get(node2).add(node1);
        }

        initBeforeTravel();
        System.out.println("深度优先遍历序列：");
        while(it.hasNext()) {
            int start = it.next().getKey();
            if(!visited.get(start)) {
                DFS(start);
            }
        }

        initBeforeTravel();
        System.out.println("\n深度优先遍历序列：");
        while(it.hasNext()) {
            int start = it.next().getKey();
            if(!visited.get(start)) {
                DFSwithStack(start);
            }
        }

        initBeforeTravel();
        System.out.println("\n广度优先遍历序列：");
        while(it.hasNext()) {
            int start = it.next().getKey();
            if(!visited.get(start)) {
                BFS(start);
            }
        }
    }
}

