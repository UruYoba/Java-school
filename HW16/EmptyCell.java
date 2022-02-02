package HW16;

public class EmptyCell extends Cell{
    public EmptyCell() {
        super();
    }

    @Override
    public boolean tryToSurvive(String action){
        return action.equals(" ") | action.equals("w");
    }

}
