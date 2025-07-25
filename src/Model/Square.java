package Model;

public class Square {

    private Integer content;
    private final int expectedContent;
    private final boolean fixed;

    public Square(int expectedContent, boolean fixed) {
        this.expectedContent = expectedContent;
        this.fixed = fixed;
        if (fixed){
            this.content = expectedContent;
        }
    }

    public int getExpectedContent() {
        return expectedContent;
    }

    public Integer getContent() {
        return content;
    }

    public void clearSquare() {
        setContent(null);
    }

    public void setContent(Integer content) {
        if (fixed) return;
        this.content = content;
    }

    public boolean isFixed() {
        return fixed;
    }

}
