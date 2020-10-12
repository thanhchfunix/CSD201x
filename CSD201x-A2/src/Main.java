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

	// check your choice
	public static int checkIntNumber(int num) {
		Scanner sc = new Scanner(System.in);
		boolean check = true;
		while (check) {
			if (sc.hasNextInt()) {
				num = sc.nextInt();
				check = false;
			} else {
				System.err.print("Invalid choice! Input again: ");
			}
			sc.nextLine();
		}
		return num;
	}

	public static void main(String[] args) {

		BookList bookList = new BookList();
		Scanner sc = new Scanner(System.in);
		boolean keepRunning = true;
		int choice = 0;

		while (keepRunning) {
			// Created menu
			System.out.println("*****************************************");
			System.out.println("|        	 MENU        	        |");
			System.out.println("|   1. Input Book and add to the end    |");
			System.out.println("|   2. Display books    		|");
			System.out.println("|   3. Search by code  			|");
			System.out.println("|   4. Input book and add to beginning  |");
			System.out.println("|   5. Add book after position k        |");
			System.out.println("|   6. Delete book at position k        |");
			System.out.println("|   7. Sort book by code                |");
			System.out.println("|   8. Sort book by price               |");
			System.out.println("|   0. Exit          			|");
			System.out.println("*****************************************");
			System.out.println("Your choice: ");
			choice = checkIntNumber(choice);

			switch (choice) {
			// Add new book at last of books list
			case 1:
				bookList.addLast();
				break;
			// Show all books
			case 2:
				bookList.list();
				break;
			// Search book by book code
			case 3:
				bookList.search();
				break;
			// Add new book at begin of books list
			case 4:
				bookList.addFirst();
				break;
			// Add new book at position k
			case 5:
				bookList.addAfter();
				break;
			// Delete a book at position k
			case 6:
				bookList.deleteAt();
				break;
			//Sort By Code
			case 7:
				bookList.sortByCode();
				break;
			//Sort By Price
			case 8:
				bookList.sortByPrice();
				break;
			case 0:
				keepRunning = false;
				break;
			}
		}
		sc.close();
	}
}
