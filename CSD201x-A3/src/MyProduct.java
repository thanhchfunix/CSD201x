/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.Scanner;
import data.Data;
import entity.Product;
import util.MyBSTree;
import util.Node;

/**
 * @author CatHuyThanh
 *
 */
public class MyProduct implements Serializable {

	Scanner sc = new Scanner(System.in);

	private String code, name;
	private int quantity, saled;
	private double price;

	// A list of products
	MyBSTree tree;

	public MyProduct() {
		tree = Data.readData();
		if (tree == null) {
			tree = new MyBSTree();
		}
	}

	// 1.1 input and insert a new product to tree
	public void insert() {
		boolean validate = true;
		code = name = "";
		price = quantity = saled = 0;
		System.out.print("Product code: ");
		while (validate) {
			code = sc.nextLine();
			if (checkString(code)) {
				System.out.print("Product name: ");
				while (validate) {
					name = sc.nextLine();
					if (checkString(name)) {
						System.out.print("Quantity: ");
						quantity = checkIntNumber(quantity);
						System.out.print("Saled: ");
						saled = checkIntNumber(saled);
						System.out.print("Price: ");
						price = checkDoubleNumber(price);
						validate = false;
					} else {
						System.err.println("Name must be required!");
						System.err.print("Enter product name again: ");
					}
				}
			} else {
				System.err.println("Code must be required!");
				System.err.print("Enter product code again: ");
			}
		}
		tree.insert(new Product(code, name, quantity, saled, price));
	}

	// 1.2 in-order traverse
	public void inOrder() {
		tree.inOrder();
	}

	// 1.3 BFT a tree
	public void BFT() {
		tree.BFT();
	}

	// 1.4 search a product by product code
	public void search() {
		boolean validate = true;
		code = "";
		Node<Product> p = null;
		System.out.print("Product code to search: ");
		while (validate) {
			code = sc.nextLine();
			if (checkString(code)) {
				p = tree.search(code);
				validate = false;
			} else {
				System.err.println("Code must be required!");
				System.err.print("Enter product code again: ");

			}
		}
		if (p != null) {
			System.out.println("Information of product code " + p.info.getCode());
			tree.visit(p);
		} else {
			System.err.println("The product code " + code + " is not exist!");
		}
	}

	// 1.5 delete a product by product code
	public void delete() {
		boolean validate = true;
		code = "";
		System.out.print("Product code to delete: ");
		while (validate) {
			code = sc.nextLine();
			if (checkString(code)) {
				tree.delete(code);
				validate = false;
			} else {
				System.err.println("Code must be required!");
				System.err.print("Enter product code again: ");
			}
		}
	}

	// 1.6 simply balancing a tree
	public void balance() {
		tree.balance();
	}

	// 1.7 count the number of products in the tree
	public int size() {
		return tree.count();
	}

	// Save all product data
	public void save() {
		Data.saveData(tree);
	}

	// Find product by price
	public void findByPrice() {
		boolean validate = true;
		price = 0;
		System.out.print("Enter price you want to find: ");
		while (validate) {
			price = checkDoubleNumber(price);
			validate = false;
		}
		tree.findByPrice(price);
	}

	private int checkIntNumber(int num) {
		boolean validate = true;
		while (validate) {
			if (sc.hasNextInt()) {
				num = sc.nextInt();
				validate = false;
			} else {
				System.err.print("Invalid number! Input again: ");
			}
			sc.nextLine();
		}
		return num;
	}

	private double checkDoubleNumber(double num) {
		boolean validate = true;
		while (validate) {
			if (sc.hasNextDouble()) {
				num = sc.nextDouble();
				validate = false;
			} else {
				System.err.print("Invalid number! Input again: ");
			}
			sc.nextLine();
		}
		return num;
	}

	private boolean checkString(String s) {
		if (s.trim().equals("") || s == null) {
			return false;
		}
		return true;
	}
}
