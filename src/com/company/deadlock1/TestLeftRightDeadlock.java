package com.company.deadlock1;

public class TestLeftRightDeadlock {

    public static void main(String[] args) {

        LeftRightDeadlock leftRightDeadlock = new LeftRightDeadlock();
        Thread thread1 = new Thread(leftRightDeadlock::leftRight);
        Thread thread2 = new Thread(leftRightDeadlock::rightLeft);
        thread1.start();
        thread2.start();

    }

}
