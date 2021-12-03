package ss.week3;

public class Date implements Comparable {

    @Override
    public boolean greaterThan(Comparable other) {
        if(this.isComparableTo(other)) {
            Date testdate = (Date) other;
            return ((this.getYear() > testdate.getYear()) ||
                    (this.getMonth() > testdate.getMonth() && this.getYear() == testdate.getYear()) ||
                    (this.getDay() > testdate.getDay() && this.getMonth() == testdate.getMonth() && this.getYear() == testdate.getYear()));
        }
        return false;
    }

    @Override
    public boolean isComparableTo(Comparable other) {
        return (other != null && other instanceof Date);
    }

    /**
     * These can be implemented to actually create the date class
     */
    public int getDay() {
        return 0;
    }

    public int getMonth() {
        return 0;
    }

    public int getYear() {
        return 0;
    }
}