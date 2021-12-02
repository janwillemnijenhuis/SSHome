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
        System.out.println(timedPass.testWord("newpass"));
        TimeUnit.SECONDS.sleep(10);
        timedPass.testWord("newpass");


    }

    public TimedPassword() {
        super(new BasicChecker());
        this.validTime = defaultTime;
        this.startTime = System.currentTimeMillis();
    }

    public TimedPassword(long validTime) {
        super(new BasicChecker());
        this.validTime = validTime;
        this.startTime = System.currentTimeMillis();
    }


    public void checkTime() {
        endTime = System.currentTimeMillis();
        if (Math.abs(this.endTime - this.startTime) > this.validTime) {
            isExpired = true;
        }
    }

    public void printError() {
        if(this.isExpired) {
            System.err.println("The password has expired.");
        }
    }

    @Override
    public boolean setWord(String oldpass, String newpass) {
        this.checkTime();
        if(isExpired) {
            printError();
            return false;
        }
        return super.setWord(oldpass, newpass);
    }

    public boolean acceptable(String suggestion) {
        this.checkTime();
        if(isExpired) {
            printError();
            return false;
        }
        return super.acceptable(suggestion);
    }

    public boolean testWord(String test) {
        this.checkTime();
        if(isExpired) {
            printError();
            return false;
        }
        return super.testWord(test);
    }
}