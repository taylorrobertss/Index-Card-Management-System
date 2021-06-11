package org.dapeng.usicms.gui;


import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.dapeng.usicms.handler.ProjectLevelConfigs;
import org.dapeng.usicms.model.UserStory;
import org.dapeng.usicms.model.UserStoryStatus;
import org.dapeng.usicms.model.UserStoryType;
public class LoadProject extends JDialog{
	private final JPanel contentPanel = new JPanel();
	public static JTextField userFileTextField;
public LoadProject() {
	//JTextField test = CreateProjectDialog.projFileTextField;

	// System.out.println(userFileTextField.getText());

	setBounds(100, 100, 379, 97);
	getContentPane().setLayout(new BorderLayout());
	contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(contentPanel, BorderLayout.CENTER);
	JLabel lblNewLabel = new JLabel("Project Name:");

	userFileTextField = new JTextField();
	userFileTextField.setColumns(10);
	GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
	gl_contentPanel
			.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED).addComponent(userFileTextField,
									GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(84, Short.MAX_VALUE)));
	gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
							.addComponent(userFileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
									GroupLayout.PREFERRED_SIZE))
					.addContainerGap(217, Short.MAX_VALUE)));
	contentPanel.setLayout(gl_contentPanel);
	{
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JTextField loadThisProject = userFileTextField;
					Desktop desktop = Desktop.getDesktop();
					File fileName = new File(loadThisProject.getText());
					 try {
							desktop.open(fileName);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					dispose();
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();

				}
			});
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}
	}
}
}
