package chatBot;

import com.mongodb.client.*;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataBase {
    private static int getRandomNum(int end) {
        return 1 + (int) (Math.random() * end);
    }

    private static void fillCollection(File file){
        var iter = 1;
        try(Scanner fileContent = new Scanner(file)) {
            while (fileContent.hasNextLine()) {
                insertJoke(fileContent.nextLine());
                iter++;
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void insertJoke(String joke){
        try (MongoClient mongoClient = MongoClients.create()){
            var dataBase = mongoClient.getDatabase("jokesbase");
            var jokesCollection = dataBase.getCollection("jokes");
            var number = jokesCollection.countDocuments() + 1;
            if (!jokesCollection.find(new Document(Map.of("joke", joke))).iterator().hasNext())
                jokesCollection.insertOne(new Document(Map.of("number", number, "joke", joke)));
        }
    }
    /*static HashMap<Integer, String> getAllJokes(){
        try (MongoClient mongoClient = MongoClients.create()) {
            var dataBase = mongoClient.getDatabase("jokesbase");
            var jokesCollection = dataBase.getCollection("jokes");
            var jokes = new HashMap<Integer, String>();
            var i = 1;
            while (jokesCollection.find().iterator().hasNext()){
                jokes.put(i, jokesCollection.find().iterator().next().toString());
                i++;
            }
            return jokes;
        }
    }*/

    static String getRandomJoke() {
        try (MongoClient mongoClient = MongoClients.create()){
            var dataBase = mongoClient.getDatabase("jokesbase");
            var jokesCollection = dataBase.getCollection("jokes");
            var count = (int)jokesCollection.countDocuments();
            var randomNum = getRandomNum(count);
            try (MongoCursor<Document> cur = jokesCollection.find().iterator()) {
                while (cur.hasNext()) {
                    var doc = cur.next();
                    var joke = new ArrayList<>(doc.values());
                    if (joke.get(1).equals(randomNum)) return joke.get(2).toString();
                }
            }
        }
        return null;
    }
}
