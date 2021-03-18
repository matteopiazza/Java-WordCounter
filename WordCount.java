/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matteo
 */
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Matteo
 */
public class WordCount {
    public static void main(String[] args){
        if(args.length<=0){
            throw new IllegalArgumentException("\n"+"No Files entered for counting"+"\n"+"Please enter files as following: java WordCount fileName.txt");
        }
        
        WordCountRunnable.threads = 0;
        WordCountRunnable.threadCountLock = new ReentrantLock();
        WordCountRunnable.totalWordCounter = 0;
        WordCountRunnable.totalWordCounterLock = new ReentrantLock();
        
        for(int i=0;i<args.length;i++){
            WordCountRunnable wc = new WordCountRunnable(args[i]);
            Thread t = new Thread(wc);
            t.start();
        }if(args.length<=0){
            throw new IllegalArgumentException("\n"+"No Files entered for counting"+"\n"+"Please enter files as following: java WordCount fileName.txt");
        }
    }
}