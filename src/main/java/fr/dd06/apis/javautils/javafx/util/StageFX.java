package fr.dd06.apis.javautils.javafx.util;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StageFX {
	private static double xOffset = 0;
	private static double yOffset = 0;

	public static void setMovable(Stage stage, BorderPane borderpane, boolean enabled) {

		borderpane.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getSource() == borderpane && enabled == true) {

					if (stage.isMaximized() == false) {
						xOffset = stage.getX() - event.getScreenX();
						yOffset = stage.getY() - event.getScreenY();
					}

				}

			}
		});
		borderpane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getSource() == borderpane && enabled == true) {

					if (stage.isMaximized() == false) {
						stage.setX(event.getScreenX() + xOffset);
						stage.setY(event.getScreenY() + yOffset);
					}

				}

			}
		});
	}

	public static void setMovableWithBorder(Stage stage, BorderPane borderpane, boolean enabled, double bordertopsize,
			double borderbottomsize, double borderrightsize, double borderleftsize) {

		if (enabled == true) {
			double bottomsize2 = stage.getHeight() - borderbottomsize;
			double rightsize2 = stage.getWidth() - borderrightsize;
			borderpane.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

					if (stage.isMaximized() == false) {
						if (event.getSceneY() <= bordertopsize) {
							xOffset = stage.getX() - event.getScreenX();
							yOffset = stage.getY() - event.getScreenY();
						} else if (event.getSceneY() >= bottomsize2 && event.getSceneY() >= stage.getHeight() / 2) {
							xOffset = stage.getX() - event.getScreenX();
							yOffset = stage.getY() - event.getScreenY();
						} else if (event.getSceneX() <= borderleftsize) {
							xOffset = stage.getX() - event.getScreenX();
							yOffset = stage.getY() - event.getScreenY();
						} else if (event.getSceneX() >= rightsize2 && event.getSceneX() >= stage.getWidth() / 2) {
							xOffset = stage.getX() - event.getScreenX();
							yOffset = stage.getY() - event.getScreenY();
						}

					}

				}
			});
			borderpane.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {

					if (stage.isMaximized() == false) {
						if (event.getSceneY() <= bordertopsize) {

							stage.setX(event.getScreenX() + xOffset);
							stage.setY(event.getScreenY() + yOffset);
						} else if (event.getSceneY() >= bottomsize2 && event.getSceneY() >= stage.getHeight() / 2) {

							stage.setX(event.getScreenX() + xOffset);
							stage.setY(event.getScreenY() + yOffset);
						} else if (event.getSceneX() <= borderleftsize) {
							stage.setX(event.getScreenX() + xOffset);
							stage.setY(event.getScreenY() + yOffset);
						} else if (event.getSceneX() >= rightsize2 && event.getSceneX() >= stage.getWidth() / 2) {
							stage.setX(event.getScreenX() + xOffset);
							stage.setY(event.getScreenY() + yOffset);
						}
					}
				}
			});
		}
	}

	public static void setMovableWithBorder(Stage stage, AnchorPane anchorpane, boolean enabled, double bordertopsize,
			double borderbottomsize, double borderrightsize, double borderleftsize) {

		if (enabled == true) {
			double bottomsize2 = stage.getHeight() - borderbottomsize;
			double rightsize2 = stage.getWidth() - borderrightsize;
			anchorpane.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

					if (stage.isMaximized() == false) {
						if (event.getSceneY() <= bordertopsize) {
							xOffset = stage.getX() - event.getScreenX();
							yOffset = stage.getY() - event.getScreenY();
						} else if (event.getSceneY() >= bottomsize2 && event.getSceneY() >= stage.getHeight() / 2) {
							xOffset = stage.getX() - event.getScreenX();
							yOffset = stage.getY() - event.getScreenY();
						} else if (event.getSceneX() <= borderleftsize) {
							xOffset = stage.getX() - event.getScreenX();
							yOffset = stage.getY() - event.getScreenY();
						} else if (event.getSceneX() >= rightsize2 && event.getSceneX() >= stage.getWidth() / 2) {
							xOffset = stage.getX() - event.getScreenX();
							yOffset = stage.getY() - event.getScreenY();
						}

					}

				}
			});
			anchorpane.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {

					if (stage.isMaximized() == false) {
						if (event.getSceneY() <= bordertopsize) {

							stage.setX(event.getScreenX() + xOffset);
							stage.setY(event.getScreenY() + yOffset);
						} else if (event.getSceneY() >= bottomsize2 && event.getSceneY() >= stage.getHeight() / 2) {

							stage.setX(event.getScreenX() + xOffset);
							stage.setY(event.getScreenY() + yOffset);
						} else if (event.getSceneX() <= borderleftsize) {
							stage.setX(event.getScreenX() + xOffset);
							stage.setY(event.getScreenY() + yOffset);
						} else if (event.getSceneX() >= rightsize2 && event.getSceneX() >= stage.getWidth() / 2) {
							stage.setX(event.getScreenX() + xOffset);
							stage.setY(event.getScreenY() + yOffset);
						}
					}
				}
			});
		}
	}

}
