package com.fortests;

import java.util.Scanner;

public class CoffeeMachine {

    static Scanner scanner = new Scanner(System.in);

    int water;
    int milk;
    int coffeeBeans;
    int cups;
    int money;

    public CoffeeMachine(int water, int milk, int coffeeBeans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        do {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
        } while (chooseAction(coffeeMachine, scanner.next()));
    }

    public static boolean chooseAction(CoffeeMachine coffeeMachine, String choose) {
        switch (Action.getAction(choose)) {
            case BUY:
                coffeeMachine.buy();
                break;
            case FILL:
                coffeeMachine.fill();
                break;
            case TAKE:
                coffeeMachine.take();
                break;
            case REMAINING:
                coffeeMachine.showInfo();
                break;
            case EXIT:
                return false;
            default:
                System.out.println("Wrong choice");
                break;

        }
        return true;
    }

    public void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        switch (scanner.next()) {
            case "1":
                checkResourcesAndBuy(CoffeeType.ESPRESSO);
                break;
            case "2":
                checkResourcesAndBuy(CoffeeType.LATTE);
                break;
            case "3":
                checkResourcesAndBuy(CoffeeType.CAPPUCCINO);
                break;
            case "back":
                break;
            default:
                System.out.println("Wrong choice");
                break;
        }
    }

    public void checkResourcesAndBuy(CoffeeType coffee) {
        if (water < coffee.getWater()) {
            System.out.println("Sorry, not enough water!\n");
        } else if (milk < coffee.getMilk()) {
            System.out.println("Sorry, not enough milk!\n");
        } else if (coffeeBeans < coffee.getCoffeeBeans()) {
            System.out.println("Sorry, not enough coffeeBeans!\n");
        } else if (cups - 1 < 0) {
            System.out.println("Sorry, not enough cups!\n");
        } else {
            System.out.println("I have enough resources, making you a coffee!\n");
            water -= coffee.getWater();
            milk -= coffee.getMilk();
            coffeeBeans -= coffee.getCoffeeBeans();
            cups--;
            money += coffee.getCost();
        }
    }

    public void fill() {
        System.out.println("Write how many ml of water do you want to add: ");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add: ");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        coffeeBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        cups += scanner.nextInt();
    }

    public void take() {
        money = 0;
    }

    public void showInfo() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money\n");
    }
}

enum Action {
    BUY("buy"), FILL("fill"), TAKE("take"), REMAINING("remaining"), EXIT("exit");

    String action;

    Action(String action) {
        this.action = action;
    }

    public String getStringAction() {
        return action;
    }

    public static Action getAction(String action) {
        for (Action thisAction : values()) {
            if (action.equalsIgnoreCase(thisAction.getStringAction())) {
                return thisAction;
            }
        }
        System.out.println("Invalid action");
        return null;
    }
}

enum CoffeeType {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);

    final int water;
    final int milk;
    final int coffeeBeans;
    final int cost;

    CoffeeType(int water, int milk, int coffeeBeans, int cost) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cost = cost;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public int getCost() {
        return cost;
    }

}
