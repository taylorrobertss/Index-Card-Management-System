/*
 * Project is a development of the demo provided by Dapend Dong
 * Instructions:
 * 1. Run the Program
 * 2. Create a project with a name and extension of .txt . For example Project.txt
 * 3. Create as many user stories as you like
 * 4. Delete a user story by enetering the name of the user story, once you press ok you can see that the user story data is deleted from the txt file.
 * 4. Load a project text file by entering the project file name 
 * 5. to modify a user story clock the "modify story" menu item and enter the project file you want to open. You can edit the contents of the file and save.
 * 
 */
package org.dapeng.usicms.gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import org.dapeng.usicms.handler.ListTransferHandler;
import org.dapeng.usicms.model.UserStory;
import org.dapeng.usicms.model.UserStoryStatus;

public class USICMS {

	private JFrame frame;
	private final Action actionCreateProject = new SwingActionCreateProject();
	private final Action actionCreateUserStory = new SwingActionCreateUserStory();
	private final Action actionDeleteUserStory = new SwingActionDeleteUserStory();
	private final Action actionModifyUserStory = new SwingActionModifyUserStory();
	private final Action actionLoadProject = new SwingActionLoadProject();

	private DefaultListModel listInProgressModel = new DefaultListModel();
	private DefaultListModel listDoneModel = new DefaultListModel();
	private DefaultListModel listToDoModel = new DefaultListModel();
	private static final USICMS SINGLE_USICMS = new USICMS();

	public static USICMS getInstance() {
		return SINGLE_USICMS;
	}

	public void addListModelElement(UserStory us, UserStoryStatus uss) {
		switch (uss) {
		case TODO:
			listToDoModel.addElement(us.getId() + "--" + us.getName());
			break;
		case INPROGRESS:
			listInProgressModel.addElement(us.getId() + "--" + us.getName());
			break;
		case DONE:
			listDoneModel.addElement(us.getId() + "--" + us.getName());
			break;
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					USICMS.getInstance().frame.setVisible(true);
					// USICMS window = new USICMS();
					// window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public USICMS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 530, 295);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNew = new JMenu("Project");
		menuBar.add(mnNew);

		JMenuItem mntmNewProject = new JMenuItem("New Project");
		mntmNewProject.setAction(actionCreateProject);
		mnNew.add(mntmNewProject);

		JMenuItem mntmLoadProject = new JMenuItem("Load Project");
		mntmLoadProject.setAction(actionLoadProject);
		mnNew.add(mntmLoadProject);

		JMenu mnUserStory = new JMenu("User Story");
		menuBar.add(mnUserStory);

		JMenuItem mntmCreateUserStory = new JMenuItem("Create User Story");
		mntmCreateUserStory.setAction(actionCreateUserStory);
		mnUserStory.add(mntmCreateUserStory);
		
		JMenuItem mntmDeleteUserStory = new JMenuItem("Delete User Story");
		mntmDeleteUserStory.setAction(actionDeleteUserStory);
		mnUserStory.add(mntmDeleteUserStory);
		
		JMenuItem mntmModifyUserStory = new JMenuItem("Modify User Story");
		mnUserStory.add(mntmModifyUserStory);
		mntmModifyUserStory.setAction(actionModifyUserStory);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panelToDo = new JPanel();
		frame.getContentPane().add(panelToDo);
		panelToDo.setLayout(null);

		JLabel lblTodo = new JLabel("ToDo");
		lblTodo.setBounds(58, 0, 57, 24);
		panelToDo.add(lblTodo);

		JList listToDo = new JList(listToDoModel);
		listToDo.setName("ToDo");
		listToDo.setBounds(0, 24, 164, 226);
		listToDo.setVisibleRowCount(12);
		listToDo.setDragEnabled(true);
		listToDo.setTransferHandler(new ListTransferHandler());
		listToDo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					DisplayUserStory dialog = new DisplayUserStory((String) list.getModel().getElementAt(index));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});

		listToDo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelToDo.add(listToDo);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mntmNewMenuItem.setBounds(45, 69, 137, 26);
		panelToDo.add(mntmNewMenuItem);

		JPanel panelInProgress = new JPanel();
		frame.getContentPane().add(panelInProgress);
		panelInProgress.setLayout(null);

		JLabel lblInProgress = new JLabel("In Progress");
		lblInProgress.setBounds(47, 5, 81, 15);
		panelInProgress.add(lblInProgress);

		JList listInProgress = new JList(listInProgressModel);
		listInProgress.setName("InProgress");
		listInProgress.setDragEnabled(true);
		listInProgress.setTransferHandler(new ListTransferHandler());
		listInProgress.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					DisplayUserStory dialog = new DisplayUserStory((String) list.getModel().getElementAt(index));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});

		listInProgress.setBounds(0, 25, 176, 226);
		panelInProgress.add(listInProgress);

		JPanel panelDone = new JPanel();
		frame.getContentPane().add(panelDone);
		panelDone.setLayout(null);

		JLabel lblDone = new JLabel("Done");
		lblDone.setBounds(69, 5, 37, 15);
		panelDone.add(lblDone);

		JList listDone = new JList(listDoneModel);
		listDone.setName("Done");
		listDone.setDragEnabled(true);
		listDone.setTransferHandler(new ListTransferHandler());
		listDone.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					DisplayUserStory dialog = new DisplayUserStory((String) list.getModel().getElementAt(index));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});
		listDone.setBounds(12, 25, 164, 226);
		panelDone.add(listDone);
	}

	private class SwingActionCreateProject extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SwingActionCreateProject() {
			putValue(NAME, "New Project");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				CreateProjectDialog dialog = new CreateProjectDialog();

				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	private class SwingActionCreateUserStory extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SwingActionCreateUserStory() {
			putValue(NAME, "Create User Story");
		}

		public void actionPerformed(ActionEvent e) {

			CreateUserStory dialog = new CreateUserStory();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}
	
	private class SwingActionDeleteUserStory extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public SwingActionDeleteUserStory() {
			putValue(NAME, "Delete User Story");
		}
		
		public void actionPerformed(ActionEvent e) {

			DeleteUserStory dialog = new DeleteUserStory();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}
	
	private class SwingActionModifyUserStory extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public SwingActionModifyUserStory() {
			putValue(NAME, "Modify User Story");
		}
		
		public void actionPerformed(ActionEvent e) {

			ModifyUserStory dialog = new ModifyUserStory();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}
	private class SwingActionLoadProject extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public SwingActionLoadProject() {
			putValue(NAME, "Load Project");
		}
		
		public void actionPerformed(ActionEvent e) {

			LoadProject dialog = new LoadProject();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}
}
