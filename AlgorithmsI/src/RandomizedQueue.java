import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] a;
	private int N;
	private int capacity;
	
	private class listIterator implements Iterator<Item>{
		private int current =0;
		private int[] shuffledIndexes = new int[N];
		public boolean hasNext(){
			if(current == 0){
				for(int i=0;i<N;i++){
					shuffledIndexes[i] = i;
					StdRandom.shuffle(shuffledIndexes);
				}
				
			}
			return (current<N);
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
		public Item next(){
			if(current==0){
				for(int i=0;i<N;i++){
					shuffledIndexes[i] = i;
					StdRandom.shuffle(shuffledIndexes);
				}
			}
			if(current >= N || size()==0){
				throw new java.util.NoSuchElementException();
			}
			return a[shuffledIndexes[current++]];
		}
	}

	public RandomizedQueue() {
		N=0;
		capacity=1;
		a= (Item[]) new Object[capacity];
		
	}
	public boolean isEmpty(){
		return (N==0);
	}
	public int size(){
		return N;
	}
	private void resizePlus(){
		capacity *=2;
		Item[] temp = (Item[]) new Object[capacity];
		for(int i=0;i<N;i++){
			temp[i] = a[i];
		}
		a = temp;
	}
	private void resizeMinus(){
		capacity /=2;
		Item[] temp = (Item[]) new Object[capacity];
		for(int i=0;i<capacity;i++){
			temp[i] = a[i];
		}
		a=temp;
	}
	public void enqueue(Item item){
		if(item==null){
			throw new NullPointerException();
		}
		if(N+1>capacity){
			resizePlus();
		}
		a[N++]=item;
		
	}
	public Item dequeue(){
		if(isEmpty()){
			throw new java.util.NoSuchElementException();
		}
		int i = StdRandom.uniform(N);
		Item item = a[i];
		a[i] = a[--N];
		a[N] = null;
		if(capacity/4>N){
			resizeMinus();
		}
		return item;
		
	}
	public Item sample(){
		if(isEmpty()){
			throw new java.util.NoSuchElementException();
		}
		return a[StdRandom.uniform(N)];
		
	}
	public Iterator<Item> iterator(){
		return new listIterator();
	}
	

	public static void main(String[] args) {
		

	}

}
