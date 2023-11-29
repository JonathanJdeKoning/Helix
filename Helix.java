 /******************************************************
 * Written by Jonathan de Koning, CS1083, Section 006 *
******************************************************/

//Necessary imports
import java.util.*;
import java.awt.*;

public class Helix {

    /*
     *   I'm just handling the exception in another method up here because
     *   I think it looks nicer, and I want to eliminate indentation
     */
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

    /*
     *  Here's our method that handles the drawing of the Helix, as well as the text on-screen.
     *  It also declares our necessary variables
     */
    public static void drawHelix() {
        //Variables Declarations
        int width = 400;
        int height = 400;
        int direction = 0;
        int[] coords = {100,100,300,300};

        //Objects Declarations
        DrawingPanel panel = new DrawingPanel(width, height);
        Graphics g = panel.getGraphics();
        Scanner in = new Scanner(System.in);

        //Fill Canvas with black
        g.fillRect(0, 0, width, height);

        //Draw white box for helix
        g.setColor(Color.WHITE);
        g.fillRect(100, 100, width / 2, height / 2);

        //Get speed from user
        System.out.print("Please, input the speed (1 - 10): ");
        int speed = in.nextInt();

        //Get number of line movements from user
        System.out.print("Please, input the number of times the line will be shown: ");
        int lineMovements = in.nextInt();

        //Run for each line movement
        for (int i = 0; i < lineMovements; i++) {
            
            //Set color to white
            g.setColor(Color.WHITE);

            //Draw Variable data to canvas
            g.drawString("UTSA – CS1083 - Section 006 – Prj 3 - Helix - Jonathan de Koning", 15, 40);
            g.drawString("Speed: " + speed, 180, 70);
            g.drawString("i: " + (i+1), 190, 340);
            g.drawString("("+coords[0]+", "+coords[1]+"), ("+coords[2]+", "+coords[3]+")" , 145, 370);

            //Draw Helix with random color
            g.setColor(getColor());
            g.drawLine(coords[0],coords[1],coords[2],coords[3]);
            pause(100/speed);

            //Don't clear the canvas if we're on the last movement
            if (i == lineMovements-1) {return;}

            // I wanted to move this to a different method: "clearCanvas", but the rubric wouldn't allow it.
            g.setColor(Color.WHITE);
            g.drawLine(coords[0],coords[1],coords[2],coords[3]);
            g.setColor(Color.BLACK);
            g.fillRect(0,310,400,100);

            //Get the new direction of the Line
            direction = newPos(coords, direction);
        }
    }

    //Pretty self-explanatory: just returns a random color.
    public static Color getColor(){
        Random rand = new Random();
        int colorNum = rand.nextInt(9);
        switch (colorNum) {
            case 0 -> {return Color.GREEN;}
            case 1 -> {return Color.GRAY;}
            case 2 -> {return Color.YELLOW;}
            case 3 -> {return Color.RED;}
            case 4 -> {return Color.ORANGE;}
            case 5 -> {return Color.PINK;}
            case 6 -> {return Color.DARK_GRAY;}
            case 7 -> {return Color.BLUE;}
            case 8 -> {return Color.BLACK;}
            default -> {return Color.WHITE;}
        }
    }

    /*
     *  Not gonna lie, I had a bit of a gripe with this method. The whole "last Movement" variable is unneccesary
     *  and needlessly increases complexity, but I had to use it because of the rubric, which increased my line count
     *  by a bit. Regardless, this method takes an integer array of coordinates and the movement of the line,
     *  and modifies the coordinates accordingly. It then checks to see if the line should change direction.
     */
    public static int newPos(int[] coords, int direction){
        // Update line based on the direction it is moving
        switch (direction){
            case 0:
                coords[0] += 10;
                break;
            case 1:
                coords[1] += 10;
                break;
            case 2:
                coords[0] -= 10;
                break;
            case 3:
                coords[1] -= 10;
                break;
        }

        //If the line is now at a corner, switch the direction it's moving accordingly.
        if (coords[0] == 100 && coords[1] == 100) {direction = 0;}
        if (coords[0] == 100 && coords[1] == 300) {direction = 3;}
        if (coords[0] == 300 && coords[1] == 100) {direction = 1;}
        if (coords[0] == 300 && coords[1] == 300) {direction = 2;}

        //Calculate end coordinates
        coords[2] = Math.abs(coords[0]-400);
        coords[3] = Math.abs(coords[1]-400);

        return direction;
    }

    //Hmm, I wonder what this method could do 🤔 (Yes, an emoji)
    public static void main(String[] args) {
        System.out.println("UTSA – Fall 2023 – CS1083 - Section 006 – Prj 3 - Helix - written by Jonathan de Koning");
        drawHelix();
    }
}
