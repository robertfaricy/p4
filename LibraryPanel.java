import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Manages the library panel that contains the Load Book button, and BookButton List
 * @author Robert Faricy
 *
 */
@SuppressWarnings("serial")
public class LibraryPanel extends JPanel {

Library myLibrary = new Library();
Book book = new Book("", "");
BookButton button = new BookButton(book);
JButton createBooksButton = new JButton("Load Books");

private ActionListener bookButtonListener;
private JTextField numberOfBooksTextField;
private JPanel buttonListPanel;

public LibraryPanel(ActionListener bookButtonListener) {
	//remember this so we can register it with any future BookButtons
	this.bookButtonListener = bookButtonListener;

	//configure the button list panel and the scroll pane that holds it
	buttonListPanel = new JPanel();
	buttonListPanel.setLayout(new BoxLayout(buttonListPanel, BoxLayout.Y_AXIS));
	buttonListPanel.setBorder(BorderFactory.createRaisedBevelBorder());
	JScrollPane buttonListScroll = new JScrollPane(buttonListPanel);
	buttonListScroll.setPreferredSize(new Dimension(300,300));
	buttonListScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	buttonListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	buttonListScroll.setBorder(BorderFactory.createLoweredBevelBorder());

	//configure the text field and button for determining how many BookButtons to create
	CreateBooksListener createBooksListener = new CreateBooksListener();
	numberOfBooksTextField = new JTextField(10);
	numberOfBooksTextField.setToolTipText("File.csv list to be loaded");
	numberOfBooksTextField.addActionListener(createBooksListener);
	
	createBooksButton.addActionListener(createBooksListener);
	
	//configure the panel to organize the text field and button
	JPanel createBooksPanel = new JPanel();
	createBooksPanel.setLayout(new BoxLayout(createBooksPanel, BoxLayout.X_AXIS));
	createBooksPanel.add(Box.createHorizontalGlue());
	createBooksPanel.add(numberOfBooksTextField);
	createBooksPanel.add(createBooksButton);
	createBooksPanel.add(Box.createHorizontalGlue());
	createBooksPanel.setBorder(BorderFactory.createEtchedBorder());

	//configure this panel to organize the button list and configuration panes/panels
	this.setLayout(new BorderLayout());
	this.add(buttonListScroll, BorderLayout.CENTER);
	this.add(createBooksPanel, BorderLayout.SOUTH);
	this.setBorder(BorderFactory.createEtchedBorder());
}

/** Create Books and associated BookButtons */
private class CreateBooksListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println(buttonListPanel.getComponentCount());

		if(myLibrary.getBooks().size() == 0)
		{	
			createBooksButton.setText("Clear Books");
			myLibrary.loadLibraryFromCSV(numberOfBooksTextField.getText());

			for(int i = 0; i<myLibrary.getBooks().size(); i++) {
				button = new BookButton(myLibrary.getBook(i));
				button.setMaximumSize(new Dimension(300,30));
				button.addActionListener(bookButtonListener);
				buttonListPanel.add(button);
				button.setToolTipText(myLibrary.getBook(i).toString());
			}
		}

		else{
			{	
				
				buttonListPanel.removeAll();
				
				createBooksButton.setText("Load Books");
				for(int i = 0; i<=myLibrary.getBooks().size(); i++) {
					myLibrary.removeBook(i);
					button.setVisible(false);
					buttonListPanel.revalidate();
				}


			}
			buttonListPanel.revalidate();






		}
	}
}
}







