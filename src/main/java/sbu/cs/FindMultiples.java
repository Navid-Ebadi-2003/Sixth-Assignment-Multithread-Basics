package sbu.cs;

/*
    In this exercise, you must write a multithreaded program that finds all
    integers in the range [1, n] that are divisible by 3, 5, or 7. Return the
    sum of all unique integers as your answer.
    Note that an integer such as 15 (which is a multiple of 3 and 5) is only
    counted once.

    The Positive integer n > 0 is given to you as input. Create as many threads as
    you need to solve the problem. You can use a Thread Pool for bonus points.

    Example:
    Input: n = 10
    Output: sum = 40
    Explanation: Numbers in the range [1, 10] that are divisible by 3, 5, or 7 are:
    3, 5, 6, 7, 9, 10. The sum of these numbers is 40.

    Use the tests provided in the test folder to ensure your code works correctly.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FindMultiples
{

    public class t3 implements Runnable{

        private int n;

        private ArrayList<Integer> L3 = new ArrayList<Integer>();

        public t3(int n) {
            this.n = n;
        }

        public void run(){

            for(int i =1 ; i<=n ; i++){
                if(i%3 == 0){
                    L3.add(i);
                }
            }
        }

        public ArrayList<Integer> getL3() {
            return L3;
        }
    }




    public class t5 implements Runnable{

        private int n;

        private ArrayList<Integer> L5 = new ArrayList<Integer>();

        public t5(int n) {
            this.n = n;
        }

        public void run(){

            for(int i =1 ; i<=n ; i++){
                if(i%5 == 0){
                    L5.add(i);
                }
            }
        }

        public ArrayList<Integer> getL5() {
            return L5;
        }
    }


    public class t7 implements Runnable{

        private int n;

         private ArrayList<Integer> L7 = new ArrayList<Integer>();

        public t7(int n) {
            this.n = n;
        }

        public void run(){

            for(int i =1 ; i<=n ; i++){
                if(i%7 == 0){
                    L7.add(i);
                }
            }
        }

        public ArrayList<Integer> getL7() {
            return L7;
        }
    }


    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */
    public int getSum(int n) {
        int sum = 0;
        ArrayList<Integer> L_all = new ArrayList<Integer>();

        t3 obj3 = new t3(n);
        Thread T3 = new Thread(obj3);

        t5 obj5 = new t5(n);
        Thread T5 = new Thread(obj5);

        t7 obj7 = new t7(n);
        Thread T7 = new Thread(obj7);

        T3.start();
        T5.start();
        T7.start();

        while (T3.isAlive() || T5.isAlive() || T7.isAlive()){

        }

        L_all.addAll(obj3.getL3());
        L_all.addAll(obj5.getL5());
        L_all.addAll(obj7.getL7());

//        for (int i =0 ;i < L_all.size();i++){
//            for(int g = i+1 ; g<L_all.size();g++){
//                if(L_all.get(i)==L_all.get(g)){
//                    L_all.remove(i);
//                }
//            }
//        }

        Set<Integer> set = new HashSet<>(L_all);
        L_all.clear();
        L_all.addAll(set);

        for (int i : L_all){
            sum+=i;
        }

        return sum;
    }

    public static void main(String[] args) {
    }
}
