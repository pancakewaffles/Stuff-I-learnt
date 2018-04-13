
public class mergeSort  {

	public mergeSort() {
		
	}
	public static void merge(int[] a, int[] aux, int lo, int mid, int hi){
		assert isSorted(a,lo,mid);
		assert isSorted(a,mid+1,hi);
		
		for(int k= lo; k<= hi;k++){
			aux[k] = a[k];
		}
		int i=lo, j=mid+1;
		for(int k=lo;k<=hi;k++){
			if(i>mid){
				aux[k]=aux[j++];
			}
			else if(j>hi){
				a[k] = aux[i++];
			}
			else if(less(aux[j],aux[i])){ // check if aux[j] < aux[i]
				a[k] = a[j++];
			}
			else{
				a[k] = aux[i++]; //means aux[i] is smaller
			}
		}
		assert isSorted(a,lo,hi);
	}
	
	private static boolean less(int a, int b) {
		if(a<=b){
			return true;
		}else{ return false;}
	
	}
	private static boolean isSorted(int[] a, int lo, int hi){
		int count=0;
		for(int i=lo;i<hi;i++){
			if(a[i]<=a[i+1]){
				count++;
			}
			
		}
		if(count==a.length-1){
			return true;
		}else{return false;}
		
	}

	public static void sort(int[] a, int[] aux, int lo, int hi){
		if(hi<=lo){return;}
		int mid=lo + (hi - lo)/2;
		sort(a,aux,lo,mid);
		sort(a,aux,mid+1,hi);
		merge(a,aux,lo,mid,hi);
	}
	public static void sort(int[] a){
		int[] aux=new int[a.length];
		sort(a,aux,0,a.length-1);
	}

	public static void main(String[] args) {
		int[] a = new int[args.length];		
		for(int i=0;i<args.length;i++){
			a[i]=Integer.parseInt(args[i]);
		}
		sort(a);
		for(int i=0;i<a.length;i++){
		System.out.println(a[i]);
		}

	}


}
