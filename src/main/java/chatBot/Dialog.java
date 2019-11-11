package chatBot;

import java.util.Scanner;

public class Dialog {
    public static void bot() {
        Scanner sc = new Scanner(System.in);
        String com = "";
        while (true) {
            System.out.println(Logic.repeatCommand());
            com = sc.nextLine();
            System.out.println(Logic.processCommand(com));
            bot();
        }
    }
    public static void getInfo(){
        System.out.println(Logic.getInfo());
    }
}