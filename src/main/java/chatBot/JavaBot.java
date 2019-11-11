package chatBot;

import java.util.*;


public class JavaBot {

    public static int getRandomNum(int start, int end) {
        return start + (int) (Math.random() * end);
    }

    public static HashMap<Integer, String> fillDictionary(HashMap<Integer, String> dict, ArrayList<String> data){
        if (dict.size() == 0){
            for (String item: data) {

                dict.put(Character.getNumericValue(item.charAt(0)), item.substring(1));
            }
        }
        return dict;
    }

    public static String getJoke(boolean isRandom) {
        HashMap<Integer, String> numberJoke = new HashMap<Integer, String>();
        ArrayList<String> lines = Jokes.jokesBase();
        numberJoke = fillDictionary(numberJoke, lines);
        if (isRandom){
            return numberJoke.get(getRandomNum(1, Jokes.jokesBase().size()));
        }
        else{
            return numberJoke.get(1);
        }
    }

}