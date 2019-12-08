import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Creates instances of LibraryPanel and ReaderPanel classes
 * Defines an ActionListener for the BookButtons 
 * responds to events coming from the LibraryPanel by passing on Book information to the ReaderPanel.
 * @author Robert Faricy
 */

@SuppressWarnings("serial")
public class ReaderOfBooksPanel extends JPanel{
	
	private LibraryPanel libraryPanel;
	private ReaderPanel  readerPanel;
	
	
	
	public ReaderOfBooksPanel() {
		
		libraryPanel = new LibraryPanel(new BookButtonListener());				
		readerPanel = new ReaderPanel();
		
		this.setLayout(new BorderLayout());
		this.add(libraryPanel, BorderLayout.WEST);
		this.add(readerPanel, BorderLayout.CENTER);
		
		this.setBorder(BorderFactory.createRaisedBevelBorder());
	}

	//Defines an ActionListener for the BookButtons
	//responds to events coming from the LibraryPanel by passing on Book information to the ReaderPanel.
	
private class BookButtonListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		BookButton bookButton = (BookButton)(e.getSource());
		readerPanel.loadBook(bookButton.getBook());
		
	}
}
}
