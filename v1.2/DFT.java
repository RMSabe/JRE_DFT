/*
 * Basic Discrete Fourier Transform (DFT) calculating application for Java Runtime.
 * Version 1.2
 *
 * Author: Rafael Sabe
 * Email: rafaelmsabe@gmail.com
 */

public class DFT
{
	private static final int BUFFER_SIZE_DEFAULT = 256;

	private int bufferSize = 0;
	private int bufferHalfSize = 0;

	private double xStep = 0.0;

	private double[] bufferInput = null;
	private double[] bufferCplxR = null;
	private double[] bufferCplxI = null;
	private double[] bufferTrnfMag = null;
	private double[] bufferTrnfPhase = null;
	private double[] bufferOutput = null;

	public DFT()
	{
		this.bufferSize = BUFFER_SIZE_DEFAULT;
		this.init();
	}

	public DFT(int bufferSize)
	{
		this.bufferSize = bufferSize;
		this.init();
	}

	public double getXStep()
	{
		return this.xStep;
	}

	public double[] getBufferInput()
	{
		return this.bufferInput;
	}

	public double[] getBufferTransformMagnitude()
	{
		return this.bufferTrnfMag;
	}

	public double[] getBufferTransformPhase()
	{
		return this.bufferTrnfPhase;
	}

	public double[] getBufferOutput()
	{
		return this.bufferOutput;
	}

	public boolean runCalc()
	{
		double x = 0.0;
		double xRelStep = 0.0;

		int nIndex = 0;
		int nCplxIndex = 0;

		if((this.bufferSize <= 0) || (this.bufferHalfSize <= 0)) return false;

		for(nIndex = 0; nIndex < this.bufferSize; nIndex++) this.bufferOutput[nIndex] = 0.0;

		for(nCplxIndex = 0; nCplxIndex < this.bufferHalfSize; nCplxIndex++)
		{
			this.bufferCplxR[nCplxIndex] = 0.0;
			this.bufferCplxI[nCplxIndex] = 0.0;
			this.bufferTrnfMag[nCplxIndex] = 0.0;
			this.bufferTrnfPhase[nCplxIndex] = 0.0;
		}

		for(nCplxIndex = 0; nCplxIndex < this.bufferHalfSize; nCplxIndex++)
		{
			x = 0.0;
			xRelStep = this.xStep*((double) nCplxIndex);

			for(nIndex = 0; nIndex < this.bufferSize; nIndex++)
			{
				this.bufferCplxR[nCplxIndex] += Math.cos(x)*this.bufferInput[nIndex];
				this.bufferCplxI[nCplxIndex] += Math.sin(x)*this.bufferInput[nIndex];

				x += xRelStep;
			}

			this.bufferCplxR[nCplxIndex] /= (double) this.bufferHalfSize;
			this.bufferCplxI[nCplxIndex] /= (double) this.bufferHalfSize;
		}

		for(nCplxIndex = 0; nCplxIndex < this.bufferHalfSize; nCplxIndex++)
		{
			this.bufferTrnfMag[nCplxIndex] = Math.sqrt(this.bufferCplxR[nCplxIndex]*this.bufferCplxR[nCplxIndex] + this.bufferCplxI[nCplxIndex]*this.bufferCplxI[nCplxIndex]);
			this.bufferTrnfPhase[nCplxIndex] = Math.atan2(this.bufferCplxI[nCplxIndex], this.bufferCplxR[nCplxIndex]);
		}

		for(nCplxIndex = 0; nCplxIndex < this.bufferHalfSize; nCplxIndex++)
		{
			x = 0.0;
			xRelStep = this.xStep*((double) nCplxIndex);

			for(nIndex = 0; nIndex < this.bufferSize; nIndex++)
			{
				this.bufferOutput[nIndex] += this.bufferTrnfMag[nCplxIndex]*Math.cos(x - this.bufferTrnfPhase[nCplxIndex]);

				x += xRelStep;
			}
		}

		return true;
	}

	private void init()
	{
		this.bufferHalfSize = this.bufferSize/2;
		this.xStep = 2.0*Math.PI/((double) this.bufferSize);

		this.bufferInput = new double[this.bufferSize];
		this.bufferCplxR = new double[this.bufferHalfSize];
		this.bufferCplxI = new double[this.bufferHalfSize];
		this.bufferTrnfMag = new double[this.bufferHalfSize];
		this.bufferTrnfPhase = new double[this.bufferHalfSize];
		this.bufferOutput = new double[this.bufferSize];
	}
}

