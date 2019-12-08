
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



/**
 * Manages book object inside of the Library class
 * @author Robert Faricy
 *
 */
public class Book implements BookInterface 
{
	public String title;
	public String author;
	public String genre;
	public String filename;
	//Constructor
	/**
	 * 
	 * @param title the title
	 * @param author the author
	 */
	public Book(String title, String author)
	{
		this.title = title;
		this.author = author;
		genre = null;
		filename = null;
	}
	
	/**
	 * returns title
	 */
	public String getTitle() {

		return title;
	}

	/**
	 * sets title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * returns author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * sets author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * returns genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * sets genre
	 */
	public void setGenre(String genre) {
		
		this.genre = genre;
		
	
	}

	/**
	 * returns filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * sets filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * checks if the books have a title, author, genre, and filename
	 * catches missed filenames
	 */
	public boolean isValid() {
		try {
		if(title.equals(null)) {
			return false;
		}
		if(author.equals(null)) {
			return false;
		}
		if(genre.equals(null)) {
			return false;
		}
		if(filename.equals(null)) {
			return false;
		}
		File file = new File(filename);
		if (file.exists())
		{
			return true;
		}
		}
		catch(Exception e ) {
		}
		
		return false;
		
	}

	/**
	 * returns the text from the .txt file based on the filename
	 * will print line by line
	 */
	public String getText() {
		File file = new File(filename);
		StringBuilder returnValue = null;
		
		StringBuilder builder = new StringBuilder();
		
		try {
			Scanner lineScan = new Scanner(file);
			
			while (lineScan.hasNextLine())
			{
				returnValue = builder.append(lineScan.nextLine()+"\n");
			}
			lineScan.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		return returnValue.toString();
		
		
		}
	/**
	 * to string provides the book object as one string
	 * including the title, author, genre, and filename
	 */
	public String toString() 
	{
		return (title+", "  + author +", " + genre +", " + filename);
	}

}
