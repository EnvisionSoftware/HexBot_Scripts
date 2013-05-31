package zeejfps.script.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

import zeejfps.script.utill.EventHandler;

public class MainPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final DefaultListModel<Integer> idsList = new DefaultListModel<Integer>();
	private final EventHandler eventHandler = new EventHandler(this);
	private final String invalidText = "Invalid!";
	private final GUI gui;
	private JList<Integer> list;
	private JTextField input;
	private JButton addB, delB, startB;
	private Font inputFont = new Font(Font.SANS_SERIF, Font.PLAIN, 26);
	
	public MainPanel(GUI gui) {
		
		this.gui = gui;
		
		setPreferredSize(GUI.SIZE);
		setLayout(new BorderLayout());
		//setBorder(BorderFactory.createEtchedBorder());
		
		//------------------Setup Left Panel--------------------\\
			JPanel left = new JPanel();
			left.setLayout(new BorderLayout());
			left.setBorder(BorderFactory.createTitledBorder("IDs List"));
			left.setPreferredSize(new Dimension(125, left.getHeight()));
		
			list = new JList<Integer>(idsList);
			list.addKeyListener(eventHandler);
			list.addFocusListener(eventHandler);
			list.setFocusable(true);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			left.add(scrollPane, BorderLayout.CENTER);

		add(left, BorderLayout.WEST);
		
		//-------------------Setup RightPanel----------------------\\
			JPanel right = new JPanel();
			right.setLayout(new GridBagLayout());
			right.setBorder(BorderFactory.createTitledBorder("Add Npc IDs"));
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1.0;
			c.weighty = 1.0;
			
			//Setup input
			input = new JTextField(6);
			input.setFocusable(true);
			input.addFocusListener(eventHandler);
			input.addKeyListener(eventHandler);
			input.addMouseListener(eventHandler);
			input.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			input.setHorizontalAlignment(JTextField.CENTER);
			input.setFont(inputFont);

			c.gridwidth = 2;
			c.gridy = 0;
			right.add(input, c);
		
			//Setup Add Button and Delete Buttons
			addB = new JButton("Add");
			addB.setFocusable(false);
			addB.addActionListener(eventHandler);
			delB = new JButton("Delete");
			delB.setFocusable(false);
			delB.addActionListener(eventHandler);
			
			c.gridwidth = 1;
			c.gridy = 1;
			//c.insets = new Insets(0, 0, 4, 0);
			right.add(addB, c);
			right.add(delB, c);
		
		add(right, BorderLayout.CENTER);
		
		//------------------Setup Button Panel--------------------------\\
			JPanel bottom = new JPanel();
			bottom.setLayout(new BorderLayout());
			
			//Setup startButton
			startB = new JButton("Start");
			startB.setPreferredSize(new Dimension(right.getWidth(), 50));
			startB.setEnabled(false);
			startB.addActionListener(eventHandler);
			bottom.add(startB, BorderLayout.CENTER);
		
		add(bottom, BorderLayout.SOUTH);
	}

	//Getters and Setters
	public JList<Integer> getList() {
		return list;
	}

	public void setList(JList<Integer> list) {
		this.list = list;
	}

	public JTextField getInput() {
		return input;
	}

	public void setInput(JTextField input) {
		this.input = input;
	}

	public JButton getAddB() {
		return addB;
	}

	public void setAddB(JButton addB) {
		this.addB = addB;
	}

	public JButton getDelB() {
		return delB;
	}

	public void setDelB(JButton delB) {
		this.delB = delB;
	}

	public JButton getStartB() {
		return startB;
	}

	public void setStartB(JButton startB) {
		this.startB = startB;
	}

	public DefaultListModel<Integer> getIdsList() {
		return idsList;
	}
	
	public GUI getGUI() {
		return gui;
	}
	
	public String getInvalidText() {
		return invalidText;
	}
}
