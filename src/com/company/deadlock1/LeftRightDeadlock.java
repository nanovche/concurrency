package com.company.deadlock1;

public class LeftRightDeadlock {

    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight(){
        synchronized (left){
            synchronized (right){
                for (int i = 0; i < 5; i++) {
                    System.out.println(i);
                }
            }
        }
    }

    public void rightLeft(){
        synchronized (right){
            synchronized (left){
                for (int i = 0; i < 5; i++) {
                    System.out.println(i);
                }
            }
        }
    }
}
