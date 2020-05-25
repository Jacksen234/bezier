
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class app extends PApplet {
    public static void main(String[] args){
        PApplet.main("app",args);
    }
    Vector p1 = new Vector(000, 300);
    Vector p2 = new Vector(400, 200);
    Vector p3 = new Vector(700, 800);
    Straight straight1 = new Straight(p1, p2);
    Straight straight2 = new Straight(p2, p3);
    Straight drivingStraight1;
    float i1 =p1.x;
    float i2 = p2.x;
    float i3 = p1.x;
    boolean incrementX = true;
    List<Vector> traceArray = new ArrayList<>();
    public void settings(){
        size(800,800);
    }
    public void setup(){
        frameRate(60);
    }
    public void draw(){
        background(40);
        drawInitials();
        //straight1
        if(incrementX){
            i1+= straight1.getSpeed(60,5);
            i2+= straight2.getSpeed(60,5);

        }else{
            i1-=straight1.getSpeed(60,5);
            i2-=straight2.getSpeed(60,5);
        }
        if(i1 >= p2.x){
            incrementX = false;
        }
        if(i1 <= p1.x){
            incrementX = true;
        }
        float yVal1 = straight1.getYVal(i1);
        ellipse(i1, yVal1, 10,10);
        //straight2
        float yVal2 = straight2.getYVal(i2);
        ellipse(i2, yVal2, 10,10);

        //driving points
        Vector driver1 = new Vector(i1, yVal1);
        Vector driver2 = new Vector(i2, yVal2);
        stroke(255);
        line(driver1.x, driver1.y, driver2.x, driver2.y);

        drivingStraight1 = new Straight(driver1, driver2);
        if(incrementX){
            i3 += drivingStraight1.getSpeedDelta(60, 5, p1.x, p3.x);
        }else{
            i3 -= drivingStraight1.getSpeedDelta(60, 5, p1.x, p3.x);
        }
        float yVal3 = drivingStraight1.getYVal(i3);
        noStroke();
        ellipse(i3, yVal3, 10, 10);
        noFill();
        stroke(189, 70, 96);
        beginShape();
        Vector v = new Vector(i3, yVal3);
        traceArray.add(v);
        for (Vector pos : traceArray){
            float posX = pos.x;
            float posY = pos.y;
            vertex(posX, posY);
        }
        if(i3 == p1.x + 3 || i3 > p3.x - 3){
            traceArray.clear();
            System.out.println("now");
        }
        endShape();

    }
    public void drawInitials(){
        strokeWeight(3);
        stroke(255);
        line(p2.x,p2.y,p1.x,p1.y);
        line(p2.x,p2.y,p3.x,p3.y);

        ellipseMode(CENTER);
        noStroke();
        fill(255);
        ellipse(p1.x,p1.y,10,10);
        ellipse(p2.x,p2.y,8,8);
        ellipse(p3.x,p3.y,8,8);
        ellipse(0,0,8,8);
    }

}
