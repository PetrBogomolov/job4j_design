package ru.job4j.ood.dip;

import java.util.TimeZone;
/*
 * PhoneMenu хранит ссылку на реализацию конкретного интерфейса, что ограничевает его функционал
 * Необходимо поле private final Email email = new Email(); заменить на
 * private final Send send;
 * Определения реализации возложить на конструктор класса.
 * Получим класс, который может пользоваться всеми реализациями интерфейса Send;
 */
public class PhoneMenu {

    private final Email email = new Email();

    public void sendMessage(Message message) {
        email.sendMessage(message);
    }

    /*
     * модель данных сообщения
     */
    class Message {
        private String text;
        private TimeZone time;

        public Message(String text) {
            this.text = text;
        }
    }

    /*
     * интрефейс отправки сообщения
     */
    interface Send {
        void sendMessage(Message message);
    }

    /*
     * реализация интрефейса Send
     */
    class Email implements Send {
        @Override
        public void sendMessage(Message message) {

        }
    }

    /*
     * реализация интрефейса Send
     */
    class SMS implements Send {
        @Override
        public void sendMessage(Message message) {

        }
    }

    /*
     * реализация интрефейса Send
     */
    class Telegram implements Send {
        @Override
        public void sendMessage(Message message) {

        }
    }
}
