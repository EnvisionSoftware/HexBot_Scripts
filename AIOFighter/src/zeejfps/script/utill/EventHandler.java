package zeejfps.script.utill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import zeejfps.script.AIOFighter;
import zeejfps.script.gui.MainPanel;

public class EventHandler implements ActionListener, MouseInputListener, KeyListener, FocusListener{

	private MainPanel panel;
	
	public EventHandler(MainPanel panel) {
		this.panel = panel;
	}
	
	//------------------------------KeyListener-------------------------------\\
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource().equals(panel.getInput()) && e.getKeyCode() == KeyEvent.VK_ENTER) {
			addToList();
		} else if (e.getSource().equals(panel.getInput()) && e.getKeyCode() == KeyEvent.VK_DELETE) {
			removeFromList();
		}
		
		if (e.getSource().equals(panel.getList()) && e.getKeyCode() == KeyEvent.VK_DELETE) {
			removeFromList();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//------------------------------KeyListener-------------------------------\\
	
	
	
	//------------------------------ActionListener-----------------------------\\
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//What to do if start Button is pressed.
		if (e.getSource().equals(panel.getStartB())) {	
			int [] ids = new int[panel.getIdsList().size()];
			for (int i = 0; i < ids.length; i ++) {
				ids[i] = panel.getIdsList().get(i);
			}
			AIOFighter.setNpcIds(ids);
			panel.getGUI().setActive(false);			
			return;
		}
		
		//What to do if add Button is pressed.
		if (e.getSource().equals(panel.getAddB())) {
			addToList();
		}
		
		//What to do if del Button is pressed.
		if (e.getSource().equals(panel.getDelB())) {
			removeFromList();
		}
	}
	//------------------------------ActionListener-----------------------------\\
	
	
	
	//------------------------------FocusListener------------------------------\\
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(panel.getInput())) {
			
			panel.getInput().dispatchEvent(new ActionEvent(panel.getAddB(), ActionEvent.ACTION_PERFORMED, null));
			panel.getInput().setText("");
			
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(panel.getList())) {
			panel.getList().clearSelection();
		}
		
	}
	//------------------------------FocusListener-------------------------------\\
	
	
	
	//------------------------------MouseListener-------------------------------\\
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(panel.getInput()) && panel.getInput().getText().equals(panel.getInvalidText())) {
			panel.getInput().setText("");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	//------------------------------MouseListener-------------------------------\\
	
	
	private void addToList() {
		if (!panel.getInput().getText().isEmpty()) {
			
			try {
				int id = Integer.parseInt(panel.getInput().getText());
				
				if (!panel.getIdsList().contains(id)) {
					panel.getIdsList().addElement(id);
				}
				
				panel.getStartB().setEnabled(true);
					
				panel.getInput().setText("");
			} catch (NumberFormatException exception) {

				panel.getInput().setText(panel.getInvalidText());
				
			}
		}
	}
	
	private void removeFromList() {
		
		if (panel.getList().equals(panel.getGUI().getWindow().getFocusOwner())) {
			
			panel.getIdsList().remove(panel.getList().getSelectedIndex());
			
		} else {
			
			if (!panel.getInput().getText().isEmpty()) {
				
				try {
					int id = Integer.parseInt(panel.getInput().getText());			
					int idIndex = panel.getIdsList().indexOf(id);
					
					panel.getIdsList().remove(idIndex);
					
					if (panel.getIdsList().isEmpty()) {
						
						panel.getStartB().setEnabled(false	);
						
					}
					
					panel.getInput().setText("");
				} catch (NumberFormatException exception) {
	
					panel.getInput().setText(panel.getInvalidText());
					
				}
				
			}
		}
	}
}
