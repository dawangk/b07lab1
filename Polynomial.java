import java.io.*;
import java.util.*;
public class Polynomial{
    double [] coefficients;
    int [] exp;

    public Polynomial(){
        coefficients = new double[1];
        exp = new int[1];
        coefficients[0] = 0;
        exp[0] = 0;
    }

    public Polynomial(double[] coefficients, int[] exp){
        this.coefficients = new double[coefficients.length];
        this.exp = new int[exp.length];
        for(int i = 0;i<coefficients.length;i++){
            this.coefficients[i] = coefficients[i];
            this.exp[i] = exp[i];
        }
        
    }

    public Polynomial(File f) throws IOException{
        coefficients = new double[1];
        exp = new int[1];
        coefficients[0] = 0;
        exp[0] = 0;
        try{
            
            BufferedReader br = new BufferedReader(new FileReader(f));

            String s = br.readLine();
            String[] ele = s.split("\\+");
            for(String x: ele){
                System.out.println(x);
                String[] e2 = x.split("-");
                for(int i = 0;i<e2.length;i++){
                    if(e2[i].equals("")) continue;
                    String[] e3 = e2[i].split("x");
                    double coe = 1;
                    int expp = 1;
                    if(e3.length!=0){
                        if(e3[0]!="") coe = Double.parseDouble(e3[0]);
                        expp = 0;
                        if(e2[i].contains("x")){//constant
                            if(e3.length==2)
                                expp = Integer.parseInt(e3[1]);
                            else 
                                expp = 1;
                        }
                        
                    }
                    if(i!=0){//negative
                        coe*=-1;
                    }
                    double[] C = new double[]{coe}; int[] E = new int[]{expp};
                    Polynomial p = new Polynomial(C,E);
                    
                    Polynomial sum =  this.add(p);
                    this.coefficients = new double[sum.coefficients.length];
                    this.exp = new int[sum.coefficients.length];
                    for(int j = 0;j<sum.coefficients.length;j++){
                        this.coefficients[j] = sum.coefficients[j];
                        this.exp[j] = sum.exp[j];
                    }
                }
            }
            br.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found: ");
        }
    }

    Polynomial add (Polynomial p){
        HashMap<Integer,Double> s = new HashMap<Integer, Double>();
        
        for(int i = 0;i<coefficients.length;i++){
            if(s.containsKey(exp[i])){
                s.put(exp[i], s.get(exp[i])+ coefficients[i]);
            }else{
                s.put(exp[i], coefficients[i]);
            }
        }

        for(int i = 0;i<p.coefficients.length;i++){
            if(s.containsKey(p.exp[i])){
                s.put(p.exp[i], s.get(p.exp[i])+ p.coefficients[i]);
            }else{
                s.put(p.exp[i], p.coefficients[i]);
            }
        }
        int sz = 0;
        for(Map.Entry<Integer, Double> x: s.entrySet()){
            if(Math.abs(x.getValue())>0.0001){
                sz++;
            }
        }

        double[] c = new double[sz];
        int[] e = new int[sz];
        int i = 0;
        for(Map.Entry<Integer, Double> x: s.entrySet()){
            if(Math.abs(x.getValue())<0.0001) continue;
            c[i] = x.getValue();
            e[i] = x.getKey();
            i++;
        }
        Polynomial ap = new Polynomial(c, e);
        return ap;
    }

    Polynomial multiply (Polynomial p){
        HashMap<Integer,Double> s = new HashMap<Integer, Double>();
        
        for(int i = 0;i<coefficients.length;i++){
            for(int j = 0;j<p.coefficients.length;j++){
                if(s.containsKey(exp[i]+p.exp[j])){
                    s.put(exp[i]+p.exp[j], s.get(exp[i]+p.exp[j])+ coefficients[i]*p.coefficients[j]);
                }else{
                    s.put(exp[i]+p.exp[j], coefficients[i]*p.coefficients[j]);
                }
            }
        }
        
        int sz = 0;
        for(Map.Entry<Integer, Double> x: s.entrySet()){
            if(Math.abs(x.getValue())>0.0001){
                sz++;
            }
        }
        double[] c = new double[sz];
        int[] e = new int[sz];
        int i = 0;
        
        for(Map.Entry<Integer, Double> x: s.entrySet()){
            if(Math.abs(x.getValue())<0.0001) continue;
            c[i] = x.getValue();
            e[i] = x.getKey();
            i++;
        }
        Polynomial ap = new Polynomial(c, e);
        return ap;
    }

    double evaluate(double x){
        double ans = 0;
        for(int i = 0;i<coefficients.length;i++){
            ans+=Math.pow(x, exp[i])*coefficients[i];
        }
        return ans;
    }

    boolean hasRoot(double x){
        if(Math.abs(evaluate(x))<0.0001){
            return true;
        }
        return false;
    }

    public void saveToFile(String s) throws IOException{
        File f = new File(s);
        FileWriter fw = new FileWriter(f);
        String w = "";

        for(int i = 0;i<coefficients.length;i++){
            w+=(coefficients[i]>0?"+":"")+coefficients[i]+"x"+exp[i];
        }
        fw.write(w);
        fw.close(); 
    }

    public String toString(){
        String a = "f(x) = ";
        for(int i = 0;i<this.coefficients.length;i++){
            a+=coefficients[i]+"x"+exp[i] + " ";
        }
        return a;
    }
    
}