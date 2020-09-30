package Bank;

public class Bank {

    public static void main(String[] args) {
        DataBase acc = new DataBase(args[1]);
        UserHandler menu1 = new UserHandler();
        Account account1 = new Account();
        menu1.menu(account1, acc);
    }
}