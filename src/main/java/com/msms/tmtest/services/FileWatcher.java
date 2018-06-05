package com.msms.tmtest.services;

import com.msms.tmtest.config.SystemConfig;
import com.msms.tmtest.services.csv.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.*;

@Service
@Slf4j
public class FileWatcher {

    @Autowired
    SystemConfig systemConfig;

    @Autowired
    CsvReader csvReader;

    @Async
    public void init() throws Exception {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        log.debug("Watching: " + systemConfig.getWatchDir());
        Path path = Paths.get(systemConfig.getWatchDir());

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);


        log.debug("Async: " + Thread.currentThread().getId());
        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                log.debug("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
                if (event.context() != null)
                    csvReader.processCsv(new File(Paths.get(path.toString(), event.context().toString()).toUri()));
            }
            key.reset();
        }
    }
}
