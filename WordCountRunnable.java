/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author Matteo
 */
public class WordCountRunnable implements Runnable {
    public static Lock totalWordCounterLock;
    public static Lock threadCountLock;
    
    public static int totalWordCounter;
    public static int threads;
    
    String fileName;
    
    public WordCountRunnable(String aFileName){
        fileName = aFileName;
    }
    
    @Override
    public void run() {
        threadCountLock.lock();
        threads++;
        threadCountLock.unlock();
        
        int wordCount = 0;
        
        
        try{
            File inputFile = new File(fileName);
            Scanner in = new Scanner(inputFile);
            
            while(in.hasNext()){
                in.next();
                wordCount++;
            }
            in.close();
        }catch(FileNotFoundException exception){
            System.out.println(exception.getMessage());
        }
        
        System.out.println(fileName+": "+wordCount);
        
        totalWordCounterLock.lock();
        totalWordCounter = totalWordCounter + wordCount;
        threadCountLock.lock();
        threads--;
        
        if(threads<1){
            System.out.println("Total Word Count: "+ totalWordCounter);
        }
        
        totalWordCounterLock.unlock();
        threadCountLock.unlock();
    }
}