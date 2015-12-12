package com.binaryworkspace.ui.swing;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import com.binaryworkspace.models.OneTriangleModel;

/**
 * Renders a basic color triangle using JOGL GLCanvas in a Swing JFrame.
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
public class OneTriangleSwing_GLCanvas {

	public static void main(String[] args) {
		// GLProfile
		GLProfile glprofile = GLProfile.getDefault();
		GLCapabilities glcapabilities = new GLCapabilities(glprofile);
		
		// GLCanvas
		final GLCanvas glcanvas = new GLCanvas(glcapabilities);
		glcanvas.addGLEventListener(new GLEventListener() {

			@Override
			public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {
				OneTriangleModel.setup(glautodrawable.getGL().getGL2(), width, height);
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

		// JFrame
		final JFrame jframe = new JFrame("Swing-JOGL: One Triangle");
		jframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowevent) {
				jframe.dispose();
				System.exit(0);
			}
		});
		jframe.getContentPane().add(glcanvas, BorderLayout.CENTER);
		jframe.setSize(640, 480);
		jframe.setVisible(true);
	}
}
