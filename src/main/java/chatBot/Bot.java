package chatBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {

            StringTokenizer tokenizer = new StringTokenizer(message.getText());
            String firstArg = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : ""; //штука которая даст нам первое слово из запроса
            switch (firstArg) {
                case "/help":
                case "помощь":
                    sendMsg(message, "Хочешь супер смешную математическую шутку - нажимай кнопку или пиши: /joke\n Чтобы добавить шутку - пиши /add *шутка*");
                    break;
                case "/joke":
                case "шутка":
                    sendMsg(message, JavaBot.getRandomJoke(JavaBot.jokes));
                    break;
                case "/add":
                case "добавить":
                    JavaBot.addJoke(message.getText().replace(firstArg, ""));
                    var s = message.getText().replace(firstArg, "");
                    sendMsg(message, message.getText());
                    sendMsg(message, s);
                    sendMsg(message, "Шутка добавлена!!!");
                    break;
                case "/see":
                case "посмотреть":
                    sendMsg(message, JavaBot.getAllJokes().toString());
                    break;

                default:
                    sendMsg(message, "Если хочешь получить супер смешную математическую шутку - нажимай кнопку или пиши: /joke\nЕсли требуется помощь - пиши: /help");
            }
        }
    }

    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboarrdFirstRow = new KeyboardRow();
        keyboarrdFirstRow.add(new KeyboardButton("помощь"));
        keyboarrdFirstRow.add(new KeyboardButton("шутка"));
        keyboarrdFirstRow.add(new KeyboardButton("посмотреть"));

        keyboardRowList.add(keyboarrdFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }

    public String getBotUsername() {
        return "manekBot2";
    }

    public String getBotToken() {
        return "1007634060:AAGRhtBR65vHAc9h2dHTHKjwDYQNLEPO3Z4";
    }
}