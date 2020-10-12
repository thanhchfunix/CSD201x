
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
import data.Data;
import entity.Book;
import util.MyList;
import util.Node;

/**
 * @author CatHuyThanh
 *
 */

public class BookList {

	// a list of book
	private MyList books;

	private String bCode, title;
	private int quantity, lended, position;
	private double price;

	Scanner sc = new Scanner(System.in);

	public MyList getBooks() {
		return books;
	}

	public BookList() {
		books = Data.readData();
		if (books == null) {
			books = new MyList();
		}
	}

	// 1.0 accept information of a Book
	private Book getBook() {
		return new Book(bCode, title, quantity, lended, price);
	}

	// 1.1 accept and add a new Book to the end of book list
	public void addLast() {
		boolean check = true;
		bCode = title = "";
		price = quantity = lended = 0;
		System.out.print("Book code: ");
		while (check) {
			bCode = sc.nextLine();
			if (checkBCode(bCode)) {
				if (books.checkBCode(bCode)) {
					System.out.print("Book title: ");
					while (check) {
						title = sc.nextLine();
						if (checkTitle(title)) {
							System.out.print("Quantity: ");
							quantity = checkIntNumber(quantity);
							System.out.print("Lended: ");
							lended = checkIntNumber(lended);
							System.out.print("Price: ");
							price = checkDoubleNumber(price);
							check = false;
						} else {
							System.err.println("Title must be required!");
							System.err.print("Input book title again: ");
						}
					}
				} else {
					System.err.println("Book code must be unique");
					System.out.print("Book code: ");
				}
			} else {
				System.err.println("Code must be required!");
				System.err.print("Input book code again: ");
			}
		}

		books.addLast(this.getBook());
		Data.saveData(books);
	}

	public void save() {
		Data.saveData(books);
	}

	// 1.2 output information of book list
	public void list() {
		if (books.isEmpty()) {
			System.err.println("Books list is empty...");
		} else {
			books.traverse();
		}
	}

	// 1.3 search book by book code
	public void search() {
		boolean check = true;
		System.out.print("Book code for search: ");
		bCode = "";
		while (check) {
			bCode = sc.nextLine();
			if (checkBCode(bCode)) {
				books.search(bCode);
				check = false;
			}
		}
		Node<Book> book = books.search(bCode);
		if (book == null) {
			System.err.println(bCode + " is not exist...");
		} else {
			System.out.println("Infomation of book code " + bCode);
			System.out.println(book.info.toString());
		}
	}

	// 1.4 accept and add a new Book to the begining of book list
	public void addFirst() {
		boolean validate = true;
		bCode = title = "";
		price = quantity = lended = 0;
		System.out.print("Book code: ");
		while (validate) {
			bCode = sc.nextLine();
			if (checkBCode(bCode)) {
				if (checkBCode(bCode)) {
					System.out.print("Book title: ");
					while (validate) {
						title = sc.nextLine();
						if (checkTitle(title)) {
							System.out.print("Quantity: ");
							quantity = checkIntNumber(quantity);
							System.out.print("Lended: ");
							lended = checkIntNumber(lended);
							System.out.print("Price: ");
							price = checkDoubleNumber(price);
							validate = false;
						} else {
							System.err.println("Title must be required!");
							System.err.print("Input book title again: ");
						}
					}
				} else {
					System.err.println("Book code must be unique");
					System.out.print("Book code: ");
				}
			} else {
				System.err.println("Code must be required!");
				System.err.print("Input book code again: ");
			}
		}

		books.addFirst(this.getBook());
		Data.saveData(books);
	}

	// 1.5 Add a new Book after a position k
	public void addAfter() {
		boolean kt = true;
		bCode = title = "";
		price = quantity = lended = position = 0;
		System.out.print("Book code: ");
		while (kt) {
			bCode = sc.nextLine();
			if (checkBCode(bCode)) {
				if (books.checkBCode(bCode)) {
					System.out.print("Book title: ");
					while (kt) {
						title = sc.nextLine();
						if (checkTitle(title)) {
							System.out.print("Quantity: ");
							quantity = checkIntNumber(quantity);
							System.out.print("Lended: ");
							lended = checkIntNumber(lended);
							System.out.print("Price: ");
							price = checkDoubleNumber(price);
							System.out.print("Enter adding position: ");
							position = checkIntNumber(position);
							kt = false;
						} else {
							System.err.println("Code must be required!");
							System.err.print("Input book title again: ");
						}
					}
				} else {
					System.err.println("Book code must be unique");
					System.out.print("Book code: ");
				}
			} else {
				System.err.println("Code must be required!");
				System.err.print("Input book code again: ");
			}
		}
		books.addAfter(this.getBook(), position);
		Data.saveData(books);
	}

	// 1.6 Delete a Book at position k
	public void deleteAt() {
		boolean kt = true;
		position = 0;
		while (kt) {
			System.out.print("Enter position to delete: ");
			position = checkIntNumber(position);
			kt = false;
		}
		books.deleteAt(position);
		Data.saveData(books);
	}

	// Check input number (integer)
	public static int checkIntNumber(int num) {
		Scanner sc = new Scanner(System.in);
		boolean check = true;
		while (check) {
			if (sc.hasNextInt()) {
				num = sc.nextInt();
				check = false;
			} else {
				System.out.print("Invalid number! Input again: ");
			}
			sc.nextLine();
		}
		return num;
	}

	// Check input number (double)
	public static double checkDoubleNumber(double num) {
		Scanner sc = new Scanner(System.in);
		boolean check = true;
		while (check) {
			if (sc.hasNextDouble()) {
				num = sc.nextDouble();
				check = false;
			} else {
				System.out.print("Invalid number! Input again: ");
			}
			sc.nextLine();
		}
		return num;
	}

	// Check bcode if null
	public boolean checkBCode(String bCode) {
		if (bCode.trim().equals("") || bCode == null) {
			return false;
		}
		return true;
	}

	// Check title if null
	public static boolean checkTitle(String title) {
		if (title.trim().equals("") || title == null) {
			return false;
		}
		return true;
	}

	public void sortByCode() {
		books.sortByCode();
		books.traverse();
	}

	public void sortByPrice() {
		books.sortByPrice();
		books.traverse();
	}

}
