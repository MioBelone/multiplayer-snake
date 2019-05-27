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


    /**
     * Initialize the class with snakes.
     * @param snakes
     */
    public ObjectToJson(List<Snake> snakes){

        this.snakes = snakes;
        mapper = new ObjectMapper();
    }

    /**
     * Initialize the class with foods.
     * @param foods
     */
    public ObjectToJson(ArrayList<Food> foods){

        this.foods = foods;
        mapper = new ObjectMapper();
    }

    /**
     * Method to parse a list of snakes into a json string.
     * @return Json string containing all information about snakes.
     * @throws JsonProcessingException
     */
    public String parseSnakes() throws JsonProcessingException {
        return mapper.writeValueAsString(snakes);
    }

    /**
     * Method to parse a list of foods into a json string.
     * @return Json string containing all information about foods.
     * @throws JsonProcessingException
     */
    public String parseFoods() throws JsonProcessingException {
        return mapper.writeValueAsString(foods);
    }

}
