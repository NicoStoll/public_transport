package de.stoll.nicolas.transport.importer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.List;

@Service
@Slf4j
public class ThreadHelperService {

    private final Executor executor;

    public ThreadHelperService() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors()); // Number of CPU cores
        taskExecutor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2);
        taskExecutor.setQueueCapacity(1000); // Limit pending tasks in queue
        taskExecutor.setThreadNamePrefix("Async-Worker-");
        taskExecutor.initialize();
        this.executor = taskExecutor;
    }

    /**
     * Submits a task to run asynchronously.
     */
    public <T> CompletableFuture<T> runAsync(Task<T> task) {
        return CompletableFuture.supplyAsync(task::execute, executor);
    }

    /**
     * Runs multiple tasks in parallel and waits for all to complete.
     */
    public <T> void runBatch(List<Task<T>> tasks) {
        List<CompletableFuture<T>> futures = tasks.stream()
                .map(task -> CompletableFuture.supplyAsync(task::execute, executor))
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join(); // Wait for all tasks
    }

    @FunctionalInterface
    public interface Task<T> {
        T execute();
    }

    /**
     * Splits a list into smaller batches.
     */
    public static <T> List<List<T>> splitIntoBatches(List<T> list, int batchSize) {
        List<List<T>> batches = new ArrayList<>();
        for (int i = 0; i < list.size(); i += batchSize) {
            batches.add(list.subList(i, Math.min(i + batchSize, list.size())));
        }
        return batches;
    }
}
