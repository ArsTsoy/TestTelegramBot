import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
//        Message message = update.getMessage();
//        User user = message.getFrom();
//        String firstName = user.getFirstName();
//        String lastName = user.getLastName();
//
//        String answer = "Hello, " + firstName + " " + lastName;
//
//        SendMessage answerMessage = new SendMessage();
//        answerMessage.setChatId(message.getChatId());
//        answerMessage.setText(answer);
//
//        try {
//            execute(answerMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }

        Message message = update.getMessage();
        Long chatId = message.getChatId();
        if(message.hasText()) {
            sendText(chatId, message.getText());
        } else if(message.hasPhoto()) {
            sendPhoto(message, "Держи фотку");
        }
        System.out.println(update);
    }


    private void sendText(Long chatId, String answer) {
        SendMessage answerMessage = new SendMessage();
        answerMessage.setChatId(chatId);
        answerMessage.setText(answer);
        try {
            setupKeyboard(answerMessage);
            execute(answerMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPhoto(Message message, String answer) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId());

        List<PhotoSize> photos = new ArrayList(message.getPhoto());
        PhotoSize photoSize1 = photos.get(0);
        String fileId = photoSize1.getFileId();
        sendPhoto.setPhoto(fileId);
        sendPhoto.setCaption(answer);
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void setupKeyboard(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Кнопка 1"));
        keyboardRow1.add(new KeyboardButton("Кнопка 2"));

        rows.add(keyboardRow1);

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton("Кнопка 3"));
        keyboardRow2.add(new KeyboardButton("Кнопка 4"));

        rows.add(keyboardRow2);

        replyKeyboardMarkup.setKeyboard(rows);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    public String getBotUsername() {
        return "testArs3Bot";
    }

    public String getBotToken() {
        return "1392950121:AAEobIitmXsCskwFZL9r3N-DULP8gpBb1_U";
    }
}
