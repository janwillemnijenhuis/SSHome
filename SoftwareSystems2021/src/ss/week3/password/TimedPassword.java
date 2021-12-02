package ss.week3.password;

import java.lang.System;
import java.util.concurrent.TimeUnit;

public class TimedPassword extends Password {
    private boolean isExpired = false;
    private long validTime;
    private long defaultTime = 10000;
    private long startTime;
    private long endTime;

    public static void main(String[] args) throws InterruptedException {
        TimedPassword timedPass = new TimedPassword();
        timedPass.setWord(timedPass.getInitPass(), "newpass");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(timedPass.testWord("newpass"));
        System.out.println(timedPass.testWord("wrongpass"));
        TimeUnit.SECONDS.sleep(9);

    }

    public TimedPassword() throws InterruptedException {
        super(new BasicChecker());
        this.validTime = defaultTime;
        this.startTime = System.currentTimeMillis();
        this.checkTime();
    }

    public TimedPassword(long validTime) throws InterruptedException {
        super(new BasicChecker());
        this.validTime = validTime;
        this.startTime = System.currentTimeMillis();
        this.checkTime();
    }


    public void checkTime() throws InterruptedException {
        while(true) {
            endTime = System.currentTimeMillis();
            if (Math.abs(this.endTime - this.startTime) > this.validTime) {
                isExpired = true;
                System.err.println("The password has expired.");
                System.exit(0);
            }
        }
    }

    public void returnError(boolean isExpired) {
        if(isExpired) {

            System.exit(0);
        }
    }

    @Override
    public boolean setWord(String oldpass, String newpass) {
        if(super.setWord(oldpass, newpass)) {
            this.startTime = System.currentTimeMillis();
            return true;
        }
        else {
            return false;
        }
    }
}