package model.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.game.SnakeContents.Snake;

public class SnakeToJson {

    private Snake snake;
    private ObjectMapper mapper;


    public SnakeToJson(Snake snake){

        this.snake = snake;
        mapper = new ObjectMapper();
    }

    public String parse() throws JsonProcessingException {
        String json = mapper.writeValueAsString(snake);
        return json;
    }

}
