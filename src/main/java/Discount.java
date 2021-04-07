public class Discount {
    private int platinum;
    private int diamond;

    public Discount() {
        this.platinum = 14;
        this.diamond = 7;
    }

    public Discount(int platinum, int diamond) {
        this.platinum = platinum;
        this.diamond = diamond;
    }

    public int getPlatinum() {
        return platinum;
    }

    public void setPlatinum(int platinum) {
        this.platinum = platinum;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }
}
