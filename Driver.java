
import java.io.*;

public class Driver {
    public static void main(String [] args) throws IOException{
        File f1 = new File("test.txt"); File f2 = new File("test2.txt");
        Polynomial p = new Polynomial(f1);
        Polynomial p2 = new Polynomial(f2);
        
        System.out.println(p);
        System.out.println(p2);
        System.out.println(p.add(p2));
        System.out.println(p.multiply(p2));
        System.out.println(p2.evaluate(2));
        System.out.println(p2.evaluate(0));

        p.saveToFile("output.txt");
    }
}