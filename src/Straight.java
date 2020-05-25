import java.lang.reflect.Array;

public class Straight {
    Vector point1;
    Vector point2;
    float m;
    float c;
    float startX;
    float lengthOfLine;
    float deltaX;
    float deltaY;

    public Straight( Vector point1, Vector point2){
        this.point1 = point1;
        this.point2 = point2;
        this.m = determineVal(point1, point2)[0];
        this.c = determineVal(point1, point2)[1];
        this.startX = determineVal(point1, point2)[2];
        this.lengthOfLine = determineVal(point1, point2)[3];
        this.deltaX = determineVal(point1, point2)[4];
        this.deltaY = determineVal(point1, point2)[5];
    }
    public float[] determineVal( Vector p1, Vector p2){
        float differenceY = p2.y - p1.y;
        float differenceX = p2.x - p1.x;
        float slope = differenceY / differenceX;

        float c = p1.y;
        float[] vals = new float[6];
        vals[0] = slope;
        vals[1] = c;
        vals[2] = p1.x;
        vals[3] = (float) Math.sqrt(Math.pow(differenceX, 2.0f) + Math.pow(differenceY, 2.0f));
        vals[4] = differenceX;
        vals[5] = differenceY;
        return vals;
    }
    public float getYVal(float x){
        return this.m * (x - this.startX) +this.c;
    }
    public float getSpeed(int frameRate, int time){
        int totalFrames = frameRate * time;
        return this.deltaX / totalFrames;
    }
    public float getSpeedDelta(int frameRate, int time, float x1, float x2){
        int totalFrames = frameRate * time;
        return (x2 - x1) / totalFrames;
    }
}
