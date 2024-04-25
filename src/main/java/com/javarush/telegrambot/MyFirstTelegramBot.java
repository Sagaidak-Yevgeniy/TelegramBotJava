package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static com.javarush.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "JavaCodeMastersPRObot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "7011631130:AAEHAiHpVjvscCRJpx85B2re1lkmQuSuKHo"; //TODO: добавьте токен бота в кавычках

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        // отобразить сообщения о начале игры - нужно взломать холодильник
        if (getMessageText().equals("/start")){
            setUserGlory(0);
            sendPhotoMessageAsync("step_1_pic");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", "step_1_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_1_btn")) {
           addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT,
                    Map.of("Взять сосиску! +20 славы", "step_2_btn",
                    "Взять рыбку! +20 славы", "step_2_btn",
                    "Скинуть банку с огурцами! +20славы", "step_2_btn"
            ));
        }

        // взламываем робот пылесос
        if (getCallbackQueryButtonKey().equals("step_2_btn")){
            addUserGlory(20);
            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота пылесоса", "step_3_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_3_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT,
                    Map.of("Отправить робот пылесос за едой! +30 славы", "step_4_btn",
                            "Покататься на роботе пылесосе! +30 славы", "step_4_btn",
                            "Убежать от робота пылесоса! +30славы", "step_4_btn"
                    ));
        }

        // взломать камеру Go-Pro
        if (getCallbackQueryButtonKey().equals("step_4_btn")){
            addUserGlory(30);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Включить и одеть камеру GoPro", "step_5_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_5_btn")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_6_pic");
            sendTextMessageAsync(STEP_6_TEXT,
                    Map.of("Бегать по крышам и снимать все подряд! +40 славы", "step_6_btn",
                            "Нападать на кошек из засады и снимать на видео! 40 славы", "step_6_btn",
                            "Нападать на собак из засады и снимать на видео! +40славы", "step_6_btn"
                    ));
        }

        // взламываем компьютер
        if (getCallbackQueryButtonKey().equals("step_6_btn")){
            addUserGlory(40);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Взлом компьютера", "step_7_btn"));
        }

        // хвастаемся другим котам
        if (getCallbackQueryButtonKey().equals("step_7_btn")){
            addUserGlory(50);
            sendPhotoMessageAsync("step_8_pic");
            sendTextMessageAsync(STEP_8_TEXT, Map.of("Выйти во двор похвастаться", "step_8_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_8_btn")) {
            addUserGlory(50);
            sendPhotoMessageAsync("final_pic");
            sendTextMessageAsync(FINAL_TEXT + "*Поздравляем Вы набрали* " + getUserGlory() + "* очков славы*");

        }

        // Команда для отображения результата
        if (getMessageText().equals("/glory")){

          sendTextMessageAsync("Сейчас у пользователя " + getUserGlory() + " очков славы");
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}