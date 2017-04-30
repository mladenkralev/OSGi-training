package com.tutorial.classloader;


import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Mladen on 27.4.2017 г..
 */
public class DroppinsWorker implements Runnable {

    public static final int THREAD_SLEEPING_MILIS = 5000;
    private final static Logger logger = Logger.getLogger(CustomClassLoader.class.getName());
    private final Set<File> filesInDirectory = new TreeSet<File>();
    private BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
    private File dirToCheck;

    DroppinsWorker(File dirToCheck) {
        this.dirToCheck = dirToCheck;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.currentThread().sleep(THREAD_SLEEPING_MILIS);

                File[] filesInDir = dirToCheck.listFiles();

                if (filesInDir.length == 0) {
                    logger.log(Level.OFF, "Empty droppins folder");
                }

                for (File file : filesInDir) {
                    if (!filesInDirectory.contains(file)) {
                        logger.log(Level.OFF, "Added FILE: " + file.getName());
                        filesInDirectory.add(file);
                    }
                }

                for (File file : filesInDir) {
                    logger.log(Level.OFF, "Bundle Installed " + file.getName());
                    install(context, file);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void install(BundleContext context, File file) {
        Bundle bundle = null;
        try {
            bundle = context.installBundle(file.toURI().toString());
            bundle.start();
        } catch (BundleException e) {
            e.printStackTrace();
        }
    }
}
