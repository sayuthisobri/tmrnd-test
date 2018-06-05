package com.msms.tmtest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class FileService {
    @Autowired
    FileWatcher fileWatcher;

    @PostConstruct
    void init() throws Exception {
        fileWatcher.init();
    }
}
