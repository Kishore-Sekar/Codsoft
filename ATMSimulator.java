import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited: $%.2f\n", amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("Successfully withdrew: $%.2f\n", amount);
            return true;
        } else {
            if (amount <= 0) {
                System.out.println("Withdrawal amount must be positive.");
            } else {
                System.out.println("Insufficient balance for this withdrawal.");
            }
            return false;
        }
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Select an option (1-4): ");
    }

    public void checkBalance() {
        System.out.printf("Current Balance: $%.2f\n", account.getBalance());
    }

    public void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    public void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }
}

public class ATMSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(1000.00); // Initial balance of $1000
        ATM atm = new ATM(account);

        while (true) {
            atm.displayMenu();
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    atm.deposit();
                    break;
                case 3:
                    atm.withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return; // Exit the program
                default:
                    System.out.println("Invalid option! Please choose again.");
            }
        }
    }
}
