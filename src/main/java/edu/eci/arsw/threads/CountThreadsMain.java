/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {

    public static void main(String a[]) {
        CountThread h1 = new CountThread();
        CountThread h2 = new CountThread();
        CountThread h3 = new CountThread();

        h1.setA(0);
        h1.setB(99);

        h2.setA(99);
        h2.setB(199);

        h3.setA(200);
        h3.setB(299);

        h1.run();
        h2.run();
        h3.run();
    }

}