/*******************************************************************************
 * Copyright (C) 2021 dapeng
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 ******************************************************************************/
package org.dapeng.usicms.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.dapeng.usicms.handler.ProjectLevelConfigs;
import org.dapeng.usicms.model.UserStory;
import org.dapeng.usicms.model.UserStoryStatus;
import org.dapeng.usicms.model.UserStoryType;

public class CreateUserStory extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField userStoryNameTextField;
	private JTextField userStoryIDTextField;
	private JTextField estDuratoinTextField;
	private JTextField estEffortTextField;
	private JTextField userStoryCreatorTextField;
	private JTextField storyPointTextField;
	private JComboBox userStoryTypeComboBox;
	private JTextPane userStoryDescTextPane;

	/**
	 * Create the dialog.
	 */
	public CreateUserStory() {
		setResizable(false);
		setBounds(100, 100, 555, 341);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTop = new JPanel();
			contentPanel.add(panelTop, BorderLayout.NORTH);
			panelTop.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			{
				JLabel lblNewLabel = new JLabel("User Story Name:");
				panelTop.add(lblNewLabel);
			}
			{
				userStoryNameTextField = new JTextField();
				panelTop.add(userStoryNameTextField);
				userStoryNameTextField.setColumns(15);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("ID:");
				panelTop.add(lblNewLabel_1);
			}
			{
				userStoryIDTextField = new JTextField();
				panelTop.add(userStoryIDTextField);
				userStoryIDTextField.setColumns(6);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Type:");
				panelTop.add(lblNewLabel_2);
			}
			{
				userStoryTypeComboBox = new JComboBox();
				userStoryTypeComboBox.setModel(new DefaultComboBoxModel(UserStoryType.values()));
				panelTop.add(userStoryTypeComboBox);
			}
		}
		{
			JPanel panelBottom = new JPanel();
			contentPanel.add(panelBottom, BorderLayout.SOUTH);
			panelBottom.setLayout(new GridLayout(2, 4, 0, 0));

			JLabel lblNewLabel_7 = new JLabel("Est. Duration:");
			panelBottom.add(lblNewLabel_7);

			estDuratoinTextField = new JTextField();
			panelBottom.add(estDuratoinTextField);
			estDuratoinTextField.setColumns(10);

			JLabel lblNewLabel_8 = new JLabel("Est. Effort:");
			panelBottom.add(lblNewLabel_8);

			estEffortTextField = new JTextField();
			panelBottom.add(estEffortTextField);
			estEffortTextField.setColumns(10);

			JLabel lblNewLabel_3 = new JLabel("Creator:");
			panelBottom.add(lblNewLabel_3);

			userStoryCreatorTextField = new JTextField();
			panelBottom.add(userStoryCreatorTextField);
			userStoryCreatorTextField.setColumns(10);

			JLabel lblNewLabel_4 = new JLabel("Story Point:");
			panelBottom.add(lblNewLabel_4);

			storyPointTextField = new JTextField();
			panelBottom.add(storyPointTextField);
			storyPointTextField.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.WEST);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblDescription = new JLabel("Description:");
				lblDescription.setBounds(0, 0, 106, 15);
				panel.add(lblDescription);
			}

			userStoryDescTextPane = new JTextPane();
			userStoryDescTextPane.setBounds(0, 23, 523, 166);
			panel.add(userStoryDescTextPane);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
			panel.setLayout(new GridLayout(4, 2, 0, 0));
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UserStory us = new UserStory();
						us.setCreator(userStoryCreatorTextField.getText());
						us.setDuration(Integer.parseInt(estDuratoinTextField.getText()));
						us.setEstimate(Integer.parseInt(estEffortTextField.getText()));
						us.setId(userStoryIDTextField.getText());
						us.setName(userStoryNameTextField.getText());
						us.setStoryPoint(Integer.parseInt(storyPointTextField.getText()));
						us.setType(userStoryTypeComboBox.getSelectedItem().toString());
						us.setDescription(userStoryDescTextPane.getText());
						us.setStatus("ToDo");
						//******
						us.setProject(ProjectLevelConfigs.projectName);
						
						us.addUserStory(us);
						
						USICMS.getInstance().addListModelElement(us, UserStoryStatus.TODO);
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
