//Sublime 2 build file (.sublime-build) entry: {"cmd": ["javac",  "-classpath", "leapsdk/lib/LeapJava.jar", "LeapTest.java"]}
//sample compile statement: 
import com.leapmotion.leap.*;
import java.io.Console;
import java.io.IOException;
import java.lang.Math;

class LeapTest{
	public static void main(String[] args){
		try{
			System.out.print("1");
			LeapListener listener = new LeapListener();
			System.out.print("2");
			Controller controller = new Controller();
			System.out.print("3");
			controller.addListener(listener);
			System.out.print("4");
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("Press Enter to quit...");
		try{
			System.in.read();
		} catch (IOException e){
			e.printStackTrace();
		}
		return;
	}
}


class LeapListener extends Listener {

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    public void onFrame(Controller controller) {
    	Console cnsl = System.console();
        Frame frame = controller.frame();
        System.out.println("Frame id: " + frame.id());
        cnsl.flush();
    }

    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }
}