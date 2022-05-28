import java.util.*;

public class Robot1 {
    public static class Point{
        private String name;
        private int scores;
        public Point(String n, int a){
            name=n;
            scores =a;
        }
        String getName(){return name;}
        int getAge(){return scores;}
    }
   public static class PersonAgeComparator implements Comparator<Point> {
        public int compare(Point a, Point b){
            if(a.getAge()> b.getAge())
                return -1;
            else if(a.getAge()< b.getAge())
                return 1;
            else
                return 1;
        }
    }
}

















