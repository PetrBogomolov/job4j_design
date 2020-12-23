package ru.job4j.io.io;

import ru.job4j.io.io.search.SearchFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sourse, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target.toString()))
        )) {
            for (Path element : sourse) {
                zip.putNextEntry(new ZipEntry(element.toString()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(sourse.toString())
                )) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Архивация прошла успешно!");
    }

    public List<Path> exclude(Path root, String ext) throws IOException {
        SearchFiles exception = new SearchFiles(path -> !path.toString().contains(ext));
        Files.walkFileTree(root, exception);
        return exception.getPaths();
    }

    public void packSingleFile(Path sourse, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target.toString()))
        )) {
            zip.putNextEntry(new ZipEntry(sourse.toString()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(sourse.toString())
            )) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgZip func = new ArgZip(args);
        func.chooseElement();
        Path sourse = new File(func.directory()).toPath();
        Path target = new File(func.output()).toPath();
        String ext = func.exclude();
        List<Path> withOutExclude = new Zip().exclude(sourse, ext);
        new Zip().packFiles(withOutExclude, target);
    }
}
