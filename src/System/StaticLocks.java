package System;

import java.util.concurrent.locks.ReentrantLock;

public class StaticLocks {
    private static ReentrantLock seaLock;
    private static ReentrantLock airLock;
    private static ReentrantLock landLock;
    private static ReentrantLock[] locks;
    private static int[] registerCount;

    public StaticLocks() {
        registerCount = new int[]{0, 0, 0};
        seaLock = new ReentrantLock();
        airLock = new ReentrantLock();
        landLock = new ReentrantLock();
        locks = new ReentrantLock[]{seaLock, airLock, landLock};
    }

    public static boolean tryRegister(int[] lockVal) {
        int count = 0;
        for (int i = 0; i < registerCount.length; i++) {
            count += registerCount[i];
        }
        if (count < 7) {

            return true;
        }
        else
            return false;
    }
}

    public static void unregister(int[] lockVal) {
        for (int i = 0; i < lockVal.length; i++) {
            if (registerCount[i] > 0) {
                registerCount[i] -= lockVal[i];
            }
        }
    }

    public static int[] getRegistered() {
        return registerCount;
    }

}

