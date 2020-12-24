package ru.job4j.io.socket;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private final static String OUT = "end";
    private final static String STOP = "stop";
    private final static String CONTINUE = "continue";
    private final String path;
    private final String botAnswers;
    private final List<String> readFile = new ArrayList<>();
    private final List<String> dialog = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean run = true;
        readFile();
        Scanner in = new Scanner(System.in);
        String hello = "Welcome in our chat! You can start communicating with chat bot";
        System.out.println(hello);
        dialog.add(hello + System.lineSeparator());
        while (run) {
            String user = in.nextLine();
            String bot;
            if (user.trim().toLowerCase().equals(OUT)) {
                bot = "Program exit";
                System.out.println(bot);
                in.close();
                run = false;
                addInDialog(user, bot);
            } else if (user.trim().toLowerCase().equals(STOP)) {
                bot = "Bot went on a break) Wait please";
                System.out.println(bot);
                addInDialog(user, bot);
                while (!user.trim().toLowerCase().equals(CONTINUE)) {
                    user = in.nextLine();
                    if (!user.equals(CONTINUE)) {
                        addInDialog(user);
                    }
                }
                bot = "Bot is back with you";
                System.out.println(bot);
                addInDialog(user, bot);
            } else {
                bot = getAnswerBot();
                System.out.println(bot);
                addInDialog(user, bot);
            }
        }
       writeDialog();
    }

    private void addInDialog(String user, String bot) {
        dialog.add(String.format("User: %s", user)
                + System.lineSeparator()
                + String.format("Bot: %s", bot)
                + System.lineSeparator()
        );
    }

    private void addInDialog(String user) {
        dialog.add(String.format("User: %s", user)
                + System.lineSeparator()
        );
    }

    private void writeDialog() {
        try (BufferedWriter write = new BufferedWriter(
                new FileWriter(path, StandardCharsets.UTF_8))) {
            for (String line : dialog) {
                write.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        try (BufferedReader read = new BufferedReader(
                new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            read.lines().forEach(readFile::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getAnswerBot() {
        return readFile.get((int) (Math.random() * readFile.size()));
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("log.txt", "answersbot.txt");
        cc.run();
    }
}
