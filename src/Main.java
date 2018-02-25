import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Enter the probability of success (π): ");
        Scanner scanner = new Scanner(System.in);
        double probabilityOfSuccess = Double.valueOf(scanner.next());
        System.out.println("Enter the number of trials (n): ");
        int numberOfTrials = Integer.valueOf(scanner.next());
        System.out.println("Enter the number of successes (x): ");
        int numberOfSuccesses = Integer.valueOf(scanner.next());

        // calculate the combinations
        // c(n,x) = n! / (x!(n-x)!)
        BigDecimal combination = new BigDecimal(combination(numberOfTrials, numberOfSuccesses));
        // calculate the binomial probability
        // (1-π^n-x)
        double d = 1 - probabilityOfSuccess;
        BigDecimal e = new BigDecimal(Math.pow(probabilityOfSuccess,numberOfSuccesses));
        double f = numberOfTrials - numberOfSuccesses;
        BigDecimal px = new BigDecimal(Math.pow(d,f));
        BigDecimal binomial = combination.multiply(e).multiply(px);

        scanner.close();

        System.out.println("π: " + probabilityOfSuccess);
        System.out.println("n: " + numberOfTrials);
        System.out.println("x: " + numberOfSuccesses);
        System.out.println("---------------");
        System.out.println("d: " + d);
        System.out.println("e: " + e);
        System.out.println("f: " + f);
        System.out.println("------------------------------");
        System.out.println("Combination: " + combination);
        System.out.println("P(x): " + px);
        System.out.println("Standard Deviation: " + distributionStandardDeviation(numberOfTrials, probabilityOfSuccess));
        System.out.println("Mean: " + distributionMean(numberOfTrials, probabilityOfSuccess));
        System.out.println("Binomial Probability: " + binomial);
        System.out.println("------------------------------");

    }

    private static double combination(int numberOfTrials, int numberOfSuccesses){
        double a = factorial(numberOfTrials);
        double b = factorial(numberOfSuccesses);
        double c = factorial(numberOfTrials-numberOfSuccesses);
        return a / (b * c);
    }

    private static BigDecimal distributionStandardDeviation(int numberOfTrials, double probabilityOfSuccess){
        double probabilityOfFailure = 1 - probabilityOfSuccess;
        double x = numberOfTrials * probabilityOfSuccess * probabilityOfFailure;
        return new BigDecimal(Math.sqrt(x));
    }

    private static double distributionMean(int numberOfTrials, double probabilityOfSuccess){
        return numberOfTrials*probabilityOfSuccess;
    }

    private static double factorial(double n) {
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}
