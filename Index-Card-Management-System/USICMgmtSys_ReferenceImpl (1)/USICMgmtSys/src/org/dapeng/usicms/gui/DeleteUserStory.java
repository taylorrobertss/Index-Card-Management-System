package org.dapeng.usicms.gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
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
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.dapeng.usicms.handler.ProjectLevelConfigs;



public class DeleteUserStory extends JDialog {
	private final JPanel contentPanel = new JPanel();
	public static JTextField userFileTextField;
	int index;

	public DeleteUserStory() {
		// import the project name from projectclass so we can load/ access the project
		JTextField test = CreateProjectDialog.projFileTextField;

		// System.out.println(userFileTextField.getText());

		setBounds(100, 100, 379, 97);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblNewLabel = new JLabel("User Story Name:");

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
						JTextField test = CreateProjectDialog.projFileTextField; //load the project file name
						String fileName= test.getText(); //convert to string
						String s= "/n&-----------&/n"; //this is the end substring 
					
						
						Path path = Paths.get(test.getText());
						String deleteThisUserStory = userFileTextField.getText();  //get the string of the user story the user wnats to delete

							String ContentsOfFile=""; // holds the contents of the project file
						try {
							ContentsOfFile = Files.readString(path, StandardCharsets.ISO_8859_1); // read the file into the
																								// string
					

						} catch (IOException ex) {
							// Handle exception
						}
						if(ContentsOfFile.contains(deleteThisUserStory)) {
						
							ContentsOfFile= ContentsOfFile.replaceAll(deleteThisUserStory+ ".*?" +s, " ");
					
						}
						
						 try {
								FileWriter myWriter = new FileWriter(fileName);
								myWriter.write(ContentsOfFile);
								myWriter.close();
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
