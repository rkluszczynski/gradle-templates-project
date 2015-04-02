package com.danibuiza.javacodegeeks.concurrency;

import java.util.concurrent.locks.StampedLock;

public class Stamped {
    private StampedLock lock = new StampedLock();
    private long counter;
    public long s, t;

    public void readSharedData() {

        long stamp = lock.tryOptimisticRead();

        read();

        if (! lock.validate(stamp)) {
            long readStamp = lock.readLock();
            try {

                read();

            }
            finally {
                lock.unlock(readStamp);
            }
        }
    }

    private void read() {
    }

    public void writeSharedData() {
        long stamp = lock.writeLock();
        try {

            write();

        } finally {
            lock.unlockWrite(stamp);
        }
    }

    private void write() {
    }
}
