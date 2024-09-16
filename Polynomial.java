public class Polynomial{
    double [] coefficients;
    
    public Polynomial(){
        coefficients = new double[1];
        coefficients[0] = 0;
    }

    public Polynomial(double[] coefficients){
        this.coefficients = new double[coefficients.length];
        for(int i = 0;i<coefficients.length;i++){
            this.coefficients[i] = coefficients[i];
        }
    }

    Polynomial add (Polynomial p){
        int mx = Math.max(p.coefficients.length, coefficients.length);
        double[] ans  = new double[mx];

        for(int i = 0;i<mx;i++){
            ans[i] = 0;
            if(i<p.coefficients.length)ans[i]+=p.coefficients[i];
            if(i<coefficients.length)ans[i]+=coefficients[i];
        }
        Polynomial ap = new Polynomial(ans);
        return ap;
    }

    double evaluate(double x){
        double cur = 1;
        double ans = 0;
        for(int i = 0;i<coefficients.length;i++){
            ans+=cur*coefficients[i];
            cur*=x;
        }
        return ans;
    }

    boolean hasRoot(double x){
        if(Math.abs(evaluate(x))<0.0001){
            return true;
        }
        return false;
    }
}