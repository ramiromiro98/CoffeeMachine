package machine;

import java.util.Scanner;

public class CoffeeMachine {

    enum SystemStatus{
        IN_MAIN_MENU, EXIT_PROGRAM, BUY, WATER, MILk, COFFEE_BEANS, DISPOSABLE_CUPS
    }

    public static void main(String[] args) {
        Machine machine = new Machine(400,540,120,9,550, SystemStatus.IN_MAIN_MENU);
        Scanner scanner = new Scanner(System.in);

        while (machine.getMachineStatus()!= SystemStatus.EXIT_PROGRAM)
        {
            switch (machine.getMachineStatus()){
                case IN_MAIN_MENU:
                    System.out.println("Write an action (buy, fill, take, remaining, exit): ")
                    break;
                case BUY:
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    break;
                case WATER:
                    System.out.println("Write how many ml of water do you want to add: ");
                    break;
                case MILk:
                    System.out.println("Write how many ml of milk do you want to add: ");
                    break;
                case COFFEE_BEANS:
                    System.out.println("Write how many grams of coffee beans do you want to add: ");
                    break;
                case DISPOSABLE_CUPS:
                    System.out.println("Write how many disposable cups of coffee do you want to add: ");
            }
            machine.action(scanner.next());
        }
    }
}

