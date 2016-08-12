package com.culshoefer.tomatotracker;

import com.airhacks.afterburner.injection.Injector;
import com.culshoefer.tomatotracker.countdowntimer.CountdownTimer;
import com.culshoefer.tomatotracker.pomodorobase.PomodoroIntervalStateManager;
import com.culshoefer.tomatotracker.pomodorobase.PomodoroState;
import com.culshoefer.tomatotracker.pomodorobase.PomodoroTimeManager;
import com.culshoefer.tomatotracker.views.mainscreen.TimerView;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * TODO extract
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Map<Object, Object> customProperties = new HashMap<>();

        Map<PomodoroState, Integer> intervalTimes = new HashMap<>();
        intervalTimes.put(PomodoroState.WORK, 1200);
        intervalTimes.put(PomodoroState.SHORTBREAK, 300);
        intervalTimes.put(PomodoroState.LONGBREAK, 1200);
        customProperties.put("intervalTimes", intervalTimes);

        customProperties.put("intervalsUntilLongBreak", 4);
        customProperties.put("breakExtension", 30);

        CountdownTimer pomodoroTimer = new CountdownTimer();//TODO set option to set the labelled later
        customProperties.put("pomodoroTimer", pomodoroTimer);

        PomodoroIntervalStateManager pim = new PomodoroIntervalStateManager();
        customProperties.put("pim", pim);

        PomodoroTimeManager ptm = new PomodoroTimeManager();
        customProperties.put("ptm", ptm);

        //TODO somehow grant access to mainscreen about its background

        //TODO inject ShapeRow

        Injector.setConfigurationSource(customProperties::get);

        //TODO make sure to set location via super(?)
        TimerView appView = new TimerView();
        Scene scene = new Scene(appView.getView());
        primaryStage.setTitle("TomatoTracker");
        //final String stageCss = getClass().getResource("mainscreen.css").toExternalForm();
        //scene.getStylesheets().add(stageCss);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
