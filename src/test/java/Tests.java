import chatBot.JavaBot;
import org.junit.Assert;
import org.junit.Test;
import chatBot.Logic;
import chatBot.Jokes;


import java.util.ArrayList;
import java.util.HashMap;

public class Tests{

    @Test
    public void testJokesClass(){
        ArrayList<String> jokes = Jokes.jokesBase();
        Assert.assertTrue(jokes.size() == 5);
    }

    @Test
    public void testGetHelp(){
        String com = "/help";
        Assert.assertEquals(Logic.getHelp(), "Хочешь супер смешную математическую шутку - пиши: /joke");
    }
}