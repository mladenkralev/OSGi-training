package com.tutorial.classloader;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class CustomClassLoader implements BundleActivator {

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Working");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("stopping");
    }

}