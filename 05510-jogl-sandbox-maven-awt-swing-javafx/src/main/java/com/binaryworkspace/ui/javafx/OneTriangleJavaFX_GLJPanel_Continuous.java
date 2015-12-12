package com.binaryworkspace.ui.javafx;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.SwingUtilities;

import com.binaryworkspace.models.OneTriangleModel;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Renders a basic color triangle using JOGL in a JavaFX SwingNode. To address
 * repaint issues this leverages a continuous rendering technique presented for
 * SWT.
 * <p>
 * <b>Notes:</b>
 * <ul>
 * <li>This is a modified code of several examples provided by Wade Walker (see
 * links below).
 * <li>Modifications include changing of import statements for JOGL 2.1.5
 * support, minor code formatting, minor comment changes and merging of code
 * examples from SWT.
 * </ul>
 * 
 * @author Chris Ludka
 *         <p>
 * @see <a href=
 *      "https://jogamp.org/wiki/index.php/Using_JOGL_in_AWT_SWT_and_Swing">
 *      https://jogamp.org/wiki/index.php/Using_JOGL_in_AWT_SWT_and_Swing</a>
 * @see <a href=
 *      "https://jogamp.org/wiki/index.php/Using_JOGL_in_AWT_SWT_and_Swing">
 *      https://wadeawalker.wordpress.com/2010/10/09/tutorial-a-cross-platform-
 *      workbench-program-using-java-opengl-and-eclipse/</a>
 */
public class OneTriangleJavaFX_GLJPanel_Continuous extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Root
		final StackPane root = new StackPane();
		final SwingNode swingNode = new SwingNode();
		root.getChildren().add(swingNode);

		// Scene
		Scene scene = new Scene(root, 250, 150);

		// Stage
		stage.setTitle("JavaFX-JOGL: One Triangle");
		stage.setScene(scene);
		stage.show();

		// Content
		createSwingContent(swingNode);
	}

	private void createSwingContent(final SwingNode swingNode) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// GLProfile
				final GLProfile glprofile = GLProfile.getDefault();
				final GLCapabilities glcapabilities = new GLCapabilities(glprofile);

				// GLJPanel
				final GLJPanel gljpanel = new GLJPanel(glcapabilities);
				gljpanel.addGLEventListener(new GLEventListener() {

					@Override
					public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {
						OneTriangleModel.setup(glautodrawable.getGL().getGL2(), glautodrawable.getWidth(), glautodrawable.getHeight());
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
				swingNode.setContent(gljpanel);

				// Create render thread
				(new Thread() {
					public void run() {
						while (true) {
							gljpanel.repaint();
							try {
								/*
								 * Don't make loop too tight, or not enough time
								 * to process window messages properly.
								 */
								sleep(1);
							} catch (InterruptedException interruptedexception) {
								/*
								 * Application just quits on interrupt, so
								 * nothing required here.
								 */
							}
						}
					}
				}).start();
			}
		});
	}
}