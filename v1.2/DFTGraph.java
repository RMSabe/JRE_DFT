/*
 * Basic Discrete Fourier Transform (DFT) calculating application for Java Runtime.
 * Version 1.2
 *
 * Author: Rafael Sabe
 * Email: rafaelmsabe@gmail.com
 */

import java.awt.*;

public class DFTGraph extends Canvas
{
	private int width = 0;
	private int height = 0;

	private DFT dft = null;

	public DFTGraph(int width, int height, DFT dft)
	{
		this.width = width;
		this.height = height;
		this.dft = dft;
	}

	@Override
	public void paint(Graphics g)
	{
		/*Store before and after coordinates (g.drawLine())*/

		int[] cx = new int[2];
		int[] cy = new int[2];

		/*Auxiliary floating point coordinates: whole graph, from 0.0 to 1.0, left-right, top-bottom*/

		double fcx = 0.0;
		double fcy = 0.0;

		int nIndex = 0;
		int nLength = 0;

		double[] buffer = null;

		final Color COLOR_1 = Color.RED;
		final Color COLOR_2 = Color.GREEN;
		final Color COLOR_3 = Color.BLUE;
		final Color COLOR_4 = Color.ORANGE;

		final int strOffsetX = 4;
		final int strOffsetY = 14;

		g.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));

		g.setColor(COLOR_1);
		g.drawString("Input", strOffsetX, strOffsetY);

		buffer = this.dft.getBufferInput();
		nLength = buffer.length;

		fcx = 0.0;
		fcy = 0.25 - 0.2*buffer[0];

		cx[0] = (int) Math.round(((double) this.width)*fcx);
		cy[0] = (int) Math.round(((double) this.height)*fcy);

		for(nIndex = 1; nIndex < nLength; nIndex++)
		{
			fcx = 0.5*((double) nIndex)/((double) nLength);
			fcy = 0.25 - 0.2*buffer[nIndex];

			cx[1] = (int) Math.round(((double) this.width)*fcx);
			cy[1] = (int) Math.round(((double) this.height)*fcy);

			g.drawLine(cx[0], cy[0], cx[1], cy[1]);

			cx[0] = cx[1];
			cy[0] = cy[1];
		}

		g.setColor(COLOR_2);
		g.drawString("DFT Magnitude", (this.width/2 + strOffsetX), strOffsetY);

		buffer = this.dft.getBufferTransformMagnitude();
		nLength = buffer.length;

		fcx = 0.5;
		fcy = 0.45 - 0.4*buffer[0];

		cx[0] = (int) Math.round(((double) this.width)*fcx);
		cy[0] = (int) Math.round(((double) this.height)*fcy);

		for(nIndex = 1; nIndex < nLength; nIndex++)
		{
			fcx = 0.5 + 0.5*((double) nIndex)/((double) nLength);
			fcy = 0.45 - 0.4*buffer[nIndex];

			cx[1] = (int) Math.round(((double) this.width)*fcx);
			cy[1] = (int) Math.round(((double) this.height)*fcy);

			g.drawLine(cx[0], cy[0], cx[1], cy[0]);
			/*g.drawLine(cx[1], cy[0], cx[1], cy[1]);*/ /*I recommend commenting out this line of code. Although it makes the graph look prettier, it also makes the graph harder to read.*/

			cx[0] = cx[1];
			cy[0] = cy[1];
		}

		g.setColor(COLOR_3);
		g.drawString("DFT Phase", strOffsetX, (this.height/2 + strOffsetY));

		buffer = this.dft.getBufferTransformPhase();
		nLength = buffer.length;

		fcx = 0.0;

		if(Double.isNaN(buffer[0])) fcy = 0.75;
		else fcy = 0.75 - 0.05*buffer[0];

		cx[0] = (int) Math.round(((double) this.width)*fcx);
		cy[0] = (int) Math.round(((double) this.height)*fcy);

		for(nIndex = 1; nIndex < nLength; nIndex++)
		{
			fcx = 0.5*((double) nIndex)/((double) nLength);

			if(Double.isNaN(buffer[nIndex])) fcy = 0.75;
			else fcy = 0.75 - 0.05*buffer[nIndex];

			cx[1] = (int) Math.round(((double) this.width)*fcx);
			cy[1] = (int) Math.round(((double) this.height)*fcy);

			g.drawLine(cx[0], cy[0], cx[1], cy[0]);
			/*g.drawLine(cx[1], cy[0], cx[1], cy[1]);*/ /*I recommend commenting out this line of code. Although it makes the graph look prettier, it also makes the graph harder to read.*/

			cx[0] = cx[1];
			cy[0] = cy[1];
		}

		g.setColor(COLOR_4);
		g.drawString("Output (Resynth)", (this.width/2 + strOffsetX), (this.height/2 + strOffsetY));

		buffer = this.dft.getBufferOutput();
		nLength = buffer.length;

		fcx = 0.5;
		fcy = 0.75 - 0.2*buffer[0];

		cx[0] = (int) Math.round(((double) this.width)*fcx);
		cy[0] = (int) Math.round(((double) this.height)*fcy);

		for(nIndex = 1; nIndex < nLength; nIndex++)
		{
			fcx = 0.5 + 0.5*((double) nIndex)/((double) nLength);
			fcy = 0.75 - 0.2*buffer[nIndex];

			cx[1] = (int) Math.round(((double) this.width)*fcx);
			cy[1] = (int) Math.round(((double) this.height)*fcy);

			g.drawLine(cx[0], cy[0], cx[1], cy[1]);

			cx[0] = cx[1];
			cy[0] = cy[1];
		}
	}
}

