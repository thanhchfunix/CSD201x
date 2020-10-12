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
import util.MyList;

/**
 * @author CatHuyThanh
 *
 */

// read data from file or write data to file data.txt
public class Data {

	// Save book data
	public static void saveData(MyList books) {
		try {
			File file = new File("data.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(books);

			oos.close();
			fos.close();
		} catch (IOException e) {
		}
	}

	// Read data
	public static MyList readData() {
		MyList books = null;
		try {
			File file = new File("data.txt");
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			books = (MyList) ois.readObject();

		} catch (FileNotFoundException e) {
			return books;
		} catch (IOException e) {
			return books;
		} catch (ClassNotFoundException e) {
			return books;
		}
		return books;
	}
}
