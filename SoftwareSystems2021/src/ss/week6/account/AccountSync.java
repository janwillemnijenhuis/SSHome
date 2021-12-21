package ss.week6.account;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AccountSync {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account();
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            int amount = i == 0 ? 100 : -100 ;
            es.execute(new Thread(new MyThread(amount, 10000000, account)));
        }
        es.shutdown();
        boolean finished = es.awaitTermination(1, TimeUnit.MINUTES);
        if (finished) {
            System.out.println(account.getBalance());
        }
    }
}
