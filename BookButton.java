import javax.swing.JButton;

/**
 * manages book object inside of a Bookbutton that extends JButton.
 * @author Robert Faricy
 *
 */
public class BookButton extends JButton{
	private Book book;

	public BookButton(Book book) {
		this.book = book;
		this.setText(book.getTitle());
	}
	public Book getBook() {
		
		return book;
	}
	
}
