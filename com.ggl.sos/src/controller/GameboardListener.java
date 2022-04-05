package com.ggl.sos.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.ggl.sos.model.SOSModel;
import com.ggl.sos.view.SOSFrame;

public class GameboardListener extends MouseAdapter {
	
	private final SOSFrame view;
	
	private final SOSModel model;

	public GameboardListener(SOSFrame view, SOSModel model) {
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		Point point = event.getPoint();
		int gameboardWidth = model.getGameboardWidth();
		int gridWidth = view.getGridWidth();
		int margin = view.getMargin();
		
		if ((point.y - margin) < 0) {
			return;
		}
		
		if ((point.x - margin) < 0) {
			return;
		}
		
		int row = (point.y - margin) / gridWidth;
		int column = (point.x - margin) / gridWidth;
		
		if (row > (gameboardWidth - 1)) {
			return;
		}
		
		if (column > (gameboardWidth - 1)) {
			return;
		}
		
		String[] options = { "S", "O" };
		int result = JOptionPane.showOptionDialog(view.getFrame(),
				"Mark an S or an O?", "Mark Question", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, null);
		if (result >= 0 && model.getGameboard(row, column) == ' ') {
			model.setGameboard(row, column, options[result]);
			if (model.checkSOS()) {
				view.updateScore();
			} else {
				model.nextPlayersTurn();
				view.updatePlayersTurn();
			}
			view.repaint();
			playAnotherGame();
		}
	}

	private void playAnotherGame() {
		if (model.isGameOver()) {
			int result = JOptionPane.showConfirmDialog(view.getFrame(),
					"Would you like to play another game?", "Game Question",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				model.setGameboardWidth(model.getGameboardWidth());
				view.updatePlayersTurn();
				view.updateScore();
				view.repaint();
			} else {
				view.shutdown();
			}
		}
	}

}
