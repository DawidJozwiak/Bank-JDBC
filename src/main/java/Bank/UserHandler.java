package Bank;

import java.util.Random;
import java.util.Scanner;

class UserHandler {
    /* Function that creates first menu look and provides 3 options for user
    It has to take Account object since it can be called again by other Menu functions     */
    public void menu(Account account1, DataBase acc) {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
        //takes user input
        Scanner myObj = new Scanner(System.in);
        int choice = myObj.nextInt();
        //switch-case according to input
        switch (choice) {
            case 1:
                //call method responsible for creating account
                this.createAccount(account1, acc);
                break;
            case 2:
                //call method responsible for logging in
                this.logIntoAccount(account1, acc);
                break;
            case 0:
                //exit console
                System.exit(0);
            default:
                //exception for illegal value
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    private String generateCreditNumber() {
        int[] arr;
        arr = new int[15];
        arr[0] = 4;
        arr[1] = 0;
        arr[2] = 0;
        arr[3] = 0;
        arr[4] = 0;
        arr[5] = 0;
        for (int j = 6; j < arr.length; j++) {
            Random random1 = new Random();
            arr[j] = random1.nextInt(9);
        }
        int[] aux;
        aux = new int[15];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                aux[i] = arr[i] * 2;
                if (aux[i] > 9) {
                    aux[i] -= 9;
                }
            } else {
                aux[i] = arr[i];
            }
            sum += aux[i];
        }
        int controlsum = ((10 - sum % 10) % 10);
        StringBuilder str = new StringBuilder();
        for (int i : arr) {
            str.append(i);
        }
        str.append(controlsum);
        return str.toString();
    }

    private void createAccount(Account account1, DataBase acc) {
        Random random = new Random();
        String first = String.format("%04d", random.nextInt(1001));
        account1.setcreditnumber(generateCreditNumber());
        account1.setPIN(first);
        account1.setBalance(0);
        acc.insertData(account1.getCreditnumber(), account1.getPIN());
        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        System.out.println(account1.getCreditnumber());
        System.out.println("Your card PIN:");
        System.out.println(account1.getPIN());
        this.menu(account1, acc);
    }

    private void logIntoAccount(Account account1, DataBase acc) {
        System.out.println("Enter your card number");
        Scanner myObj1 = new Scanner(System.in);
        String num = myObj1.nextLine();
        String pin = myObj1.nextLine();
        if (num.equals(account1.getCreditnumber()) && pin.equals(account1.getPIN())) {
            System.out.println("You have successfully logged in\n");
            this.userLogged(account1, acc);
        } else
            System.out.println("Wrong card number");
        this.menu(account1, acc);
    }

    public void userLogged(Account account1, DataBase acc) {
        System.out.println("1. Balance");
        System.out.println("2. Add Income");
        System.out.println("3. Transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
        Scanner myObj3 = new Scanner(System.in);
        int choice1 = myObj3.nextInt();
        switch (choice1) {
            case 1:
                System.out.println("Balance: " + account1.getBalance() + "\n");
                userLogged(account1, acc);
                break;
            case 2:
                System.out.println("You have successfully logged out!");
                this.menu(account1, acc);
                break;
            case 0:
                System.out.println("Bye!");
                System.exit(0);
                break;
        }
    }
}