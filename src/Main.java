import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Enter the probability of success (π): ");
        Scanner scanner = new Scanner(System.in);
        double probabilityOfSuccess = Double.valueOf(scanner.next());
        System.out.println("Enter the number of trials (n): ");
        long numberOfTrials = Integer.valueOf(scanner.next());
        System.out.println("Enter the number of successes (x): ");
        long numberOfSuccesses = Integer.valueOf(scanner.next());

        // calculate the combinations
        // c(n,x) = n! / (x!(n-x)!)
        double combination = combination(numberOfTrials, numberOfSuccesses);
        // calculate the binomial probability
        // (1-π^n-x)
        double d = 1 - probabilityOfSuccess;
        double e = Math.pow(probabilityOfSuccess,numberOfSuccesses);
        long f = numberOfTrials - numberOfSuccesses;
        double binomial = combination * e * Math.pow(d,f);

        scanner.close();

        /*System.out.println("π: " + probabilityOfSuccess);
        System.out.println("n: " + numberOfTrials);
        System.out.println("x: " + numberOfSuccesses);
        System.out.println("---------------");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println("d: " + d);
        System.out.println("---------------");*/
        System.out.println("Comparison: " + combination);
        System.out.println("Binomial Probability: " + binomial);


    }

    private static double combination(long numberOfTrials, long numberOfSuccesses){
        long a = factorial(numberOfTrials);
        long b = factorial(numberOfSuccesses);
        long c = factorial(numberOfTrials-numberOfSuccesses);
        double result = a / (b * c);
        return result;
    }

    private static long factorial(long n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}
