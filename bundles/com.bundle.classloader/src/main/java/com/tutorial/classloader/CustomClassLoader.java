package com.tutorial.classloader;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Logger;

public class CustomClassLoader implements BundleActivator {

    private final static Logger logger = Logger.getLogger(CustomClassLoader.class.getName());

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        logger.log(Level.WARNING , "STARTING A BUNDLE");

        String pathToCurrentApplication = System.getProperty("user.dir");
        String pathToDroppinsFolder = pathToCurrentApplication + "\\droppins";

        File fileDroppins = new File(pathToDroppinsFolder);

        if(!fileDroppins.exists()){
            fileDroppins.mkdir();
            logger.log(Level.WARNING , "Created drppins folder at " + pathToDroppinsFolder);
        }

        DroppinsWorker droppinsWorker = new DroppinsWorker(fileDroppins);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(droppinsWorker);


    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        logger.log(Level.WARNING , "STOPPING A BUNDLE");
    }

}