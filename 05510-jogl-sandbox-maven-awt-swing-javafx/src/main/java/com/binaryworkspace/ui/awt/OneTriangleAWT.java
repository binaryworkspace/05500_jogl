package com.binaryworkspace.ui.awt;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.binaryworkspace.models.OneTriangleModel;

/**
 * Renders a basic color triangle using JOGL in a AWT Frame.
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
public class OneTriangleAWT {

	public static void main(String[] args) {
		// GLProfile
		GLProfile glprofile = GLProfile.getDefault();
		GLCapabilities glcapabilities = new GLCapabilities(glprofile);
		
		// GLCanvas
		final GLCanvas glcanvas = new GLCanvas(glcapabilities);
		glcanvas.addGLEventListener(new GLEventListener() {

			@Override
			public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {
				// Mode
				GL2 gl2 = glautodrawable.getGL().getGL2();
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

			@Override
			public void init(GLAutoDrawable glautodrawable) {
			}

			@Override
			public void dispose(GLAutoDrawable glautodrawable) {
			}

			@Override
			public void display(GLAutoDrawable glautodrawable) {
				OneTriangleModel.render(glautodrawable.getGL().getGL2(), glautodrawable.getWidth(), glautodrawable.getHeight());
			}
		});

		// AWT Frame
		final Frame frame = new Frame("AWT-JOGL: One Triangle");
		frame.add(glcanvas);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowevent) {
				frame.remove(glcanvas);
				frame.dispose();
				System.exit(0);
			}
		});
		frame.setSize(640, 480);
		frame.setVisible(true);
	}
}
