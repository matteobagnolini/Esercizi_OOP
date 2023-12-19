package a04.e1;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class BankAccountFactoryImpl implements BankAccountFactory {

    @Override
    public BankAccount createBasic() {
        return createWithFeeAndCredit(a -> 0, c -> false, r -> 0);
    }

    @Override
    public BankAccount createWithFee(UnaryOperator<Integer> feeFunction) {
        return createWithFeeAndCredit(feeFunction, c -> false, r -> 0);
    }

    @Override
    public BankAccount createWithCredit(Predicate<Integer> allowedCredit, UnaryOperator<Integer> rateFunction) {
        return createWithFeeAndCredit(a -> 0, allowedCredit, rateFunction);
    }

    @Override
    public BankAccount createWithBlock(BiPredicate<Integer, Integer> blockingPolicy) {
        return new BankAccount() {

            private boolean isBlocked = false;
            private int balance = 0;

            @Override
            public int getBalance() {
                return balance;
            }

            @Override
            public void deposit(int amount) {
                balance += amount;
            }

            @Override
            public boolean withdraw(int amount) {
                if(isBlocked) {
                    return false;
                }
                if(blockingPolicy.test(amount, balance)) {
                    isBlocked = true;
                    return false;
                }
                balance -= amount;
                return true;
            }
            
        };
    }

    @Override
    public BankAccount createWithFeeAndCredit(UnaryOperator<Integer> feeFunction, Predicate<Integer> allowedCredit,
            UnaryOperator<Integer> rateFunction) {
        return new BankAccount() {

            private int balance = 0;


            @Override
            public int getBalance() {
                return balance;
            }

            @Override
            public void deposit(int amount) {
                balance += amount;
            }

            @Override
            public boolean withdraw(int amount) {
                if(balance - amount - feeFunction.apply(amount) >= 0) {
                    balance = balance - amount - feeFunction.apply(amount);
                    return true;
                } else if(allowedCredit.test(- balance + amount + feeFunction.apply(amount))) {
                    balance = balance - amount - feeFunction.apply(amount) - rateFunction.apply(balance - amount - feeFunction.apply(amount));
                    return true;
                }
                return false;
            }
            
        };
    }

}
