/*
 * So we have seen Anonymous Classes and Abstract Classes. Now we shall talk about Inner Classes.
 * Inner Classes are classes within classes. Check out Robot.java
 */

public class App {


	public static void main(String[] args) {
		Robot robot = new Robot(7);
		robot.start();
		
		Robot.Brain brain = robot.new Brain(); //LOL THis is a really stupid syntax. Don't call inner classes from other classes!
		brain.think();                         
		
		Robot.Battery battery = new Robot.Battery();
		battery.charge();
	}

}
