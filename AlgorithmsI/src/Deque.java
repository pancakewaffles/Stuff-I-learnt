import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
	private int N;
	private Node first;
	private Node last;
	
	private class Node {
		private Item item;
		private Node next;
		private Node previous;
	}
	private class DequeIterator implements Iterator<Item>{
		
		private Node current=first;
		public boolean hasNext(){
			return (current!=null);
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
		public Item next(){
			    if(!hasNext()){
			    	throw new java.util.NoSuchElementException();
			    }else{
				Item item=current.item;
				current=current.next;
				return item;
			    }
			
		}
	}

	public Deque() {
		first=null;
		last=null;
		N=0;
		
		
	}
	public boolean isEmpty(){
		return (first==null);
	}
	public int size(){
		return N;
		
	}
	public void addFirst(Item item){
		if(item==null){
			throw new NullPointerException();
		}
		Node oldfirst = first;
		first = new Node();
		first.item=item;
		first.next=oldfirst;
		first.previous=null;
		if(oldfirst!=null){
		oldfirst.previous=first;
		}
		N++;
		if(N==1){
			last=first;
		}
	}
	public void addLast(Item item){
		if(item==null){
			throw new NullPointerException();
		}
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		last.previous=oldlast;
		if(oldlast!=null){
			oldlast.next=last;
		}
		N++;
		
		
		
		
	}
	public Item removeFirst(){
		if(first==null){
			throw new java.util.NoSuchElementException();
		}
		Item item=first.item;
		first=first.next;
		N--;
		return item;
		
	}
	public Item removeLast(){
		if(first==null){
			throw new java.util.NoSuchElementException();
		}
		Item item=last.item;
		last=last.previous;
		N--;
		return item;
		
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	
	public static void main(String[] args) {

		
	}

}
