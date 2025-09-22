package account;

public class AccountProgram {
    public static void main(String[] args) {
        Account account = new Account("234-5678-90-12", "Andrés Chacua", 0);
        Account account1 = new Account("237-4578-71-11", "Miguel Tovar", 0);

        account.credit(1500000);
        System.out.println(account.getBalance());

        account.debit(500000);
        System.out.println("Saldo cuenta origen: " + account.getBalance());

        account.transferTo(account1, 300000);
        System.out.println("Saldo cuenta destino: " + account1.getBalance());
        System.out.println("Saldo cuenta origen: " + account.getBalance());

        System.out.println(account);
        System.out.println(account1);
    }
}
