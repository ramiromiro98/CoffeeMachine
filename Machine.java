package machine;

public class Machine {

    private static final int ESPRESSO_WATER_PER_CUP = 250;
    private static final int ESPRESSO_COFFEE_BEANS_PER_CUP = 16;
    private static final int ESPRESSO_COST = 4;

    private static final int LATTE_WATER_PER_CUP = 350;
    private static final int LATTE_MILK_PER_CUP = 75;
    private static final int LATTE_COFFEE_BEANS_PER_CUP = 20;
    private static final int LATTE_COST = 7;

    private static final int CAPPUCCINO_WATER_PER_CUP = 200;
    private static final int CAPPUCCINO_MILK_PER_CUP = 100;
    private static final int CAPPUCCINO_COFFEE_BEANS_PER_CUP = 12;
    private static final int CAPPUCCINO_COST = 6;

    private int WATER_SUPPLIED;
    private int MILK_SUPPLIED;
    private int COFFEE_BEANS_SUPPLIED;
    private int DISPOSABLE_CUPS;
    private int MONEY;

    private CoffeeMachine.SystemStatus machineStatus;

    public Machine(int WATER_SUPPLIED, int MILK_SUPPLIED, int COFFEE_BEANS_SUPPLIED, int DISPOSABLE_CUPS, int MONEY, CoffeeMachine.SystemStatus machineStatus) {
        this.WATER_SUPPLIED = WATER_SUPPLIED;
        this.MILK_SUPPLIED = MILK_SUPPLIED;
        this.COFFEE_BEANS_SUPPLIED = COFFEE_BEANS_SUPPLIED;
        this.DISPOSABLE_CUPS = DISPOSABLE_CUPS;
        this.MONEY = MONEY;
        this.machineStatus = machineStatus;
    }

    public CoffeeMachine.SystemStatus getMachineStatus() {
        return machineStatus;
    }

    public void setMachineStatus(CoffeeMachine.SystemStatus machineStatus) {
        this.machineStatus = machineStatus;
    }

    public void action(String parameter){
        switch (machineStatus){
            case IN_MAIN_MENU:
                switch (parameter){
                    case "buy":
                        machineStatus = CoffeeMachine.SystemStatus.BUY;
                        break;
                    case "fill":
                        machineStatus = CoffeeMachine.SystemStatus.WATER;
                        break;
                    case "take":
                        takeMoney();
                        break;
                    case "remaining":
                        machineStatus();
                        break;
                    case "exit":
                        machineStatus = CoffeeMachine.SystemStatus.EXIT_PROGRAM;
                        break;
                    default:
                        System.out.println("Invalid Option");
                        break;
                }
                break;
            case BUY:
                switch (parameter){
                    case "1":
                        buyCoffee(parameter);
                        break;
                    case "2":
                        buyCoffee(parameter);
                        break;
                    case "3":
                        buyCoffee(parameter);
                        break;
                    case "back":
                        machineStatus = CoffeeMachine.SystemStatus.IN_MAIN_MENU;
                        break;
                    default:
                        System.out.println("Invalid Option");
                        machineStatus = CoffeeMachine.SystemStatus.IN_MAIN_MENU;
                        break;
                }
                break;
            case WATER:
                addWater(parameter);
                break;
            case MILk:
                addMilk(parameter);
                break;
            case COFFEE_BEANS:
                addCoffeeBeans(parameter);
                break;
            case DISPOSABLE_CUPS:
                addDisposableCups(parameter);
                break;
        }
    }

    public void buyCoffee(String parameter){
        if (validatePurchase(parameter)) {
            System.out.println("I have enough resources, Making you a coffee!");
            switch (parameter) {
                case "1":
                    buyEspresso();
                    break;
                case "2":
                    buyLatte();
                    break;
                case "3":
                    buyCappuccino();
                    break;
            }
        }
        else {
            System.out.println("Sorry, not enough resources");
        }

        machineStatus = CoffeeMachine.SystemStatus.IN_MAIN_MENU;
    }

    public void buyLatte(){
        WATER_SUPPLIED -= LATTE_WATER_PER_CUP;
        MILK_SUPPLIED -= LATTE_MILK_PER_CUP;
        COFFEE_BEANS_SUPPLIED -= LATTE_COFFEE_BEANS_PER_CUP;
        DISPOSABLE_CUPS--;
        MONEY += LATTE_COST;
    }

    public void buyEspresso(){
        WATER_SUPPLIED -= ESPRESSO_WATER_PER_CUP;
        COFFEE_BEANS_SUPPLIED -= ESPRESSO_COFFEE_BEANS_PER_CUP;
        DISPOSABLE_CUPS--;
        MONEY += ESPRESSO_COST;
    }

    public void buyCappuccino(){
        WATER_SUPPLIED -= CAPPUCCINO_WATER_PER_CUP;
        MILK_SUPPLIED -= CAPPUCCINO_MILK_PER_CUP;
        COFFEE_BEANS_SUPPLIED -= CAPPUCCINO_COFFEE_BEANS_PER_CUP;
        DISPOSABLE_CUPS--;
        MONEY += CAPPUCCINO_COST;
    }

    public void addWater(String parameter){
        WATER_SUPPLIED+=Integer.parseInt(parameter);
        machineStatus = CoffeeMachine.SystemStatus.MILk;
    }

    public void addMilk(String parameter){
        MILK_SUPPLIED+=Integer.parseInt(parameter);
        machineStatus = CoffeeMachine.SystemStatus.COFFEE_BEANS;
    }

    public void addCoffeeBeans(String parameter){
        COFFEE_BEANS_SUPPLIED+=Integer.parseInt(parameter);
        machineStatus = CoffeeMachine.SystemStatus.DISPOSABLE_CUPS;
    }

    public void addDisposableCups(String parameter){
        DISPOSABLE_CUPS+=Integer.parseInt(parameter);
        machineStatus = CoffeeMachine.SystemStatus.IN_MAIN_MENU;
    }

    public void machineStatus(){
        System.out.println("The coffee machine has:");
        System.out.println(WATER_SUPPLIED+" of water");
        System.out.println(MILK_SUPPLIED+" of milk");
        System.out.println(COFFEE_BEANS_SUPPLIED+" of coffee beans");
        System.out.println(DISPOSABLE_CUPS+" of disposable cups");
        System.out.println(MONEY+" of money");
        machineStatus= CoffeeMachine.SystemStatus.IN_MAIN_MENU;
    }

    public void takeMoney(){
        System.out.println("I gave you $"+MONEY);
        MONEY=0;
        machineStatus= CoffeeMachine.SystemStatus.IN_MAIN_MENU;
    }

    private boolean validatePurchase(String coffee)
    {
        boolean flag = false;
        if (coffee.equals("1")){
            if (WATER_SUPPLIED>=ESPRESSO_WATER_PER_CUP && COFFEE_BEANS_SUPPLIED>=ESPRESSO_COFFEE_BEANS_PER_CUP && DISPOSABLE_CUPS>0)
                flag = true;
            else
                flag = false;
        }
        else if (coffee.equals("2")){
            if (WATER_SUPPLIED>=LATTE_WATER_PER_CUP && MILK_SUPPLIED>=LATTE_MILK_PER_CUP && COFFEE_BEANS_SUPPLIED>=LATTE_COFFEE_BEANS_PER_CUP && DISPOSABLE_CUPS>0)
                flag = true;
            else
                flag = false;
        }
        else if (coffee.equals("3")){
            if (WATER_SUPPLIED>=CAPPUCCINO_WATER_PER_CUP && MILK_SUPPLIED>= CAPPUCCINO_MILK_PER_CUP && COFFEE_BEANS_SUPPLIED>=CAPPUCCINO_COFFEE_BEANS_PER_CUP && DISPOSABLE_CUPS>0)
                flag=true;
            else
                flag=false;
        }
        return flag;
    }
}
