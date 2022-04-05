package com.ggl.sos.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class SOSModel {
	
	private final int defaultWidth, maximumWidth;
	private int gameboardWidth, playerTurn;
	private int[] score;
	
	private char[][] gameboard;
	
	private List<LineSegment> lineSegments;
	
	public SOSModel() {
		this.defaultWidth = 3;
		this.maximumWidth = 10;
		this.gameboardWidth = defaultWidth;
		this.gameboard = initializeGameboard(gameboardWidth);
		this.lineSegments = new ArrayList<>();
		this.playerTurn = 1;
		this.score = new int[2];
		
	}

	public int getGameboardWidth() {
		return gameboardWidth;
	}

	public void setGameboardWidth(int gameboardWidth) {
		this.gameboardWidth = gameboardWidth;
		this.gameboard = initializeGameboard(gameboardWidth);
		this.lineSegments.clear();
		this.playerTurn = 1;
		this.score = new int[2];
	}
	
	private char[][] initializeGameboard(int gameboardWidth) {
		char[][] gameboard = new char[gameboardWidth][gameboardWidth];
		
		for (int row = 0; row < gameboardWidth; row++) {
			for (int column = 0; column < gameboardWidth; column++) {
				gameboard[row][column] = ' ';
			}
		}
		
		return gameboard;
	}
	
	public void setGameboard(int row, int column, String letter) {
		this.gameboard[row][column] = letter.charAt(0);
	}
	
	public char getGameboard(int row, int column) {
		return gameboard[row][column];
	}

	public char[][] getGameboard() {
		return gameboard;
	}
	
	public boolean isGameOver() {
		for (int row = 0; row < gameboardWidth; row++) {
			for (int column = 0; column < gameboardWidth; column++) {
				if (gameboard[row][column] == ' ') {
					return false;
				}
			}
		}
		
		return true;
	}

	public int getDefaultWidth() {
		return defaultWidth;
	}

	public int getMaximumWidth() {
		return maximumWidth;
	}
	
	public void nextPlayersTurn() {
		this.playerTurn = (playerTurn == 1) ? 2 : 1;
	}

	public int getPlayerTurn() {
		return playerTurn;
	}
	
	public boolean checkSOS() {
		boolean sosFound = false;
		for (int row = 0; row < gameboardWidth; row++) {
			for (int column = 0; column < gameboardWidth; column++) {
				if (checkEast(row, column)) sosFound = true;
				if (checkSoutheast(row, column)) sosFound = true;
				if (checkSouth(row, column)) sosFound = true;
				if (checkSouthwest(row, column)) sosFound = true;
			}
		}
		
		return sosFound;
	}
	
	private boolean checkEast(int row, int column) {
		Color[] lineColor = { Color.RED, Color.BLUE };
		if (isCharacter('S', row, column)
				&& isCharacter('O', row, column + 1)
				&& isCharacter('S', row, column + 2)) {
			Coordinate start = new Coordinate(row, column);
			Coordinate end = new Coordinate(row, column + 2);
			Color color = lineColor[playerTurn - 1];
			if (addLineSegment(new LineSegment(color, start, end))) {
				incrementScore(playerTurn);
			}
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkSoutheast(int row, int column) {
		Color[] lineColor = { Color.RED, Color.BLUE };
		if (isCharacter('S', row, column)
				&& isCharacter('O', row + 1, column + 1)
				&& isCharacter('S', row + 2, column + 2)) {
			Coordinate start = new Coordinate(row, column);
			Coordinate end = new Coordinate(row + 2, column + 2);
			Color color = lineColor[playerTurn - 1];
			if (addLineSegment(new LineSegment(color, start, end))) {
				incrementScore(playerTurn);
			}
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkSouth(int row, int column) {
		Color[] lineColor = { Color.RED, Color.BLUE };
		if (isCharacter('S', row, column)
				&& isCharacter('O', row + 1, column)
				&& isCharacter('S', row + 2, column)) {
			Coordinate start = new Coordinate(row, column);
			Coordinate end = new Coordinate(row + 2, column);
			Color color = lineColor[playerTurn - 1];
			if (addLineSegment(new LineSegment(color, start, end))) {
				incrementScore(playerTurn);
			}
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkSouthwest(int row, int column) {
		Color[] lineColor = { Color.RED, Color.BLUE };
		if (isCharacter('S', row, column)
				&& isCharacter('O', row + 1, column - 1)
				&& isCharacter('S', row + 2, column - 2)) {
			Coordinate start = new Coordinate(row, column);
			Coordinate end = new Coordinate(row + 2, column - 2);
			Color color = lineColor[playerTurn - 1];
			if (addLineSegment(new LineSegment(color, start, end))) {
				incrementScore(playerTurn);
			}
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isCharacter(char c, int row, int column) {
		if (row < 0 || row > (gameboardWidth - 1)) {
			return false;
		}
		
		if (column < 0 || column > (gameboardWidth - 1)) {
			return false;
		}
		
		if (gameboard[row][column] == c) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean addLineSegment(LineSegment lineSegment) {
		LineSegment complimentSegment = new LineSegment(lineSegment.getLineColor(),
				lineSegment.getEndCoordinate(), lineSegment.getStartCoordinate());
		if (contains(lineSegment) || contains(complimentSegment)) {
			return false;
		} else {
			lineSegments.add(lineSegment);
			return true;
		}
	}
	
	private boolean contains(LineSegment lineSegment) {
		for (LineSegment segment : lineSegments) {
			if ((segment.getStartCoordinate()
					.equals(lineSegment.getStartCoordinate()))
					&& (segment.getEndCoordinate()
							.equals(lineSegment.getEndCoordinate()))) {
				return true;
			}
		}

		return false;
	}

	public List<LineSegment> getLineSegments() {
		return lineSegments;
	}
	
	private void incrementScore(int player) {
		this.score[player - 1]++;
	}

	public int[] getScore() {
		return score;
	}

}
