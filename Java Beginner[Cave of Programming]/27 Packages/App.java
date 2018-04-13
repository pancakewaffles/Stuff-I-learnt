/* Packages perform 2 functions:
 *    1. Organize your code
 *    2. Stop you having conflicts between class names
 *       i.e. two same classes but in different packages is okay
 */
import ocean.Fish; //importing from other package
import ocean.plants.Seaweed; //analogous to folders

public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fish fish = new Fish();
		Seaweed weed = new Seaweed();

	}

}

//If you want to distribute your package, convention says that you name it your website, but backwards
// i.e. caveofprogramming.com => package called com.caveofprogramming