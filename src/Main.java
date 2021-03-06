import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the type of probability a) binomial b) hypergeometric: ");
        String type = scanner.nextLine();

        if ( type.equals("a")){
            System.out.println("Enter the probability of success (π): ");
            double probabilityOfSuccess = Double.valueOf(scanner.next());
            System.out.println("Enter the number of trials (n): ");
            int numberOfTrials = Integer.valueOf(scanner.next());
            System.out.println("Enter the number of successes (x): ");
            int numberOfSuccesses = Integer.valueOf(scanner.next());

            scanner.close();

            final Object[][] table = new String[numberOfSuccesses + 2][];
            table[0] = new String[]{"#", "Combination", "π^x", "(1-π^n-x)", "Binomial Prob."};
            BigDecimal sumBinomial = new BigDecimal(0);
            for (int i = 0; i < numberOfSuccesses + 1; i++) {
                // calculate the combinations
                // c(n,x) = n! / (x!(n-x)!)
                BigDecimal combination = new BigDecimal(combination(numberOfTrials, i));
                // calculate the binomial probability
                // (1-π^n-x)
                double d = 1 - probabilityOfSuccess;
                BigDecimal e = new BigDecimal(Math.pow(probabilityOfSuccess, i));
                double f = numberOfTrials - i;
                BigDecimal px = new BigDecimal(Math.pow(d, f));
                BigDecimal binomial = combination.multiply(e).multiply(px);
                sumBinomial = sumBinomial.add(binomial);
                // convert the results to string
                String displayCombination = displayResult(String.valueOf(combination),12);
                String displayE = displayResult(String.valueOf(e),12);
                String displayPx = displayResult(String.valueOf(px),12);
                String displayBinomial = displayResult(String.valueOf(binomial),12);

                table[i + 1] = new String[]{
                        String.valueOf(i),
                        displayCombination,
                        displayE,
                        displayPx,
                        displayBinomial
                };
            }
            String displaySD = displayResult(
                    String.valueOf(distributionStandardDeviation(numberOfTrials, probabilityOfSuccess)),
                    12);
            String displaySumBinomial = displayResult(String.valueOf(sumBinomial),12);

            System.out.println("------------------------------");
            for (final Object[] row : table) {
                System.out.format("%-15s%-15s%-15s%-15s%-15s\n", row);
            }
            System.out.println("------------------------------");
            System.out.println("Sum Binomial: " + displaySumBinomial);
            System.out.println("Standard Deviation: " + displaySD);
            System.out.println("Mean: " + distributionMean(numberOfTrials, probabilityOfSuccess));
        }else if(type.equals("b")){

            System.out.println("Enter the number of trials (N): ");
            int N = Integer.valueOf(scanner.next());
            System.out.println("Enter the number of successes (S): ");
            int S = Integer.valueOf(scanner.next());
            System.out.println("Enter the number of the sample (n): ");
            int n = Integer.valueOf(scanner.next());
            System.out.println("Enter the number of successes in the sample (x): ");
            int x = Integer.valueOf(scanner.next());

            scanner.close();

            BigDecimal a = new BigDecimal(combination(S,x));
            BigDecimal b = new BigDecimal(
                    combination(
                            N - S,
                            n - x)
            );
            BigDecimal c = new BigDecimal(combination(N,n));
            BigDecimal d = a.multiply(b);
            double e = d.doubleValue()/c.doubleValue();
            System.out.println("------------------------------");
            System.out.println("c1: " + a);
            System.out.println("c2: " + b);
            System.out.println("c3: " + c);
            System.out.println("------------------------------");
            System.out.println("(c1*c2)/c3");
            System.out.println(a + " x " + b + " / " + c + " = " + e);

        }

    }

    @SuppressWarnings("SameParameterValue")
    private static String displayResult(String r, int length){
        if (r.length() > length) {
            r = r.substring(0, 12);
        }
        return r;
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
