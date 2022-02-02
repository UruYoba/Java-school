package HW16;

public class BombCell extends Cell{
    public BombCell() {
        super();
    }

    @Override
    public boolean tryToSurvive(String action){
        return action.equals(" ");
    }
}
