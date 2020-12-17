package ru.job4j.it.io.archive;

import ru.job4j.it.io.search.Search;
import java.io.*;
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

    }

    public void packSingleFile(Path sourse, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(String.valueOf(target)))
        )) {
            zip.putNextEntry(new ZipEntry(sourse.toString()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(sourse.toString())
            )) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgZip func = new ArgZip(args);
        Path sourse = new File(func.directory()).toPath();
        Path target = new File(func.output()).toPath();
        String ext = func.exclude();
        new Zip().packFiles(Search.search(sourse, ext), target);
    }
}
