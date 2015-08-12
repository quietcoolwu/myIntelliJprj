package com.mooc163.java2;

/**
 * Created by William on 2015/7/17.
 */
public class vendingMachine {

    int price = 80;
    int balance;
    int total;

    public static void main(String[] args) {
        vendingMachine vm = new vendingMachine();
        vm.showPrompt();
        vm.showBalance();
        vm.insertMoney(100);
        vm.getFood();
        vm.showBalance();


    }

    void showPrompt() {
        System.out.println("Welcome");
    }

    void insertMoney(int amount) {
        balance = balance + amount;

    }

    void showBalance() {
        System.out.println(balance);
    }

    void getFood() {
        if (balance >= price) {
            System.out.println("Here you are.");
            balance = balance - price;
            total = total + price;
        }
    }
}
