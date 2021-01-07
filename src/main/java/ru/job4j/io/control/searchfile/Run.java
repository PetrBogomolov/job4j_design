package ru.job4j.io.control.searchfile;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        GetData key = new GetData(args);
        key.searchKey();
        SearchFiles find = new SearchFiles();
        find.recordingResult(
                find.findFile(key.getDirectiry(), key.getSearchingFileName(), key.getPatternType()),
                key.getTargetFileName()
        );
    }
}
