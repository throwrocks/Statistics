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
            String displayCombination = String.valueOf(combination);
            if (displayCombination.length() > 12) {
                displayCombination = displayCombination.substring(0, 12);
            }

            String displayE = String.valueOf(e);
            if (displayE.length() > 12) {
                displayE = displayE.substring(0, 12);
            }

            String displayPx = String.valueOf(px);
            if (displayPx.length() > 12) {
                displayPx = displayPx.substring(0, 12);
            }

            String displayBinomial = String.valueOf(binomial);
            if (displayBinomial.length() > 12) {
                displayBinomial = displayBinomial.substring(0, 12);
            }

            table[i + 1] = new String[]{
                    String.valueOf(i),
                    displayCombination,
                    displayE,
                    displayPx,
                    displayBinomial
            };


        }

        System.out.println("------------------------------");
        for (final Object[] row : table) {
            System.out.format("%-15s%-15s%-15s%-15s%-15s\n", row);
        }
        System.out.println("------------------------------");

        String displaySD = String.valueOf(distributionStandardDeviation(numberOfTrials, probabilityOfSuccess));
        if ( displaySD.length() > 12){
            displaySD = displaySD.substring(0,12);
        }

        String displaySumBinomial = String.valueOf(sumBinomial);
        if ( displaySumBinomial.length() > 12){
            displaySumBinomial = displaySumBinomial.substring(0,12);
        }

        System.out.println("Sum Binomial: " + displaySumBinomial);
        System.out.println("Standard Deviation: " + displaySD);
        System.out.println("Mean: " + distributionMean(numberOfTrials, probabilityOfSuccess));

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
