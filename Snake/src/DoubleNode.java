
public class DoubleNode {

	private int x,y;
	private DoubleNode prev, next;

	public DoubleNode(int x,int y) {
		this.x = x;
		this.y=y;
		this.prev = null;
		this.next = null;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public DoubleNode getPrev() {
		return prev;
	}

	public void setPrev(DoubleNode prev) {
		this.prev = prev;
	}

	public DoubleNode getNext() {
		return next;
	}

	public void setNext(DoubleNode next) {
		this.next = next;
	}
}
