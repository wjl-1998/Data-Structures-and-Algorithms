package linkedList;

public class Polynomial {
	public static void main(String[] args) {
		//多项式p1
        PolyList p1=new PolyList();
        p1.insert(new PolyNode(2,2));
        p1.insert(new PolyNode(100,3));
        p1.insert(new PolyNode(45,5));
        p1.insert(new PolyNode(3,20));
        System.out.println("p1="+p1.printS());
       
      //多项式p2
        PolyList p2=new PolyList();
        p2.insert(new PolyNode(8,2));
        p2.insert(new PolyNode(7,3));
        p2.insert(new PolyNode(4,4));
        p2.insert(new PolyNode(6,18));
        p2.insert(new PolyNode(7,20));
        System.out.println("p2="+p2.printS());
        
       //相加
       PolyList resultList1= PolyList.add(p1, p2);
       System.out.println("p1+p2="+resultList1.printS());
       
       System.out.println();
       
     //多项式p3
       PolyList p3=new PolyList();
       p3.insert(new PolyNode(2,1));
       p3.insert(new PolyNode(3,2));
       p3.insert(new PolyNode(4,3));
       System.out.println("p3="+p3.printS());
       
       
     //多项式p4
       PolyList p4=new PolyList();
       p4.insert(new PolyNode(5,1));
       p4.insert(new PolyNode(1,2));
       System.out.println("p4="+p4.printS());
       
       //相乘
       PolyList resuList2=PolyList.multiply(p3, p4);
       System.out.println("p3*p4="+resuList2.printS());
	}

}

class PolyList{
	PolyNode head;
	PolyNode current;
	
	public PolyList() {
		head = new PolyNode();
		current = head;
		head.next = null;
	}
	
	public boolean isEmpty() {
		return head.next == null;
	}
	
	public void insert(PolyNode node) {
		current.next = node;
		current = node;
	}
	
	public String printS() {
		StringBuilder s=new StringBuilder("");
		StringBuilder a=new StringBuilder("");
		StringBuilder i=new StringBuilder("");
		StringBuilder theOne=new StringBuilder("");
		
		current = head.next;
		while(current != null) {
			a.delete(0, a.length());
			i.delete(0, i.length());
			theOne.delete(0, theOne.length());
			
			if(current.getA() == 1)
				a.append("");
			else
				a.append(String.valueOf(current.getA()));
			
			if(current.getI() == 1) {
				i.append("");
				theOne.append(a.toString()).append("x").append(i.toString());
			}else {
				i.append(String.valueOf(current.getI()));
				theOne.append(a.toString()).append("x^").append(i.toString());
			}
			
			if(current == head.next)
				s.append(theOne.toString());
			else
				s.append("+").append(theOne.toString());
			current = current.next;
		}
		return s.toString();
	}
	
	//加法运算
	public static PolyList add(PolyList p1, PolyList p2) {
		PolyList result = new PolyList();
		p1.current = p1.head.next;
		p2.current = p2.head.next;
		while(p1.current != null && p2.current != null) {
			if(p1.current.getI() == p2.current.getI()) {
				result.insert(new PolyNode(p1.current.getA()+ p2.current.getA(),p1.current.getI()));
				p1.current = p1.current.next;
				p2.current = p2.current.next;
			}
			
			else if(p1.current.getI() < p2.current.getI()) {
				result.insert(p1.current);
				p1.current = p1.current.next;
			}else {
				result.insert(p2.current);
				p2.current = p2.current.next;
			}
		}
		
		while(p1.current != null) {
			result.insert(p1.current);
			p1.current = p2.current.next;
		}
		while(p2.current != null) {
			result.insert(p2.current);
			p2.current = p2.current.next;
		}
		
		return result;
	}
	
	//乘法算法
	public static PolyList multiply(PolyList p1, PolyList p2) {
		
		PolyList result = new PolyList();
		p1.current = p1.head.next;
		p2.current = p2.head.next;
		while(p1.current != null) {
			while(p2.current != null) {
				int a = p1.current.getA()*p2.current.getA();
				int i = p1.current.getI() + p2.current.getI();
				
				result.insert(new PolyNode(a, i));
				p2.current = p2.current.next;
			}
			
			p1.current = p1.current.next;
			p2.current = p2.head.next;
		}
		
		//合并同类项
		result.current = result.head.next;
		PolyNode tempPrevious = result.current;
		PolyNode temp = result.current.next;
		while(result.current.next != null) {
			while(temp != null) {
				if(temp.getI() != result.current.getI()) {
					temp = temp.next;
					tempPrevious = tempPrevious.next;
				}else {
					result.current.setA(result.current.getA()+temp.getA());
					tempPrevious.next=temp.next;
					temp=temp.next;
				}
			}
			result.current = result.current.next;
			tempPrevious = result.current;
			temp = result.current.next;
		}
		return result;
	}
	
}

class PolyNode{
	private int a;
	private int i;
	PolyNode next;
	
	public PolyNode(int a, int i) {
		this.a = a;
		this.i = i;
		this.next = null;
	}
	
	public PolyNode() {
		this(0,0);
	}
	
	public int getA() {
		return a;
	}
	
	public int getI() {
		return i;
	}
	
	public void setA(int a) {
		this.a = a;
	}
	
	public void setI(int i) {
		this.i = i;
	}
	
}