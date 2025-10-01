
import Models.Product;
import Services.InventoryManagement;
import util.CSVHelper;

import java.util.*;

public class App {

    public static void main(String[] args) {
        InventoryManagement inventoryManager = new InventoryManagement();
        CSVHelper helper = new CSVHelper();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addProduct(scanner, inventoryManager);
                case 2 -> displayAllProducts(inventoryManager);
                case 3 -> searchProductById(scanner, inventoryManager);
                case 4 -> deleteProduct(scanner, inventoryManager);
                case 5 -> updateProduct(scanner, inventoryManager);
                case 6 -> helper.generateReport();
                case 7 -> helper.readDATAfromCSV();
                case 8 -> {
                    System.out.println("Exiting Inventory Management System... Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // --------- Menu Printer ---------
    private static void printMenu() {
        System.out.println("\n----------------- Inventory Management System -----------------");
        System.out.println("1. Add Product");
        System.out.println("2. Display All Products");
        System.out.println("3. Search Product by ID");
        System.out.println("4. Delete Product");
        System.out.println("5. Update Product");
        System.out.println("6. Generate Report (CSV)");
        System.out.println("7. Add Products from CSV File");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    // --------- Case 1: Add Product ---------
    private static void addProduct(Scanner scanner, InventoryManagement manager) {
        if (manager.addElementByInput(scanner)) {
            System.out.println("✅ Product added successfully!");
        }
    }

    // --------- Case 2: Display Products ---------
    private static void displayAllProducts(InventoryManagement manager) {
        List<Product> products = manager.Read();
        manager.printTheTable(products);
    }

    // --------- Case 3: Search by ID ---------
    private static void searchProductById(Scanner scanner, InventoryManagement manager) {
        System.out.print("Enter the Product ID to search: ");
        int productId = scanner.nextInt();
        Product product = manager.ReadById(productId);

        if (product != null) {
            manager.printTheTable(Collections.singletonList(product));
        } else {
            System.out.println("⚠️ Product not found!");
        }
    }

    // --------- Case 4: Delete Product ---------
    private static void deleteProduct(Scanner scanner, InventoryManagement manager) {
        System.out.print("Enter the Product ID to delete: ");
        int productId = scanner.nextInt();
        manager.delete(productId);
        System.out.println("✅ Product deleted successfully!");
    }

    // --------- Case 5: Update Product ---------
    private static void updateProduct(Scanner scanner, InventoryManagement manager) {
        System.out.print("Enter the Product ID to update: ");
        int productId = scanner.nextInt();

        Product productToUpdate = new Product();
        productToUpdate.setProductId(productId);

        System.out.print("Enter the number of fields you want to update: ");
        int updates = scanner.nextInt();
        Set<Integer> selected = new HashSet<>();

        while (updates-- > 0) {
            System.out.println("\nChoose field to update:");
            System.out.println("1. Name");
            System.out.println("2. Type");
            System.out.println("3. Quantity");
            System.out.println("4. Price");
            System.out.print("Your choice: ");

            int updateChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (selected.contains(updateChoice)) {
                System.out.println("⚠️ You already chose this option. Try again.");
                updates++;
                continue;
            }

            selected.add(updateChoice);

            switch (updateChoice) {
                case 1 -> {
                    System.out.print("Enter new Name: ");
                    productToUpdate.setProductName(scanner.nextLine());
                }
                case 2 -> {
                    System.out.print("Enter new Type: ");
                    productToUpdate.setProductType(scanner.nextLine());
                }
                case 3 -> {
                    System.out.print("Enter new Quantity: ");
                    productToUpdate.setAvailableQty(scanner.nextInt());
                }
                case 4 -> {
                    System.out.print("Enter new Price: ");
                    productToUpdate.setPrice(scanner.nextDouble());
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }

        Product updatedProduct = manager.update(productToUpdate);
        if (updatedProduct != null) {
            System.out.println("✅ Product updated successfully!");
            manager.printTheTable(Collections.singletonList(updatedProduct));
        } else {
            System.out.println("⚠️ Update failed. Product not found.");
        }
    }
}
