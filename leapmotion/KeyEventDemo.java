
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.io.Console;
import java.lang.Math;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;
 
public class KeyEventDemo extends JFrame
        implements KeyListener,
        ActionListener
{
    JTextArea displayArea;
    JTextField typingArea;
    static final String newline = System.getProperty("line.separator");

    Controller controller = new Controller(); //because fuck good practice
    //LetterSign[] letters = new LetterSign();
     
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        //controller = new Controller();
        



        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

         
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        KeyEventDemo frame = new KeyEventDemo("KeyEventDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Set up the content pane.
        frame.addComponentsToPane();
        
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
     
    private void addComponentsToPane() {
         
        JButton button = new JButton("Record");
        button.addActionListener(this);
         
        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);
         
        //Uncomment this if you wish to turn off focus
        //traversal.  The focus subsystem consumes
        //focus traversal keys, such as Tab and Shift Tab.
        //If you uncomment the following line of code, this
        //disables focus traversal and the Tab events will
        //become available to the key event listener.
        //typingArea.setFocusTraversalKeysEnabled(false);
         
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(375, 125));
         
        getContentPane().add(typingArea, BorderLayout.PAGE_START);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(button, BorderLayout.PAGE_END);
    }
     
    public KeyEventDemo(String name) {
        //constructador
        super(name);
    }
     
     
    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        
        
        //
        //displayInfo(e, "KEY TYPED: ");
    }
     
    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        //We only care about when a key is typed
        Frame frame = controller.frame();
        if(e.getKeyCode() <= 122 && e.getKeyCode() >=65 && frame.hands().count()>0 && frame.fingers().count()>0){
            displayInfo(e, "KEY PRESSED: ", frame);
        }
        typingArea.setText("");
        
    }
     
    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
        //We only care about when a key is typed
        //displayInfo(e, "KEY RELEASED: ");
    }
     
    /** Handle the button click. */
    public void actionPerformed(ActionEvent e) {
        //Clear the text components.
        displayArea.setText("");
        typingArea.setText("");
         
        //Return the focus to the typing area.
        typingArea.requestFocusInWindow();
    }
     
    /*
     * We have to jump through some hoops to avoid
     * trying to print non-printing characters
     * such as Shift.  (Not only do they not print,
     * but if you put them in a String, the characters
     * afterward won't show up in the text area.)
     */
    private void displayInfo(KeyEvent e, String keyStatus, Frame frame){
        
        String frameInfo = "Frame id: " + frame.id()
                         + ", timestamp: " + frame.timestamp()
                         + ", hands: " + frame.hands().count()
                         + ", fingers: " + frame.fingers().count()
                         + ", tools: " + frame.tools().count()
                         + ", gestures " + frame.gestures().count();
        //You should only rely on the key char if the event
        //is a key typed event.

        int id = e.getID();
        String keyString;
        int keyCode = 0;
        char c;
        if (id == KeyEvent.KEY_TYPED) {
            c = e.getKeyChar();
            keyString = "key character = '" + c + "'";
        } else {
            keyCode = e.getKeyCode();
            keyString = "key code = " + keyCode
                    + " ("
                    + KeyEvent.getKeyText(keyCode)
                    + ")";
        }

        LetterSign nl = new LetterSign(KeyEvent.getKeyText(keyCode).charAt(0));
        
        for(Hand hand : frame.hands()) {
            //String handType = hand.isLeft() ? "Left hand" : "Right hand";
            //System.out.println("  " + handType + ", id: " + hand.id()
                             ////+ ", palm position: " + hand.palmPosition());

            // Get the hand's normal vector and direction
            nl.setPalmData(hand.palmNormal());
            //System.out.println(normal);
            //Vector direction = hand.direction();
//
            //// Calculate the hand's pitch, roll, and yaw angles
            //System.out.println("  pitch: " + Math.toDegrees(direction.pitch()) + " degrees, "
            //                 + "roll: " + Math.toDegrees(normal.roll()) + " degrees, "
            //                 + "yaw: " + Math.toDegrees(direction.yaw()) + " degrees");
//
            //// Get arm bone
            //Arm arm = hand.arm();
            //System.out.println("  Arm direction: " + arm.direction()
            //                 + ", wrist position: " + arm.wristPosition()
            //                 + ", elbow position: " + arm.elbowPosition());

            // Get fingers
            for (Finger finger : hand.fingers()) {
                //System.out.println("    " + finger.type() + ", id: " + finger.id()
                //                 + ", length: " + finger.length()
                //                 + "mm, width: " + finger.width() + "mm");//

                //Get Bones
                for(Bone.Type boneType : Bone.Type.values()) {
                    Bone bone = finger.bone(boneType);
                    nl.setDir(finger.type(),boneType,bone.direction());
                    nl.setPos(finger.type(),boneType,bone.nextJoint());
                    //System.out.println(finger.type() + " " + boneType + " - end: " + bone.nextJoint()
                    //                 + ", direction: " + bone.direction());
                }
            }
        }

        //System.out.print("\n\n\n\n\n\n\n\n");
        try {
               PrintWriter pw = new PrintWriter(new FileOutputStream(
                    new File("output.txt"), 
                         true /* append = true */)); 
               pw.append(nl.sign2String()+"\n");
               pw.close();
        } catch (Exception ex){
            System.out.println("widdit");
               //e.printStackTrace();
        }   
        
        //System.out.println(nl.sign2String());

        displayArea.append(keyString + newline);
        displayArea.setCaretPosition(displayArea.getDocument().getLength());
        
    }
}


class LetterSign{
    /*
    position        0
    direction       1

    thumb           0
    index           1
    middle          2
    ring            3
    pinky           4

    metacarpal      0
    proximal        1
    intermediate    2
    distal          3

    x               0
    y               1
    z               2
    */

    double[][][][] fingerData;
    double[] palmData;
    char letter;

    int getBoneAsInt(Bone.Type type){
        switch (type)
        {
            case TYPE_METACARPAL :
                return 0;
            case TYPE_PROXIMAL:
                return 1;
            case TYPE_INTERMEDIATE:
                return 2;
            case TYPE_DISTAL:
                return 3;
        }
        return -1;
    }   

    int getFingerAsInt(Finger.Type type){
        switch (type)
        {
            case TYPE_THUMB :
                return 0;
            case TYPE_INDEX:
                return 1;
            case TYPE_MIDDLE:
                return 2;
            case TYPE_RING:
                return 3;
            case TYPE_PINKY:
                return 4;
        }
        return -1;
    }

    public LetterSign(char newletter){

        this.fingerData = new double[2][5][4][3]; // pos, finger, bone, xyz
        this.palmData = new double[3];
        this.letter = Character.toLowerCase(newletter);

        return;     
    }

    public static LetterSign[] InitializeAlphabet(){
        LetterSign[] alphabet = new LetterSign[26];
        for(int i = 97;i<=122;i++){
            alphabet[i] = new LetterSign((char)i);
        }
        return alphabet;
    }

    public void setVals(boolean isDir, Finger.Type f, Bone.Type b, Vector vals){
        int i = isDir ? 1 : 0;
        int j = getFingerAsInt(f);
        int k = getBoneAsInt(b);
        
        fingerData[i][j][k][0] = vals.getX();
        fingerData[i][j][k][1] = vals.getY();
        fingerData[i][j][k][2] = vals.getZ();

        return;
    }

    public void setDir(Finger.Type f, Bone.Type b, Vector vals){
        //worker fct
        setVals(true, f, b, vals);
    }
    public void setPos(Finger.Type f, Bone.Type b, Vector vals){
        //wrkr fct
        setVals(false,f,b,vals);
    }

    public void setPalmData(Vector vals){
        palmData[0]=vals.getX();
        palmData[1]=vals.getY();
        palmData[2]=vals.getZ();
        return;
    }

    public String sign2String(){
        String signString = this.letter + " ";
        for (int ii=0;ii<=2; ii++){
            signString+=(Double.toString(palmData[ii]) + " ");
        }
        for(int ii = 0; ii <=1; ii++){
            for (int jj = 0; jj<=4;jj++){
                for(int kk = 0; kk<=3;kk++){
                    for(int ll = 0; ll<=2; ll++){
                        signString+= (Double.toString(fingerData[ii][jj][kk][ll])+" ");
                    }
                }
            }
        }
        return signString;
    }

    public LetterSign(String s){
        int n = 0;
        String[] tokens = s.split(" ");
        this.letter = Character.toLowerCase(tokens[n].charAt(0));
        n++;
        for (int ii=0;ii<=2; ii++){
            this.palmData[ii] = Double.parseDouble(tokens[n]);
            n++;
        }
        for(int ii = 0; ii <=1; ii++){
            for (int jj = 0; jj<=4;jj++){
                for(int kk = 0; kk<=3;kk++){
                    for(int ll = 0; ll<=2; ll++){
                        this.fingerData[ii][jj][kk][ll] = Double.parseDouble(tokens[n]);
                        n++;
                    }
                }
            }
        }
        return;
    }

}
