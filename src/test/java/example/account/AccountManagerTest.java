package example.account;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
public class AccountManagerTest {
    Customer customer=new Customer();
    AccountManager accountManager=new AccountManagerImpl();

    @Test
    void givenValidAmount_whenWithdraw_thenSucceed(){
        customer.setBalance(1000);
        assertThat(accountManager.withdraw(customer,700))
                .isEqualTo("success");
    }

    @Test
    void givenAmountExceedsBalanceAndNotCreditAllowed_whenWithdraw_thenSucceed(){
        customer.setBalance(1000);
        assertThat(accountManager.withdraw(customer,1300))
                .isEqualTo("insufficient account balance");
    }
    @Test
    void givenAmountExceedsBalanceAndIsCreditAllowed_whenWithdraw_thenSucceed(){
        customer.setBalance(1000);
        customer.setCreditAllowed(true);
        assertThat(accountManager.withdraw(customer,1300))
                .isEqualTo("success");
    }
    @Test
    void givenAmountExceedsCreditLimitAndNotVIP_whenWithdraw_thenSucceed(){
        customer.setBalance(1000);
        customer.setCreditAllowed(true);
        assertThat(accountManager.withdraw(customer,3000))
                .isEqualTo("maximum credit exceeded");
    }
    @Test
    void givenAmountExceedsCreditLimitAndIsVIP_whenWithdraw_thenSucceed(){
        customer.setBalance(1000);
        customer.setCreditAllowed(true);
        customer.setVip(true);
        assertThat(accountManager.withdraw(customer,3000))
                .isEqualTo("success");
    }
    @Test
    void givenPositiveAmount_whenDeposit_thenBalanceIncrease(){
        customer.setBalance(1000);

        accountManager.deposit(customer,1000);

        assertThat(customer.getBalance()).isEqualTo(2000);
    }
    @Test
    void givenNegativeAmount_whenDeposit_thenBalanceRemainUnchanged(){
        customer.setBalance(1000);

        accountManager.deposit(customer,-1000);

        assertThat(customer.getBalance()).isEqualTo(1000);
    }
    @Test
    void givenMultiPleAMounts_WhenWithdrawAndDeposit_ThenPass(){
        customer.setBalance(1000);
        customer.setVip(true);

        System.out.println(customer.getBalance());
        accountManager.withdraw(customer,1000);
        accountManager.withdraw(customer,900);
        assertThat(customer.getBalance()).isEqualTo(-900);
    }
}
