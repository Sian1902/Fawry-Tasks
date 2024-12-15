package example.store;

import example.account.AccountManager;
import example.account.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.stubbing.Answer;

import static org.assertj.core.api.Assertions.*;

public class StoreTest {
    AccountManager accountManager= Mockito.mock(AccountManager.class);
    Customer customer=new Customer();
    Product product = new Product();
    Store store=new StoreImpl(accountManager);
    @Test
    void giveProductNumberExceedsQuantity_whenBuy_ThenFail(){
        Mockito.verifyZeroInteractions(accountManager);
     assertThatThrownBy(()-> store.buy(product,customer)).
             isInstanceOf(RuntimeException.class)
             .hasMessage("Product out of stock");
    }
    @Test
    void givenValidProductAmount_whenBuy_thenPass(){
        product.setQuantity(5);
        product.setPrice(200);
        Mockito.when(accountManager.withdraw(Matchers.any(),Matchers.anyInt()))
                .thenReturn("success");
        store.buy(product,customer);
        assertThat(product.getQuantity()).isEqualTo(4);
    }
    @Test
    void givenValidProductAmountButExceedsCustomerBalance_whenBuy_thenPass(){
        product.setQuantity(5);
        product.setPrice(200);
        Mockito.when(accountManager.withdraw(Matchers.any(),Matchers.anyInt()))
                .thenReturn("insufficient account balance");
        assertThatThrownBy(()-> store.buy(product,customer))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Payment failure: insufficient account balance");
        assertThat(product.getQuantity()).isEqualTo(5);
    }

}
