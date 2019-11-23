package chatBot;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JavaBot{
    public static File file = new File(System.getProperty("user.dir") + "/src/main/resources/jokes.txt");
    public static int getRandomNum(int end) {
        return 1 + (int) (Math.random() * end);
    }
    public  static Map<Integer, String> jokes
            = fillDict(file);
    public static void addJoke(String joke){

        jokes.put(jokes.size() + 1, joke);
    }

    public static Map<Integer, String> fillDict(File file){
        var iter = 1;
        HashMap<Integer, String> dict = new HashMap<>();
        try(Scanner fileContent = new Scanner(file)) {
            while (fileContent.hasNextLine()) {
                dict.put(iter, fileContent.nextLine());
                iter++;
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return dict;
    }

    public static String getRandomJoke(Map<Integer, String> jokes) {
        return jokes.get(getRandomNum(jokes.size()));
    }

    public static void main(String[] args) {
        System.out.println(getRandomJoke(JavaBot.jokes));
    }

    public static Map<Integer, String> getAllJokes(){
        return jokes;
    }
}