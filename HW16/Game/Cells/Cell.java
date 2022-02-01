package HW16.Game.Cells;

public class Cell {
    String defaultValue;
    String currentValue;
    private String flag;
    protected String value;
    int x;
    int y;
    boolean isDemined;

    public Cell(int x, int y, String value) {
        this.defaultValue = "■";
        this.currentValue = "■";
        this.value = value;
        this.x = x;
        this.y = y;
        flag = "\u2691";
        this.isDemined = false;
    }

    public String getValue(){
        return this.currentValue;
    }

    public Cell demine(){
        isDemined = !isDemined;
        if(isDemined){
            currentValue = flag;
        } else{
            currentValue = defaultValue;
        }
        return this;
    }

    public String click(){
        if(isDemined){
            this.currentValue = flag;
            return currentValue;
        }
        this.currentValue = value;
        return this.value;
    }

    protected void setValue(String newValue){
        this.value = newValue;
    }

    public String print(boolean isGameOver){
        if(isGameOver & isDemined){
            if(this instanceof EmptyCell){
                return "X|";
            }
            return "\u2713|";
        }
        if(isGameOver & this instanceof BombCell){
            return value + "|";
        }
        return toString();
    }

    @Override
    public String toString() {

        String kek = currentValue.equals("  ")? currentValue : currentValue + "|";
        return kek;
    }
}
