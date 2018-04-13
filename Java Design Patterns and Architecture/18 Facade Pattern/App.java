/*
 * A Facade Pattern means using a class that provides a simpler interface to some other classes.
 * i.e. Facade.
 * 
 * We will use the example of a game.
 */
public class App {
	public static void main(String[] args){
		
		//We will put the below three lines into the facade class.
		//InputSystem input = new InputSystem();
		//GameObjects objects = new GameObjects();
		//GameConsole screen = new GameConsole();
		
		Game game = new Game();
		
		//Mandatory game loop.
		while(true){ 
			// Input
			//input.getInput(); -> We will put this into the facade class.
			
			// Update game objects (player, bad guys, etc)
			//objects.update(input); -> We will put this into the facade class.
			
			// Draw
			//screen.draw(objects); -> We will put this into the facade class.
			
			// The idea of a facade pattern is to simplify the above using only a class.
		
			game.update();
			
		}
	}
}
