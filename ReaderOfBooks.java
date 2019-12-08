import javax.swing.JFrame;

/**
 * This Java application maintains a gui that allows a user to load a .csv file of
 * format title, author, genre, filename. Then creating a book object based on the filename attribute
 * to the corresponding .txt in the same folder. 
 * The book object can be read in the reader window and provides
 * Current/Total Page Number, Title, Author, and PageUp/PageDown buttons.
 * @author Robert Faricy
 */

//Class manages JFrame for main panel
public class ReaderOfBooks {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Reader of Books Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ReaderOfBooksPanel mainPanel = new ReaderOfBooksPanel();

		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setVisible(true);
	}

}

