import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Manages the ability of the gui to read a selected book object and provide relevant attributes
 * of type title, author, current page number and total page number.
 * Also includes page up and page down buttons.
 * @author Robert Faricy
 *
 */
@SuppressWarnings("serial")
public class ReaderPanel extends JPanel {
	private JLabel bookLabel;
	private JLabel bookAuthor;
	private JLabel bookPage;
	private JTextArea bookTextArea;
	private JScrollPane bookTextScroll;
	
	private int pageSize;
	private int pageMax;
	private int pageMin;
	private int pageValue;
	private int pageNumber;
	private int pageNumberCurrent;
	/** Initialize and layout components to display information about an Item */
	public ReaderPanel() {
		//instantiate and initialize labels to include in infoPanel
		JLabel bookLabelLabel = new JLabel("");
		bookAuthor = new JLabel("");
		bookPage = new JLabel("");
		bookLabel = new JLabel("");
		//configure infoPanel and add components
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
		infoPanel.setBorder(BorderFactory.createEtchedBorder());
		infoPanel.add(Box.createHorizontalGlue());
		infoPanel.add(bookLabelLabel);
		infoPanel.add(bookLabel);
		infoPanel.add(Box.createHorizontalGlue());
		infoPanel.add(bookAuthor);
		infoPanel.add(bookPage);
		//instantiate and configure the text area
		bookTextArea = new JTextArea();
		bookTextArea.setLineWrap(true);
		bookTextArea.setWrapStyleWord(true);
		bookTextArea.setEditable(false);
		bookTextArea.setBorder(BorderFactory.createEtchedBorder());
		
		//set up the scroll pane to hold the text area
		bookTextScroll = new JScrollPane(bookTextArea);
		bookTextScroll.setPreferredSize(new Dimension(400,200));
		bookTextScroll.setBorder(BorderFactory.createEtchedBorder());
		
		
		addAdjustmentListener adjustmentListener = new addAdjustmentListener();
		bookTextScroll.getVerticalScrollBar().addAdjustmentListener(adjustmentListener);
		
		
	
		
		
		pageButtonUpListener pageButtonUpListener = new pageButtonUpListener();
		pageButtonDownListener pageButtonDownListener = new pageButtonDownListener();
		JButton pageButtonUp = new JButton("Page UP");
		JButton pageButtonDown = new JButton("Page Down");
		pageButtonUp.addActionListener(pageButtonUpListener);
		pageButtonDown.addActionListener(pageButtonDownListener);
		
		JPanel pagePanel = new JPanel();
		pagePanel.setBorder(BorderFactory.createEtchedBorder());
		pagePanel.add(pageButtonUp);
		pagePanel.add(pageButtonDown);
		//set up this DisplayPanel's layout and add sub-panels/panes
		this.setLayout(new BorderLayout());
		this.add(infoPanel, BorderLayout.NORTH);
		this.add(bookTextScroll, BorderLayout.CENTER);
		this.add(pagePanel, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createEtchedBorder());
	}
	private class addAdjustmentListener implements AdjustmentListener {

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
		pageSize = bookTextScroll.getVerticalScrollBar().getBlockIncrement(1);
		pageMax = bookTextScroll.getVerticalScrollBar().getMaximum();
		pageMin= bookTextScroll.getVerticalScrollBar().getMinimum();
		pageValue = bookTextScroll.getVerticalScrollBar().getValue();
		//Dev Print tests
//		System.out.println("S " + pageSize);
//		System.out.println("Max " + pageMax);
//		System.out.println("Min " + pageMin);
//		System.out.println("value " + pageValue);
		
		if (pageValue != 0) {
		pageNumber = pageMax/pageSize;
		pageNumberCurrent = (pageNumber-((pageMax-pageValue)/pageSize))+1;
		bookPage.setText( pageNumberCurrent +"/"+pageNumber);
		}
		}
	}
	private class pageButtonUpListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
		bookTextScroll.getVerticalScrollBar().setValue(pageValue-pageSize);
		
		}
	}
	private class pageButtonDownListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent event) {
				bookTextScroll.getVerticalScrollBar().setValue(pageValue+pageSize);
			
								
			}
	}

	public void loadBook(Book book) {
		bookLabel.setText(book.getTitle()+"\t-" +book.getAuthor()+ "\t");
	
		String content =  book.getText();
		
		bookTextArea.setText(content);				
		this.revalidate();
		bookTextArea.setCaretPosition(0);
}
}
