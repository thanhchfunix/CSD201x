/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author CatHuyThanh
 *
 */

public class Main {

	// contains a list of MyFile
	private static MyFile[] files;

	// constructor
	public Main() {
		files = null;
	}

	// get information of all files under given folder name
	public void loadFiles(String folder) {
		List<MyFile> listFiles = new ArrayList<>();
		loadFiles(folder, listFiles);
		files = listFiles.stream().toArray(MyFile[]::new);
	}

	public void loadFiles(String folder, List<MyFile> listFiles) {
		File file = new File(folder);
	    File[] allFiles = file.listFiles();
	    for (File f : allFiles) {
	      if (f.isFile()) {
	        listFiles.add(new MyFile(f.getName(), f.length(), f.getAbsolutePath()));
	      } else if (f.isDirectory()) {
	        loadFiles(f.getPath(), listFiles);
	      }
	    }
	}

	// list information of all loaded files
	public void list(MyFile[] files) {
		if (files != null && files.length > 0) {
			// output heading
			System.out.println(String.format("%-20s%-10s", "Name", "Size(in byte)"));
			for (MyFile f : files) {
				System.out.println(f);
			}
		} else {
			System.out.println("List of files is empty...");
		}
	}

	// sort the list of files ascending by size (use selection sort)
	public void selectionSort() {
		int min;
		for (int i = 0; i < files.length - 1; i++) {
			min = i;
			for (int j = i + 1; j < files.length; j++) {
				if (files[j].getSize() < files[min].getSize()) {
					min = j;
				}
			}
			if (min != i) {
				MyFile temp = files[i];
				files[i] = files[min];
				files[min] = temp;
			}
		}
	}

	// sort the list of files ascending by size (use insertion sort)
	public void insertionSort() {
		MyFile key;
		int j, i;
		for (i = 1; i < files.length; i++) {
			key = files[i];
			j = i - 1;
			while (j >= 0 && files[j].getSize() > key.getSize()) {
				files[j + 1] = files[j];
				j = j - 1;
			}
			files[j + 1] = key;
		}
	}
	
	public void quickSort(int left, int right) {
		if (left >= right) {
			return;
		}
		MyFile pivot = files[(left + right) / 2];
		int i = left, j = right;
		do {
			while (files[i].getSize() < pivot.getSize()) {
				i++;
			}
			while (files[j].getSize() > pivot.getSize()) {
				j--;
			}
			if (i <= j) {
				MyFile temp = files[i];
				files[i] = files[j];
				files[j] = temp;
				i++;
				j--;
			}
		} while (i < j);
		quickSort(left, j);
		quickSort(i, right);
	}

	// sort and output sorted list of text files
	public void sort(SortType st) {
		if (st == SortType.INSERTTIONSORT) {
			insertionSort();
		} else if (st == SortType.SELECTIONSORT) {
			selectionSort();
		} else if (st == SortType.QUICKSORT) {
			quickSort(0, files.length - 1);
		}
		// output result after sorting
		list(files);
	}

	public void sortByName() {
		// sort list file by it's name ASC
        System.out.println("sort list file by it's name ASC: ");
        ArrayList<MyFile> listFile = new ArrayList<MyFile>();
        for (MyFile mf : files) {
			listFile.add(mf);
		}
        Collections.sort(listFile, new Comparator<MyFile>() {
            @Override
            public int compare(MyFile mf1, MyFile mf2) {
                return mf1.getName().compareTo(mf2.getName());
            }
        });
        
        // show list file sort by name
        for (MyFile mf : listFile) {
            System.out.println(mf.toString());
        }
	}
	
	// return true if given MyFile contains given keyword, otherwise return false
	public boolean searchFile(MyFile mf, String keyword) throws IOException {
		if (!mf.getName().toLowerCase().endsWith(".txt")) {
			return false;
		}
		
	    FileReader fr = null;
	    LineNumberReader lnr = null;
	    String str;
	    boolean result = false;

	    try {
	      // create new reader
	      fr = new FileReader(mf.getFullPath());
	      lnr = new LineNumberReader(fr);

	      // read lines till the end of the stream
	      while ((str = lnr.readLine()) != null) {
	        // check keyword in str
	        if (str.toLowerCase().contains(keyword.toLowerCase())) {
	          result = true;
	        }
	      }

	    } catch (IOException ex) {
	      return false;
	    } finally {
	      // closes the stream and releases system resources
	      if (fr != null) {
	        fr.close();
	      }
	      if (lnr != null) {
	        lnr.close();
	      }
	    }
	    return result;
	}

	// output information of all files which content has given keyword
	public void searchFile(String keyword) throws IOException {
		// save all files which matched given keyword to the list and output the list
		List<MyFile> listFiles = new ArrayList<>();
		for (MyFile f : files) {
			if (searchFile(f, keyword)) {
				listFiles.add(f);
			}
		}
		MyFile[] foundFiles = listFiles.stream().toArray(MyFile[]::new);
		list(foundFiles);
	}

	public void readFile(MyFile mf) throws IOException {
		FileReader fr = null;
	    LineNumberReader lnr = null;
	    String str;

	    try {
	      // create new reader
	      fr = new FileReader(mf.getFullPath());
	      lnr = new LineNumberReader(fr);

	      // read lines till the end of the stream
	      while ((str = lnr.readLine()) != null) {             
	            // prints content of line
	            System.out.println(str);
	      }

	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      // closes the stream and releases system resources
	      if (fr != null) {
	        fr.close();
	      }
	      if (lnr != null) {
	        lnr.close();
	      }
	    }
	}
	
	// output content of the files
	public void viewFile(String title) throws IOException {
		List<MyFile> txtFile = new ArrayList<>();
		for (MyFile f : files) {
			if (f.getName().endsWith(".txt")) {
				txtFile.add(f);
			}
		}
		boolean foundFile = false;
		for (MyFile mf : txtFile) {
			if (mf.getName().equals(title)) {
				readFile(mf);
				foundFile = true;
			}
		}
		if (!foundFile) {
			System.out.println('"' + title + '"' + " is not exist in directory!");
		}
	}
	
	public static int checkInput(boolean check, int choice) {
		Scanner sc = new Scanner(System.in);
		while (check) {
			if (sc.hasNextInt()) {
				choice = sc.nextInt();
				check = false;
			} else {
				System.out.print("Please enter a number: ");
			}
			sc.nextLine();
		}
		return choice;
	}

	public static void main(String[] args) {
		Main main = new Main();
		Scanner sc = new Scanner(System.in);
		boolean keepRunning = true;
		int choice = 0;
		while (keepRunning) {

//			Created menu
			System.out.println("|        MENU        |");
			System.out.println("|   1. Load files    |");
			System.out.println("|   2. Sort files    |");
			System.out.println("|   3. Search files  |");
			System.out.println("|   4. View files    |");
			System.out.println("|   0. Exit          |");
			System.out.println("**********************");
			System.out.print("Enter your choice: ");
			boolean check = true;
			choice = checkInput(check, choice);

			switch (choice) {
			case 1:
				System.out.print("Type name folder: ");
				check = true;
				while (check) {
		            String folder = sc.nextLine();
		            try {
		              main.loadFiles(folder);
		              main.list(files);
		              check = false;
		            } catch (NullPointerException ex) {
		              System.err.println("Directory is not exist");
		              System.out.print("Enter a folder again: ");
		            }
		          }
				break;
			case 2:
				try {
					System.out.println("\t 1. Sort By Size");
					System.out.println("\t 2. Sort By Name");
					check = true;
					int option = 0;
					System.out.print("Select: ");
					option = checkInput(check, option);
					if (option == 1) {
						System.out.println("\t 1. SELECTION SORT");
						System.out.println("\t 2. INSERTTION SORT");
						System.out.println("\t 3. QUICK SORT");
						check = true;
						int sortType = 0;
						System.out.print("Sort type: ");
						sortType = checkInput(check, sortType);
						if (sortType == 1) {
							main.sort(SortType.SELECTIONSORT);
						} else if (sortType == 2) {
							main.sort(SortType.INSERTTIONSORT);
						} else if (sortType == 3) {
							main.sort(SortType.QUICKSORT);
						} else {
							main.list(files);
						}
					} else {
						System.out.println("Sort file by name ASC:");
						main.sortByName();
					}
				} catch (Exception e1) {
					main.list(files);
				}				
				break;
			case 3:
				System.out.print("Enter any keyword to search:");
				String keyword = "";
				check = true;
				while (check) {
					keyword = sc.nextLine();
					if (!keyword.trim().equals("")) {
						try {
							main.searchFile(keyword);
							check = false;

						} catch (NullPointerException ex) {
							System.err.println("You need load files before search!");
							break;
						} catch (IOException ex) {
						}
					} else {
						System.out.println("Keyword is missing!");
						System.out.print("Enter any keyword again: ");
					}
				}
				break;
			case 4:
				try {
					System.out.print("Enter title file to view ");
					System.err.print("(case-sensitive)");
					System.out.print(": ");
					String title = sc.nextLine();
					main.viewFile(title);
				} catch (Exception e) {
					System.err.println("You need load files before search!");
				}
				break;
			case 0:
				keepRunning = false;
				break;
			default:
				System.out.println("Invalid input! You need choice again.");
				choice = checkInput(check, choice);
				keepRunning = true;
			}
		}
	}
}
