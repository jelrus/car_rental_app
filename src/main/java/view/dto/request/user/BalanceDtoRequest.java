package view.dto.request.user;

import view.dto.request.DtoRequest;

import java.math.BigDecimal;
import java.util.Objects;

public class BalanceDtoRequest extends DtoRequest {

    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceDtoRequest that = (BalanceDtoRequest) o;
        return Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance);
    }

    @Override
    public String toString() {
        return "BalanceDtoRequest{" +
                "balance=" + balance +
                '}';
    }
}