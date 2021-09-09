import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

// 변화는 값에 돈의 환율과 같이 서버에서 받아오는 변동되는 값을 반영하려면 Mockito를 추가해 Mocking을 할 환경을 만든다

@ExtendWith(MockitoExtension.class)
public class DollarControllerTest {
    @Mock
    public MarketApi marketApi;

    // Test가 실행되기 이전에~
    @BeforeEach
    public void init(){
        // MarketApi.connect가 실행될 때 그 값을 3000으로 return하게 한다~ (값에 따라 다른 결과가 나오기에 logic이 맞는지만을 체크하기 위해서)
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    public void testHello(){
        System.out.println("hello");
    }

    @Test
    public void dollarTest(){
        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        System.out.println(calculator.sum(10, 10));

        Assertions.assertEquals(22000, calculator.sum(10, 10));
    }

    @Test
    public void mockTest(){
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        System.out.println(calculator.sum(10, 10));

        Assertions.assertEquals(60000, calculator.sum(10, 10));

    }
}
