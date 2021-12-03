package ss.week3.password;

public class RandomChecker {
    private Checker checker;
    private String password;
    private int length;

    public static void main(String[] args) {
        RandomChecker checker = new RandomChecker(new BasicChecker(), 10);
        checker.genPwd();
        System.out.println(checker.getPassword());
    }

    public RandomChecker(Checker checker, int length) {
        this.checker = checker;
        this.length = length;
    }

    public boolean genPwd() {
        Random random = new Random(this.length);
        String output = "";
        while(!checker.acceptable(output)) {
            output = random.randomString();
        }
        this.password = output;
        return true;
    }

    public String getPassword() {
        return this.password;
    }
}