package chatBot;

import chatBot.JavaBot;

public class Logic {
    public static String getInfo(){
        return("Привет!\nЕсли хочешь получить супер смешную математическую шутку - пиши: /joke\nЕсли требуется помощь - пиши: /help");
    }

    public static String processCommand(String command){
        if (command.equals("/joke")) {
            return(JavaBot.getJoke(true));
        }
        else {
            return(getHelp());
        }

    }
    public static String getHelp(){
        return("Хочешь супер смешную математическую шутку - пиши: /joke");
    }

    public static String repeatCommand(){
        return("Enter the command, friend:");
    }
}