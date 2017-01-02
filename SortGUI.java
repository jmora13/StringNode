//Jose Mora
//CIS 255
//12-8-15
//Project 8

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.Random;

public class SortGUI extends JPanel {

	private int[] selectionArray, insertionArray;
	private int indexOfSelection = 0, indexOfInsertion = 1;
	private boolean isSelectionSorted = false;
	private boolean isInsertionSorted = false;

	// variables for the display
	private static final int ARRAY_SIZE = 50, BAR_WIDTH = 8, SPACE_APART = 5, MAX = 80, MIN = 1, SS_Y_START = 120, IS_Y_START = 270;
	private JButton changeButton;

	Random r = new Random();
	
	public SortGUI() {
		selectionArray = new int[ARRAY_SIZE];
		insertionArray = new int[ARRAY_SIZE];

		for (int i = 0; i < ARRAY_SIZE; i++) {
			int x = r.nextInt(MAX);
			this.selectionArray[i] = x;
			this.insertionArray[i] = x;
		}

		setBackground(Color.white);
		setPreferredSize(new Dimension(300, 300));

		changeButton = new JButton("Click to take one step in the search");
		add(changeButton);
		changeButton.addActionListener(new ButtonHandler());		
	}

	public void paintComponent(Graphics pen) {
		super.paintComponent(pen);
		int xPos = 20;

		if (isSelectionSorted == false ) {
			pen.setColor(Color.blue);
			pen.drawString("Selection Sort", xPos, SS_Y_START + 20);
		} else {
			pen.setColor(Color.MAGENTA);
			pen.setFont(new Font("Arial", Font.ITALIC, 30));
			pen.drawString("Yay! Last Project!", 150, 180);
		}
		for (int index = 0; index < ARRAY_SIZE; index++) {
			pen.fillRect(xPos, SS_Y_START - selectionArray[index], BAR_WIDTH, selectionArray[index]);
			xPos = xPos + BAR_WIDTH + SPACE_APART; // adds width and spaces so bars don't overlap
		}

		xPos = 20; // resets x position 

			if(isInsertionSorted == false) {
				pen.setColor(Color.red);
				pen.drawString("Insertion Sort", xPos, IS_Y_START + 20);
			}
			
		for (int index = 0; index < ARRAY_SIZE; index++) {
			pen.fillRect(xPos, IS_Y_START - insertionArray[index], BAR_WIDTH, insertionArray[index]);
			xPos = xPos + BAR_WIDTH + SPACE_APART; // adds width and spaces so bars don't overlap
		}

		xPos = 20; // resets x position
	}

	private void modifiedSelectionSort() {
		int smallest = indexOfSelection;
		if (indexOfSelection < ARRAY_SIZE - 1) {
			for (int read = indexOfSelection + 1; read < ARRAY_SIZE; read++) {
				if(selectionArray[read] < selectionArray[smallest]) {
					smallest = read;
				}
			}
			int foo = selectionArray[smallest];
			selectionArray[smallest] = selectionArray[indexOfSelection];
			selectionArray[indexOfSelection] = foo;
		} else {
			isSelectionSorted = true;
		}
	}

	private void modifiedInsertionSort() {
		if (indexOfInsertion < ARRAY_SIZE) {
			 int key = insertionArray[indexOfInsertion];
			 int position = indexOfInsertion;
			 while(position > 0 && key < insertionArray[position - 1]) {
				 insertionArray[position] = insertionArray[position - 1];
				 position--;
			 }
			 insertionArray[position] = key;
		} else {
			isInsertionSorted = true;
		}
	}
	
		
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			modifiedSelectionSort();
			indexOfSelection += 1;
			if(indexOfSelection >= ARRAY_SIZE - 1) {
				isSelectionSorted = true;
			}
			modifiedInsertionSort();
			indexOfInsertion++;
			if(indexOfInsertion >= ARRAY_SIZE) {
				isInsertionSorted = true;
			}
			repaint();
		}
	}
	
	public static void main(String[] args) {
		SortGUI panel = new SortGUI();
		JFrame frame = new JFrame("Sort!");
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setSize(700, 350);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}