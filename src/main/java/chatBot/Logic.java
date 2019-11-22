package chatBot;

import chatBot.JavaBot;

public class Logic {
    public static String getInfo(){
        return("Привет!\nЕсли хочешь получить супер смешную математическую шутку - нажимай кнопку или пиши: /joke\nЕсли требуется помощь - пиши: /help");
    }

    public static String processCommand(String command){
        if (command.equals("/joke")) {
            return(JavaBot.getRandomJoke(JavaBot.jokes));
        }
        else {
            return(getHelp());
        }

    }
    public static String getHelp(){
        return("Хочешь супер смешную математическую шутку - нажимай кнопку или пиши: /joke");
    }
}