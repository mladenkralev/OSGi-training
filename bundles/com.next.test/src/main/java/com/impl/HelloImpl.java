package com.impl;

import com.interfaces.IHello;


public class HelloImpl implements IHello
{
    public void turnOn()
    {
        System.out.println("FubarService activated");
    }

    public void realTurnOff()
    {
        System.out.println("FubarService activated");
    }

    public void sayHello()
    {
        System.out.println("Hello World!");
    }
}