package fr.dd06.apis.javautils.javafx.animation;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.WritableValue;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimatorFX {
	/**
	 * A slow speed for animations
	 */
	public static final double SLOW = 2500;

	/**
	 * A normal speed for animations
	 */
	public static final double NORMAL = 1000;

	/**
	 * A fast speed for animations
	 */
	public static final double FAST = 500;
	

	public static void fadeInFrameFX(Stage toFade, double duration) {
		Timeline timeline = new Timeline();
		KeyValue transparent = new KeyValue(toFade.opacityProperty(), 0.0);
		KeyValue opaque = new KeyValue(toFade.opacityProperty(), 1.0);

		KeyFrame startFadeIn = new KeyFrame(Duration.ZERO, transparent);
		KeyFrame endFadeIn = new KeyFrame(Duration.millis(duration), opaque);

		timeline.getKeyFrames().addAll(startFadeIn, endFadeIn);

		timeline.play();
	}

	public static void fadeOutFrameFX(Stage toFade, double duration) {

		Timeline timeline = new Timeline();
		KeyFrame key = new KeyFrame(Duration.millis(duration), new KeyValue(toFade.opacityProperty(), 0));
		timeline.getKeyFrames().add(key);
		timeline.setOnFinished((exit) -> toFade.close());
		timeline.play();
	}

	public static void inconify(Stage toIconify) {

		 
		Timeline timeline = new Timeline();
		KeyValue transparent = new KeyValue(toIconify.opacityProperty(), 0.0);

		KeyValue opaque = new KeyValue(toIconify.opacityProperty(), 1.0);

		KeyFrame startIconify = new KeyFrame(Duration.ZERO, opaque);

		KeyFrame endIconify = new KeyFrame(Duration.millis(135), transparent);
		
		
		timeline.getKeyFrames().addAll(startIconify, endIconify);
		timeline.setOnFinished((event -> {
			toIconify.setIconified(true);
			toIconify.setOpacity(1.0);
		}));
		timeline.play();
	}

	

}
