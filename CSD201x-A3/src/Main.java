import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author CatHuyThanh
 *
 */
public class Main {

	public static Scanner sc = new Scanner(System.in);

	// Check input choice
	public static int checkChoice(int num) {
		boolean check = true;
		while (check) {
			if (sc.hasNextInt()) {
				num = sc.nextInt();
				check = false;
			} else {
				System.err.print("Invalid number! Input again: ");
			}
			sc.nextLine();
		}
		return num;
	}

	public static void main(String[] args) {
		MyProduct p = new MyProduct();

		boolean keepRunning = true;
		
		while (keepRunning) {
			System.out.println("|**********************************|");
			System.out.println("|            Product List          |");
			System.out.println("|**********************************|");
			System.out.println("| 1. Insert a new product          |");
			System.out.println("| 2. In-order traverse             |");
			System.out.println("| 3. Breadth first  traverse       |");
			System.out.println("| 4. Search by a product code      |");
			System.out.println("| 5. Delete by a product code      |");
			System.out.println("| 6. Simple balancing              |");
			System.out.println("| 7. Count number of products      |");
			System.out.println("| 8. Save all products             |");
			System.out.println("| 9. Search by a product price     |");
			System.out.println("| 0. Exit                          |");
			System.out.println("|----------------------------------|");
			System.out.print("  Your choice: ".toUpperCase());

			int choice = 0;
			choice = checkChoice(choice);

			switch (choice) {
			
			// Add new product
			case 1:
				p.insert();
				break;
				
			// Breadth first traversal - Inorder
			case 2:
				p.inOrder();
				break;
				
			// Breadth first traversal
			case 3:
				p.BFT();
				break;
				
			// Search product by code
			case 4:
				p.search();
				break;
				
			// Delete product by code
			case 5:
				p.delete();
				break;
				
			// Balancing BST
			case 6:
				p.balance();
				break;
				
			// Count all products
			case 7:
				System.out.println("Number of products: " + p.size());
				break;
				
			// Save all products data.txt
			case 8:
				p.save();
				break;
				
			// Search product by price >= input price
			case 9:
				p.findByPrice();
				break;
				
			// Exit
			case 0:
				keepRunning = false;
				break;
			}
		}
		sc.close();
	}
}
