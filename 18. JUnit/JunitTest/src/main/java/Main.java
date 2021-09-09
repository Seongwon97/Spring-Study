public class Main {
    public static void main(String[] args) {
        System.out.println("Hello JUnit");

        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        System.out.println(calculator.sum(10, 10));
    }
}
