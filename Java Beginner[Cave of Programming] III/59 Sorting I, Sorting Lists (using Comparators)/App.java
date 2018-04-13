import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/*
 * Wherein we learn how to sort lists
 * To sort a list in something other than natural order, you have to create a comparator(in the form of a class) that implements Comparable interface.
 */
class Person {
	private int id;
	private String name;
	
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	
	
}
class StringLengthComparator implements Comparator<String>{

	@Override
	public int compare(String s1, String s2) { 
		int len1 = s1.length();
		int len2 = s2.length();
		
		if(len1>len2){
			return 1; //s1 after s2
		}
		if(len1<len2){
			return -1; //s1 before s2
		}
		
		return 0;
	}
	
}

class AlphabeticalComparator implements Comparator<String>{

	@Override
	public int compare(String s1, String s2) { 
		
		return s1.compareTo(s2); //compareTo is a method of the Comparable interface. It pretty much defines natural order.
	}
	
}

class ReverseAlphabeticalComparator implements Comparator<String>{

	@Override
	public int compare(String s1, String s2) { 
		
		return -s1.compareTo(s2); //Neat trick.
	}
	
}


public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		///////////////////////// Sorting Strings ///////////////////////////////////
		List<String> animals = new ArrayList<String>();
		
		
		animals.add("tiger");
		animals.add("lion");
		animals.add("snake");
		animals.add("mongoose");
		animals.add("cat");
		animals.add("elephant");
		
		Collections.sort(animals); //Java's own sorting method. Sorts in natural order (alphabetical order, numerical order etc.)
		
		//Collections.sort(animals,new StringLengthComparator()); //Using our own custom comparator
		//Collections.sort(animals,new AlphabeticalComparator());
		Collections.sort(animals,new ReverseAlphabeticalComparator()); //In reverse alphabetical order.
		
		for(String animal: animals){
			System.out.println(animal);
		}
		
		
		///////////////////////// Sorting Numbers ///////////////////////////////////
		List<Integer> numbers = new ArrayList<Integer>();
		
		numbers.add(3);
		numbers.add(36);
		numbers.add(73);
		numbers.add(40);
		numbers.add(1);
		
		Collections.sort(numbers, new Comparator<Integer>(){ //Custom Comparator using Anonymous classes

			@Override
			public int compare(Integer num1, Integer num2) {
				
				return -num1.compareTo(num2);
			}
			
		});
		
		for(Integer number: numbers){
			System.out.println(number);
		}
		
		///////////////////////// Sorting Arbitrary Objects///////////////////////////////////
		
		List<Person> people = new ArrayList<Person>();
		
		people.add(new Person(1,"Joe"));
		people.add(new Person(3,"Bob"));
		people.add(new Person(4,"Clare"));
		people.add(new Person(2,"Sue"));
		
		//Sort in order of ID
		Collections.sort(people, new Comparator<Person>(){ //people doesn't have a natural order; it doesn't implement the comparable interface. So we have to define one for it.
		
			@Override
			public int compare(Person p1, Person p2) {
				if(p1.getId()>p2.getId()){
					return 1;
				}else if(p1.getId()<p2.getId()){
					return -1;
				}
				return 0;
			} 
			
		}); 
		
		for(Person person:people){
			System.out.println(person);
		}
		
		System.out.println("");
		
		//Sort in order of name
		Collections.sort(people, new Comparator<Person>(){

			@Override
			public int compare(Person p1, Person p2) {
				return p1.getName().compareTo(p2.getName());
			} 
			
			
		
		}); 
		
		for(Person person:people){
			System.out.println(person);
		}
		
		
		
	}

}
