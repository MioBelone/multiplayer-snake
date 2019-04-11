package model.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.game.SnakeContents.Snake;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to parse either a list of foods or a list of snakes into a JSON string to be send to the server.
 *
 * @author Alexander Schleiter
 */
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

    //method to parse a list of snakes into a json string
    public String parseSnakes() throws JsonProcessingException {
        return mapper.writeValueAsString(snakes);
    }

    //method to parse a list of foods into a json string
    public String parseFoods() throws JsonProcessingException {
        return mapper.writeValueAsString(foods);
    }

}
