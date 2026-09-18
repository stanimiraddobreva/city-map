package model;

public class Street {
    private String to;
    private int length;

    public Street(String to, int length) {
        setTo(to);
        setLength(length);
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        if (to == null || to.isBlank()) {
            throw new IllegalArgumentException("Името на кръстовището не може да бъде празно.");
        }
        this.to = to;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length of the street must not be a negative number");
        }
        this.length = length;
    }

    @Override
    public String toString() {
        return to + "(" + length + ")";
    }
}
