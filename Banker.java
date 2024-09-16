class Account{
    private int balance = 0;

    public synchronized void deposit(int amount){
        if(amount > 0){
            balance +=amount;
            System.out.println("Deposited:$"+amount+", New Balance:$"+balance);
        }
    }
    public synchronized void withdraw(int amount){
        if(amount<=balance){
            balance-=amount;
            System.out.println("withdrawn:$"+amount+",New Balanece:$" + balance);
        }
        else if(amount > balance){
            System.out.println("Insufficient balance");
        }
    }

    public int getBalance(){
        return balance;
    }
}

class DepositThread extends Thread{
    private Account account;
    private int amount;

    public DepositThread(Account account , int amount){
        this.account = account;
        this.amount = amount;

       

    }
   
    public void run(){
        for(int i=0;i<5;i++){
            account.deposit(amount);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}

class withdrawThread extends Thread{
    private Account account;
    private int amount;
     
    public withdrawThread(Account account , int amount){
        this.account = account;
        this.amount = amount;
    }
   
    public void run(){
        for(int i=0;i<5;i++){
            account.withdraw(amount);
            try {
                Thread.sleep(150);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

}


public class Banker {
    public static void main(String[] args){
        Account account = new Account();

        DepositThread depositThread1 = new DepositThread(account, 100);
        DepositThread depositThread2 = new DepositThread(account, 200);

        withdrawThread withdrawThread1 = new withdrawThread(account, 50);
        withdrawThread withdrawThread2 = new withdrawThread(account, 150);

        depositThread1.start();
        depositThread2.start();
        withdrawThread1.start();
        withdrawThread2.start();

        try {
            depositThread1.join();
            depositThread2.join();
            withdrawThread1.join();
            withdrawThread2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Final Balance: $" + account.getBalance());
    

    }
}
