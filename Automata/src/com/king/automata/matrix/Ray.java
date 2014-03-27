package com.king.automata.matrix;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.Matrix;

import com.king.automata.model.Location;

public class Ray {
	float[] P0;
	float[] P1;

	public Ray(GL10 gl, int width, int height, float xTouch, float yTouch,
			float x, float y, float z) {
        MatrixGrabber matrixGrabber = new MatrixGrabber();
        matrixGrabber.getCurrentState(gl);
        

        int[] viewport = {0, 0, width, height};

        float[] nearCoOrds = new float[3];
        float[] farCoOrds = new float[3];
        float[] temp = new float[4];
        float[] temp2 = new float[4];
        // get the near and far cords for the click
        float winx = xTouch, winy = (float)viewport[3] - yTouch;
        
        int result = GLU.gluUnProject(winx, winy, 1f, matrixGrabber.mModelView, 0, matrixGrabber.mProjection, 0, viewport, 0, temp, 0);
        Matrix.multiplyMV(temp2, 0, matrixGrabber.mModelView, 0, temp, 0);
        if(result == GL10.GL_TRUE){
            nearCoOrds[0] = temp2[0] / temp2[3];
            nearCoOrds[1] = temp2[1] / temp2[3];
            nearCoOrds[2] = temp2[2] / temp2[3];
        }
        
        result = GLU.gluUnProject(winx, winy, 0, matrixGrabber.mModelView, 0, matrixGrabber.mProjection, 0, viewport, 0, temp, 0);
        Matrix.multiplyMV(temp2,0,matrixGrabber.mModelView, 0, temp, 0);
        if(result == GL10.GL_TRUE){
            farCoOrds[0] = temp2[0] / temp2[3];
            farCoOrds[1] = temp2[1] / temp2[3];
            farCoOrds[2] = temp2[2] / temp2[3];
        }
        this.P0 = nearCoOrds;
        this.P1 = farCoOrds;
    }
	
	/**
	 * Record the current modelView matrix
	 * state. Has the side effect of
	 * setting the current matrix state
	 * to GL_MODELVIEW
	 * @param gl context
	 */
	public float[] getCurrentModelView(GL10 gl)
	{
		float[] mModelView = new float[16];
		getMatrix(gl, GL10.GL_MODELVIEW, mModelView);
		return mModelView;
	}

	/**
	 * Record the current projection matrix
	 * state. Has the side effect of
	 * setting the current matrix state
	 * to GL_PROJECTION
	 * @param gl context
	 */
	public float[] getCurrentProjection(GL10 gl)
	{
		float[] mProjection = new float[16];
		getMatrix(gl, GL10.GL_PROJECTION, mProjection);
		return mProjection;
	}

	/**
	 * Fetches a specific matrix from opengl
	 * @param gl context
	 * @param mode of the matrix
	 * @param mat initialized float[16] array
	 * to fill with the matrix
	 */
	private void getMatrix(GL10 gl, int mode, float[] mat)
	{
		MatrixTrackingGL gl2 = (MatrixTrackingGL) gl;
		gl2.glMatrixMode(mode);
		gl2.getMatrix(mat, 0);
	}

	public Location getClick(float zoom) {
		// P0[0] = x;
		float xCoord = pointSlope(slope(P0[0], P1[0], P0[2], P1[2]), P0[0], P0[2], zoom);
		float yCoord = pointSlope(slope(P0[1], P1[1], P0[2], P1[2]), P0[1], P0[2], zoom);
		return new Location(xCoord, yCoord, zoom);
	}

	private float slope(float r1, float r2, float z1, float z2) {
		return (r2 - r1)/(z2 - z1);
	}
	
	private float pointSlope(float m, float r1, float z1, float requestedZoom) {
		return (m * (requestedZoom - z1)) + r1;
	}
}
