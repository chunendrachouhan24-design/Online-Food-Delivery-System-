import java.util.Scanner;

class FoodItem {
    private String itemName;
    private double price;

    public FoodItem(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }
}

class Restaurant {
    private String name;
    private FoodItem[] menu;

    public Restaurant(String name, FoodItem[] menu) {
        this.name = name;
        this.menu = menu;
    }

    public void displayMenu() {
        System.out.println("\n===== " + name + " MENU =====");

        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". "
                    + menu[i].getItemName()
                    + " - " + menu[i].getPrice());
        }
    }

    public FoodItem[] getMenu() {
        return menu;
    }
}
class Order {
    private FoodItem[] items = new FoodItem[10];
    private int[] quantities = new int[10];
    private int count = 0;

    private double subtotal;
    private double deliveryCharge;
    private double tax;
    private double total;

    public void addItem(FoodItem item, int quantity) {
        items[count] = item;
        quantities[count] = quantity;
        count++;
    }
    public void calculateTotal() {
        subtotal = 0;
        for (int i = 0; i < count; i++) {
            subtotal += items[i].getPrice() * quantities[i];
        }

        // Free delivery if subtotal > 500
        if (subtotal > 500) {
            deliveryCharge = 0;
        } else {
            deliveryCharge = 50;
        }
        // 5% GST
        tax = subtotal * 0.05;
        total = subtotal + deliveryCharge + tax;
    }
    public void displayOrderSummary() {
        System.out.println("\n===== ORDER SUMMARY =====");
        for (int i = 0; i < count; i++) {

            double amount = items[i].getPrice() * quantities[i];

            System.out.println(
                    items[i].getItemName()
                    + " x " + quantities[i]
                    + " = " + amount);
        }

        System.out.println("----------------------------");
        System.out.println("Subtotal        : " + subtotal);
        System.out.println("Delivery Charge : " + deliveryCharge);
        System.out.println("GST (5%)        : " + tax);
        System.out.println("Total Amount    : " + total);
    }
}

public class OnlineFoodDeliverySystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Food Items
        FoodItem burger = new FoodItem("Burger", 100);
        FoodItem pizza = new FoodItem("Pizza", 300);
        FoodItem sandwich = new FoodItem("Sandwich", 80);
        FoodItem fries = new FoodItem("French Fries", 120);

        FoodItem[] menu = {burger, pizza, sandwich, fries};

        Restaurant restaurant = new Restaurant("Food Hub", menu);

        restaurant.displayMenu();

        Order order = new Order();

        System.out.print("\nHow many different items do you want to order? ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {

            System.out.print("\nEnter menu item number: ");
            int choice = sc.nextInt();

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();

            order.addItem(menu[choice - 1], qty);
        }
        order.calculateTotal();
        order.displayOrderSummary();
        sc.close();
    }
}