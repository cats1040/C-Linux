package com.shreya.singleton;

// lazy-loading, singleton, fast!
public class Singleton {
    
    private static volatile Singleton instance = null;
    private static Integer mutex = 10;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new Singleton();
                }
                else {
                    return instance;
                }
            }
        }
        return instance;
    }
}
