
public class DoubleLinkedList {
	private DoubleNode head;
	private DoubleNode tail;
	private int size;
	public DoubleLinkedList(DoubleNode head) {
		this.head = head;
		this.tail=head;
		size=0;
		
	}

	public DoubleNode getHead() {
		return head;
	}

	public void setHead(DoubleNode head) {
		this.head = head;
	}

	public DoubleNode getTail() {
		return tail;
	}

	public void setTail(DoubleNode tail) {
		this.tail = tail;
	}
	

	
	public void ateApple() {
		size++;
		
	}
		
}
