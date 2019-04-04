package model.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.game.SnakeContents.Snake;

import java.util.ArrayList;
import java.util.List;

public class ObjectToJson {

    private List<Snake> snakes;
    private ArrayList<Food> foods;
    private ObjectMapper mapper;


    public ObjectToJson(List<Snake> snakes){

        this.snakes = snakes;
        mapper = new ObjectMapper();
    }

    public ObjectToJson(ArrayList<Food> foods){

        this.foods = foods;
        mapper = new ObjectMapper();
    }

    public String parseSnakes() throws JsonProcessingException {
        return mapper.writeValueAsString(snakes);
    }

    public String parseFoods() throws JsonProcessingException {
        return mapper.writeValueAsString(foods);
    }

}
