import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/*
 * Natural Order in the context of sorting arrays, tree sets and tree maps
 * How to define your own natural order for your own classes?
 */

class Person implements Comparable<Person>{
	private String name;

	public Person(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}

	@Override
	public int compareTo(Person person) { 
		//Defining the Natural Order
		/*
		 * Sorting via name lengths.
		 * For ArrayList this works.
		 * But for the TreeSet something weird happens that hadn't happen before.
		 * Sue, who happens to have to same name length as Joe, is gone.
		 * Problem is, there is a conflict between the .equals method and the compareTo method.
		 * You have to make sure that your equals method and your compareTo method must arrive at the same conclusion.
		 * What happened is that you are telling TreeSet that "Sue" equals "Joe", and since there cannot be duplicates in a Set, keep only one of them.
		 */
		int len1 = name.length();
		int len2 = person.name.length();
		if(len1 > len2){
			return 1;
		}else if (len1<len2){
			return -1;
		}
		else{ //Case of equal lengths, but different string values
			return name.compareTo(person.name);
		}
		
		
		//return name.compareTo(person.name); //Make these person sort their names in alphabetical order
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) { 
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		List<Person> list = new ArrayList<Person>();
		SortedSet<Person> set = new TreeSet<Person>(); //SortedSet or Set both works. SortedSet is just an emphasis.
		
		addElements(list);      //TreeSet won't work as well if we do not have the natural order defined.
		addElements(set);
		
		Collections.sort(list); //Sorting the list in Alphabetical Order, String's Natural Order.
		                        //But the class Person does not have a natural order defined, so we have to define one for it.
		showElements(list);
		System.out.println();
		showElements(set);
		

	}
	
	private static void addElements(Collection<Person> col){ //Collection is the parent interface of both sets and lists.
		col.add(new Person("Joe"));
		col.add(new Person("Sue"));
		col.add(new Person("Juliet"));
		col.add(new Person("Clare"));
		col.add(new Person("Mike"));
	}
	private static void showElements(Collection<Person> col){ 
		for(Person element:col){
			System.out.println(element);
		}
	}

}
