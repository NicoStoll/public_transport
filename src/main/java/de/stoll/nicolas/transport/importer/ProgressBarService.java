package de.stoll.nicolas.transport.importer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProgressBarService {

    private static final int BAR_WIDTH = 100; // Width of the progress bar
    private long totalItems;
    private long processedItems;

    public void startProgressBar(long total) {
        this.totalItems = total;
        this.processedItems = 0;
        printProgress();
    }

    public void updateProgress(int times) {
        processedItems += times;
        printProgress();
    }

    public void complete() {
        printProgress(100);
        log.info("\n Import completed successfully!");
    }

    private void printProgress() {
        int percent = (int) (((double) processedItems / totalItems) * 100);
        printProgress(percent);
    }

    private void printProgress(int percent) {
        int filled = (percent * BAR_WIDTH) / 100;
        String bar = "[" + "=".repeat(filled) + " ".repeat(BAR_WIDTH - filled) + "] " + percent + "%";
        System.out.print("\r" + bar);
    }
}
