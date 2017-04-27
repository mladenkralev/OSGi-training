package com.helloworld;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleListener;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.Bundle;

public class HelloWorldActivator implements BundleActivator, BundleListener {

    private BundleContext context =
            FrameworkUtil.getBundle(HelloWorldActivator.class).getBundleContext();

    @Override
    public void bundleChanged(BundleEvent event) {
        switch (event.getType()) {
            case BundleEvent.INSTALLED:
                System.out.println("Installed");
                printBundleCount();
                break;
            case BundleEvent.STARTED:
                System.out.println("started");
                printBundleCount();
                break;
            default:
                break;
        }
    }

    private void printBundleCount() {
        Bundle[] bundlesInContext = context.getBundles();
        for (Bundle bundle : bundlesInContext) {
            System.out.println(bundle.toString());
        }
    }

    private int getBundleCount() {
        return context.getBundles().length;
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Working");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("stopping");
    }
}