import chatBot.Dialog;
import chatBot.JavaBot;
import org.junit.Assert;
import org.junit.Test;
import chatBot.Logic;
import chatBot.Jokes;


import java.util.ArrayList;
import java.util.HashMap;

public class Tests{
    @Test
    public void testGetRandomNum(){
        Integer randInt = JavaBot.getRandomNum(1, 5);
        Assert.assertTrue(randInt <= 5 && randInt >= 1);
        Assert.assertFalse(randInt > 22);
    }

    @Test
    public void testFillingDictionary(){
        HashMap<Integer, String> dictActual = new HashMap<Integer, String>();
        HashMap<Integer, String> dictExpected = new HashMap<Integer, String>();
        dictExpected.put(1, "a");
        dictExpected.put(2, "b");
        dictExpected.put(3, "c");
        ArrayList<String> data = new ArrayList<String>();
        data.add("1a");
        data.add("2b");
        data.add("3c");
        Assert.assertEquals(JavaBot.fillDictionary(dictActual, data), dictExpected);
    }

    @Test
    public void testGetJoke(){
        String actualJoke = JavaBot.getJoke(false);
        String expectedJoke = Jokes.jokesBase().get(0).substring(1);
        Assert.assertTrue(actualJoke.equals(expectedJoke));
    }

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