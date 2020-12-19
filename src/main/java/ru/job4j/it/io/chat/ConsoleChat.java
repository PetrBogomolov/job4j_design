package ru.job4j.it.io.chat;

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

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean run = true;
        readFile();
        Scanner in = new Scanner(System.in);
        try (BufferedWriter write = new BufferedWriter(
                new FileWriter(path, StandardCharsets.UTF_8))) {
            String hello = "Welcome in our chat! You can start communicating with chat bot";
            System.out.println(hello);
            while (run) {
                String text = in.nextLine();
                String answersBot;
                if (text.trim().toLowerCase().equals(OUT)) {
                    answersBot = "Program exit";
                    System.out.println(answersBot);
                    in.close();
                    run = false;
                } else if (text.trim().toLowerCase().equals(STOP)) {
                    answersBot = "Bot went on a break) Wait please";
                    System.out.println(answersBot);
                    while (!text.trim().toLowerCase().equals(CONTINUE)) {
                        text = in.nextLine();
                    }
                    answersBot = "Bot is back with you";
                    System.out.println(answersBot);
                    answersBot = "Input text";
                    System.out.println(answersBot);
                } else {
                    answersBot = getAnswerBot();
                    System.out.println(answersBot);
                }
                write.write(hello
                         + System.lineSeparator()
                         + String.format("User: %s", text)
                         + System.lineSeparator()
                         + String.format("Bot: %s", answersBot)
                         + System.lineSeparator()
                );
            }
        } catch (IOException e) {
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
