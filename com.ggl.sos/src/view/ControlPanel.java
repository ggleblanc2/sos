package com.ggl.sos.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.ggl.sos.model.SOSModel;

public class ControlPanel {
	
	private final JPanel panel;
	
	private JLabel playersTurnLabel;
	
	private JTextField player1ScoreField, player2ScoreField;
	
	private final SOSFrame view;
	
	private final SOSModel model;
	
	private SpinnerModel spinnerModel;

	public ControlPanel(SOSFrame view, SOSModel model) {
		this.view = view;
		this.model = model;
		this.panel = createControlPanel();
	}
	
	private JPanel createControlPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		Font titleFont = AppFonts.generateTitleFont();
		Font textFont = AppFonts.generateTextFont();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 5, 5, 5);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		playersTurnLabel = new JLabel();
		playersTurnLabel.setFont(textFont);
		playersTurnLabel.setHorizontalAlignment(JLabel.CENTER);
		updatePlayersTurn();
		panel.add(playersTurnLabel, gbc);
		
		gbc.gridy++;
		JLabel label = new JLabel("Score");
		label.setFont(titleFont);
		label.setHorizontalAlignment(JLabel.CENTER);
		panel.add(label, gbc);
		
		gbc.gridy++;
		panel.add(createScorePanel(), gbc);
		
		gbc.gridy++;
		label = new JLabel("Grid Width");
		label.setFont(titleFont);
		label.setHorizontalAlignment(JLabel.CENTER);
		panel.add(label, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy++;
		label = new JLabel("Width:");
		label.setFont(textFont);
		panel.add(label, gbc);
		
		gbc.gridx++;
		int minimum = model.getDefaultWidth();
		int maximum = model.getMaximumWidth();
		int width = model.getGameboardWidth();
		spinnerModel = new SpinnerNumberModel(width, minimum, maximum, 1);
		JSpinner gridwidthSpinner = new JSpinner(spinnerModel);
		gridwidthSpinner.setFont(textFont);
		panel.add(gridwidthSpinner, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy++;
		JButton button = new JButton("Submit");
		button.setFont(textFont);
		panel.add(button, gbc);
		
		button.addActionListener(event -> {
			int gameboardWidth = (int) spinnerModel.getValue();
			model.setGameboardWidth(gameboardWidth);
			view.calculateCells();
			view.repaint();
		});
		
		return panel;
	}
	
	private JPanel createScorePanel() {
		JPanel panel = new JPanel(new GridLayout(0, 2, 20, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		Font titleFont = AppFonts.generateTitleFont();
		Font textFont = AppFonts.generateTextFont();
		
		player1ScoreField = new JTextField(2);
		player1ScoreField.setEditable(false);
		player1ScoreField.setFont(titleFont);
		player1ScoreField.setHorizontalAlignment(JTextField.CENTER);
		panel.add(player1ScoreField);
		
		player2ScoreField = new JTextField(2);
		player2ScoreField.setEditable(false);
		player2ScoreField.setFont(titleFont);
		player2ScoreField.setHorizontalAlignment(JTextField.CENTER);
		panel.add(player2ScoreField);
		
		JLabel label = new JLabel("Player 1");
		label.setFont(textFont);
		panel.add(label);
		
		label = new JLabel("Player 2");
		label.setFont(textFont);
		panel.add(label);
		
		updateScore();
		
		return panel;
	}
	
	public void updateScore() {
		int[] score = model.getScore();
		player1ScoreField.setText(Integer.toString(score[0]));
		player2ScoreField.setText(Integer.toString(score[1]));
	}
	
	public void updatePlayersTurn() {
		String text = "Player " + model.getPlayerTurn() + "'s turn";
		playersTurnLabel.setText(text);
	}

	public JPanel getPanel() {
		return panel;
	}

}
