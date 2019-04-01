package presenter;

import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class FoodPresenter {

    private List<Circle> foods = new ArrayList<>();

    public FoodPresenter(List<Circle> foods) {
        this.foods = foods;
    }

    public FoodPresenter() {
    }

    public List<Circle> getFoods() {
        return foods;
    }
}
