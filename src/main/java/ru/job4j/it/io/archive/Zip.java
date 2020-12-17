package ru.job4j.it.io.archive;

import ru.job4j.it.io.search.Search;
import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(Path sourse, List<Path> exception, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target.toString())
                )
        )) {
            zip.putNextEntry(new ZipEntry(sourse.toString()));
            try (BufferedReader in = new BufferedReader(new FileReader(sourse.toString()))) {
                while (in.ready()) {
                    String line = in.readLine();
                    for (Path path : exception) {
                        if (!path.getFileName().toString().equals(line)) {
                            zip.write(line.getBytes());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Path sourse = new File(func.directory()).toPath();
        Path target = new File(func.output()).toPath();
        String ext = func.exclude();
        new Zip().packFiles(sourse, Search.search(sourse, ext), target);
    }
}
