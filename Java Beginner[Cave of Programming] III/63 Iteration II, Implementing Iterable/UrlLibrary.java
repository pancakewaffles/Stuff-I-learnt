import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

//Class that stores some URLs
public class UrlLibrary implements Iterable<String> { //Iterable makes the class able to use to foreach loop.
	private LinkedList<String> urls = new LinkedList<String>();
	
	private class UrlIterator implements Iterator<String>{ //Iterator allows you to define how you want to iterate.
														   //My custom iterator
		private int index = 0; 
		
		@Override
		public boolean hasNext() {
			return index<urls.size();
		}

		@Override
		public String next() {
			
			StringBuilder sb = new StringBuilder();
			try {  //Downloading a website url 
				URL url = new URL(urls.get(index));
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
				
				String line = null;
				
				while((line = br.readLine())!= null){
					sb.append(line);
					sb.append("\n");
				}
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			index++;
			
			return sb.toString();
			
		}
		@Override
		public void remove(){
			urls.remove(index);
		}
		
	}
	public UrlLibrary() {
		urls.add("http://www.caveofprogramming.com");
		urls.add("http://www.facebook.com");
		urls.add("http://news.bbc.co.uk");
		
	}
	
	/*
	@Override
	public Iterator<String> iterator() { //the sole method of the Iterable interface
		return urls.iterator(); //using the default LinkedList iterator
	}
	*/
	@Override
	public Iterator<String> iterator() { //specifying my own iterator
		return new UrlIterator();
	}
	


}
