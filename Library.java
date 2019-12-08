import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the collection of book objects.
 * @author Robert Faricy
 *
 */
public class Library implements LibraryInterface
{
	//Initializes an array list for the library.
	private ArrayList<Book> book = new ArrayList<Book>();
	
	
	
	/**
	 * gets the book list from a copy to encapsulate
	 */
	public ArrayList<Book> getBooks() {
		ArrayList<Book>	bookCopy = new ArrayList<Book>();
		bookCopy.addAll(book);
		return bookCopy;
	}

	/**
	 * adds a book to the booklist
	 */
	public void addBook(Book books) {
		// TODO Auto-generated method stub
		book.add(books);
	}
	

	/**
	 * removes a book from the booklist
	 */
	public void removeBook(int index) {
		// TODO Auto-generated method stub
		// if else to validate if given index is inside range
		
		if(index < 0 || index >= book.size()) 
		{
			System.out.println("Please select a book in index range");
		}
		else
		{
		book.remove(index);
		}

	}
	public void removeBooks(int index) {
		
		book.remove(index);
	}

	@Override
	/**
	 * gets an index for a book from the booklist
	 */
	public Book getBook(int index) {
	
		if(index < 0 || index >= book.size()) {
			System.out.println("Please select a book in index range");
		}
		else {
		return book.get(index);
			}
		return null;

}
	/**
	 * to string provides an index followed by the Title, author, genre, filename 
	 * of the book from the booklist
	 */
	public String toString(){
		String returnValue = "";
		//ArrayList<Book> toString = new ArrayList<>();
		for(int i = 0; i < book.size(); i++) {
			returnValue +=i + " - " + book.get(i).toString();
			returnValue += "\n";
			}
		return returnValue;
	}

	@Override
	public void loadLibraryFromCSV(String csvFilename) {

		//Instance scanners and line delimiter
		File csv = new File(csvFilename);
		String lineScan = "";

		//Create Array-list of type Book
		Library myLibrary = new Library();
		//Validate
		if(csv.exists() && csv.isFile())
		{

			try {
				Scanner fileScan = new Scanner(csv);
				while (fileScan.hasNextLine())
				{
					//Scan lines
					lineScan = fileScan.nextLine();	
					String[] split = lineScan.split(",");

					String title = split [0];
					String author = split [1];
					String genre = split [2];
					String filePath = split [3];

					//Creates book object with given title,author
					Book add = new Book(title, author);


					//Appends onto book object genre and filepath
					add.setGenre(genre);
					add.setFilename(filePath);


					//Adds book object to myLibrary
					this.addBook(add);
					

				}
				//Print Test
				//System.out.println(myLibrary);
				fileScan.close();
			}catch (FileNotFoundException e) {
				System.out.println("Could not open file: "+ csv);
			}			
		}
	}
}