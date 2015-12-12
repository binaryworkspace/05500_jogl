package com.binaryworkspace.models;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

/**
 * Common rendering model that produces a basic color triangle.
 * <p>
 * <b>Notes:</b>
 * <ul>
 * <li>This is a modified code example provided by Wade Walker (see links
 * below).
 * <li>Modifications include changing of import statements for JOGL 2.1.5
 * support, minor code formatting and minor comment changes.
 * </ul>
 * 
 * @author Chris Ludka
 *         <p>
 * @see <a href=
 *      "https://jogamp.org/wiki/index.php/Using_JOGL_in_AWT_SWT_and_Swing">
 *      https://jogamp.org/wiki/index.php/Using_JOGL_in_AWT_SWT_and_Swing</a>
 */
public class OneTriangleModel {
	public static void setup(GL2 gl2, int width, int height) {
		// Mode
		gl2.glMatrixMode(GL2.GL_PROJECTION);
		gl2.glLoadIdentity();

		/*
		 * Size the draw space the coordinate system origin residing at the
		 * lower left.
		 */
		GLU glu = new GLU();
		glu.gluOrtho2D(0.0f, width, 0.0f, height);

		// Mode
		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glLoadIdentity();

		// Set GLViewPort
		gl2.glViewport(0, 0, width, height);
	}

	public static void render(GL2 gl2, int width, int height) {
		// Clear
		gl2.glClear(GL.GL_COLOR_BUFFER_BIT);

		// Draw a triangle filling the window
		gl2.glLoadIdentity();
		gl2.glBegin(GL.GL_TRIANGLES);
		gl2.glColor3f(1, 0, 0);
		gl2.glVertex2f(0, 0);
		gl2.glColor3f(0, 1, 0);
		gl2.glVertex2f(width, 0);
		gl2.glColor3f(0, 0, 1);
		gl2.glVertex2f(width / 2, height);
		gl2.glEnd();
	}
}