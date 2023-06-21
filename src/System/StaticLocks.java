package System;

import java.util.concurrent.locks.ReentrantLock;

public class StaticLocks {
    private static int registerCount;
    private static ReentrantLock lock;

    public StaticLocks() {
        registerCount = 0;
        lock = new ReentrantLock();
    }

    public static boolean tryRegister() {
        if (registerCount < 7) {
            registerCount++;
            if (registerCount == 7) {
                lock.lock();
            }
        }
        else
            return false;
        return true;
    }

    public static void unregister() {
        if(registerCount == 7){
            lock.unlock();
        }
        if(registerCount>0){
            registerCount--;
        }
    }

    public static int getRegistered() {
        return registerCount;
    }

}

