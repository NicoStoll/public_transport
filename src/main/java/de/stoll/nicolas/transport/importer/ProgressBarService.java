package de.stoll.nicolas.transport.importer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProgressBarService {

    private static final int BAR_WIDTH = 100; // Width of the progress bar
    private long totalItems;
    private long processedItems;

    private String importDataType = "";

    public void startProgressBar(long total, String importDataType) {
        this.totalItems = total;
        this.processedItems = 0;
        this.importDataType = importDataType;
        printProgress();
    }

    public void updateProgress(int times) {
        processedItems += times;
        printProgress();
    }

    public void complete() {
        printProgress(100);
        System.out.println();
    }

    private void printProgress() {
        int percent = (int) (((double) processedItems / totalItems) * 100);
        printProgress(percent);
    }

    private void printProgress(int percent) {

        int filled = Math.min((percent * BAR_WIDTH) / 100, BAR_WIDTH);
        String bar = "[" + "=".repeat(filled) + " ".repeat(BAR_WIDTH - filled) + "] " + percent + "% " + importDataType;
        System.out.print("\r" + bar);
    }
}
