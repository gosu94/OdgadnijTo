package com.odgadnijto;/**
 * Created by gosu on 18.12.16.
 */

/**
 * @author Tomasz Pilarczyk
 */
public class Tester {

    public static void main(String[] args) {
        String baba = "cosik ktosik mosik";
        baba = baba.replaceAll("\\s+", "");
        System.out.println(baba);
    }
}
