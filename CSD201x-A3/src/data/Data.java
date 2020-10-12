/**
 * 
 */
package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import util.MyBSTree;

/**
 * @author CatHuyThanh
 *
 */

// read and save all data product in data.txt
public class Data {
	
	// Save product data
	public static void saveData(MyBSTree tree) {
		try {
			File file = new File("data.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(tree);

			oos.close();
			fos.close();
			System.out.println("Save products successful!");
		} catch (IOException ex) {
		}
	}

	// Read product data
	public static MyBSTree readData() {
		MyBSTree tree = null;
		try {
			File file = new File("data.txt");
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			tree = (MyBSTree) ois.readObject();

		} catch (FileNotFoundException ex) {
			return tree;
		} catch (IOException ex) {
			return tree;
		} catch (ClassNotFoundException ex) {
			return tree;
		}
		return tree;
	}
}
