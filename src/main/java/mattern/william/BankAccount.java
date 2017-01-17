package mattern.william;

import java.math.BigDecimal;

/**
 * Created by williammattern on 1/17/17.
 */
public class BankAccount {
    private ODPStatus odpStatus;
    private String[] TransactionRecord;
    private AccountStatus accountStatus;
    private double balance;
    private AccountType accountType;
    private String accountNumber;
    private String accountHolderName;
    private double interestRate;

    public BankAccount(String accountNumber,AccountType accountType, String accountHolderName){
        //new accounts require at least these 3 items
        // if accountNumber is already taken, deny account opening
    }
    public void setAccountType(AccountType accountType){
        this.accountType = accountType;
    }

    public void changeAccountHolderName(String accountHolderName){
        if (this.accountStatus == AccountStatus.OPEN) {
            this.accountHolderName = accountHolderName;
        } else {
            System.out.println("You cannot change the account holder name on this account");
        }
    }

    public boolean setAccountStatus(AccountStatus accountStatus){
        if (accountStatus == AccountStatus.CLOSED && balance != 0){
            return false;
        } else {
            this.accountStatus = accountStatus;
            return true;
        }
    }

    public String getBalance(){
        if (accountStatus == AccountStatus.FROZEN){
            return "OFAC status = Frozen, access denied";
        } else {
            return Double.toString(balance);
        }
    }

    public boolean creditAccount(double creditAmount){
        if (accountStatus == AccountStatus.OPEN){
            this.balance += creditAmount;
            return true;
        } else {
            return false;
        }
    }

    public boolean debitAccount(double debitAmount){
        if ( accountStatus == AccountStatus.OPEN){
            if (this.odpStatus == ODPStatus.ON){
                if (this.balance < debitAmount){
                    return false;
                } else {
                    this.balance -= debitAmount;
                    return true;
                }
            } else {
                this.balance -= debitAmount;
                return true;
            }
        } else {
            return false;
        }
    }
}
