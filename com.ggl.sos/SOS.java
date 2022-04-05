package com.ggl.sos;

import javax.swing.SwingUtilities;

import com.ggl.sos.model.SOSModel;
import com.ggl.sos.view.SOSFrame;

public class SOS implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new SOS());
	}

	@Override
	public void run() {
		new SOSFrame(new SOSModel());
	}

}
