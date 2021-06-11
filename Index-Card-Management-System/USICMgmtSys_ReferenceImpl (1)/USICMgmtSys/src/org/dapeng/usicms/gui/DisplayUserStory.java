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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.dapeng.usicms.handler.ProjectLevelConfigs;
import org.dapeng.usicms.model.UserStory;

public class DisplayUserStory extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField userStoryNameTextField;
	private JTextField userStoryIDTextField;
	private JTextField estDuratoinTextField;
	private JTextField estEffortTextField;
	private JTextField userStoryCreatorTextField;
	private JTextField storyPointTextField;
	private JTextPane userStoryDescTextPane;
	private JTextField viewTypeTextField;
	private JTextField viwStatusTextField;

	/**
	 * Create the dialog.
	 */
	public DisplayUserStory(String idName) {
		String id = idName.split("--")[0];
		UserStory us = new UserStory();

		for (UserStory singleUs : ProjectLevelConfigs.userStories) {
			if (singleUs.getId().equalsIgnoreCase(id))
				us = singleUs;
		}

		setResizable(false);
		setBounds(100, 100, 555, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTop = new JPanel();
			contentPanel.add(panelTop, BorderLayout.NORTH);
			panelTop.setLayout(new GridLayout(0, 6, 0, 0));
			{
				JLabel lblNewLabel = new JLabel("Story Name:");
				panelTop.add(lblNewLabel);
			}
			{
				userStoryNameTextField = new JTextField(us.getName());
				userStoryNameTextField.setEditable(false);
				panelTop.add(userStoryNameTextField);
				userStoryNameTextField.setColumns(15);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("ID:");
				panelTop.add(lblNewLabel_1);
			}
			{
				userStoryIDTextField = new JTextField(us.getId());
				userStoryIDTextField.setEditable(false);
				panelTop.add(userStoryIDTextField);
				userStoryIDTextField.setColumns(6);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Type:");
				panelTop.add(lblNewLabel_2);
			}
			{
				viewTypeTextField = new JTextField(us.getType());
				viewTypeTextField.setEditable(false);
				panelTop.add(viewTypeTextField);
				viewTypeTextField.setColumns(10);
			}
			{
				JLabel label = new JLabel("");
				panelTop.add(label);
			}
			{
				JLabel label = new JLabel("");
				panelTop.add(label);
			}
			{
				JLabel label = new JLabel("");
				panelTop.add(label);
			}
			{
				JLabel label = new JLabel("");
				panelTop.add(label);
			}
		}
		{
			JPanel panelBottom = new JPanel();
			contentPanel.add(panelBottom, BorderLayout.SOUTH);
			panelBottom.setLayout(new GridLayout(3, 4, 0, 0));

			JLabel lblNewLabel_7 = new JLabel("Est. Duration:");
			panelBottom.add(lblNewLabel_7);

			estDuratoinTextField = new JTextField(String.valueOf(us.getDuration()));
			estDuratoinTextField.setEditable(false);
			panelBottom.add(estDuratoinTextField);
			estDuratoinTextField.setColumns(10);

			JLabel lblNewLabel_8 = new JLabel("Est. Effort:");
			panelBottom.add(lblNewLabel_8);

			estEffortTextField = new JTextField(String.valueOf(us.getEstimate()));
			estEffortTextField.setEditable(false);
			panelBottom.add(estEffortTextField);
			estEffortTextField.setColumns(10);

			JLabel lblNewLabel_3 = new JLabel("Creator:");
			panelBottom.add(lblNewLabel_3);

			userStoryCreatorTextField = new JTextField(us.getCreator());
			userStoryCreatorTextField.setEditable(false);
			panelBottom.add(userStoryCreatorTextField);
			userStoryCreatorTextField.setColumns(10);

			JLabel lblNewLabel_4 = new JLabel("Story Point:");
			panelBottom.add(lblNewLabel_4);

			storyPointTextField = new JTextField(String.valueOf(us.getStoryPoint()));
			storyPointTextField.setEditable(false);
			panelBottom.add(storyPointTextField);
			storyPointTextField.setColumns(10);
			{
				JLabel lblNewLabel_5 = new JLabel("Status:");
				panelBottom.add(lblNewLabel_5);
			}

			viwStatusTextField = new JTextField(us.getStatus());
			viwStatusTextField.setEditable(false);
			panelBottom.add(viwStatusTextField);
			viwStatusTextField.setColumns(10);
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
			userStoryDescTextPane.setText(us.getDescription());
			userStoryDescTextPane.setEditable(false);
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
				JButton cancelButton = new JButton("Close");
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
