package ss.week6.account;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	protected double balance = 0.0;
	private boolean inTransaction = false;
	private Lock lock = new ReentrantLock();

	public synchronized void transaction(double amount) {
		try {
			while (inTransaction) {
				wait();
			}
			while (this.getBalance() < -1000 && amount < 0) {
				wait();
			}
			this.inTransaction = true;
			balance = balance + amount;
			notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.inTransaction = false;
	}

	public synchronized double getBalance() {
		return balance;
	}
}
