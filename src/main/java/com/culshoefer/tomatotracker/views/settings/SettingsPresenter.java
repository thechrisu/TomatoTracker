package com.culshoefer.tomatotracker.views.settings;


import com.culshoefer.tomatotracker.countdowntimer.CountdownTimer;
import com.culshoefer.tomatotracker.pomodorobase.PomodoroTimeManager;
import com.culshoefer.tomatotracker.spinnerfield.IntegerSpinnerAutoCommit;
import com.culshoefer.tomatotracker.spinnerfield.MinuteSecondSpinner;
import com.culshoefer.tomatotracker.spinnerfield.PomodoroTimeInputFields;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * @author Christoph Ulshoefer <christophsulshoefer@gmail.com> 18/07/16. TODO backup current
 *         interval times to disk
 */
public class SettingsPresenter implements Initializable {
    @FXML
    private IntegerSpinnerAutoCommit pomodoroWorkMinsSpnr;
    @FXML
    private IntegerSpinnerAutoCommit pomodoroWorkSecsSpnr;
    @FXML
    private IntegerSpinnerAutoCommit pomodoroShortBreakMinsSpnr;
    @FXML
    private IntegerSpinnerAutoCommit pomodoroShortBreakSecsSpnr;
    @FXML
    private IntegerSpinnerAutoCommit pomodoroLongBreakMinsSpnr;
    @FXML
    private IntegerSpinnerAutoCommit pomodoroLongBreakSecsSpnr;
    @FXML
    private IntegerSpinnerAutoCommit pomodoroIntervalsSpnr;

    @Inject
    private PomodoroTimeManager ptm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert pomodoroWorkMinsSpnr != null : "fx:id\"pomodoroMinsSpnr\" was not injected. check FXML Timer.fxml";
        assert pomodoroWorkSecsSpnr != null : "fx:id\"pomodoroSecsSpnr\" was not injected. check FXML Timer.fxml";
        assert pomodoroShortBreakMinsSpnr != null : "fx:id\"pomodoroMinsSpnr\" was not injected. check FXML Timer.fxml";
        assert pomodoroShortBreakSecsSpnr != null : "fx:id\"pomodoroSecsSpnr\" was not injected. check FXML Timer.fxml";
        MinuteSecondSpinner workFields = new MinuteSecondSpinner(pomodoroWorkMinsSpnr, pomodoroWorkSecsSpnr);
        MinuteSecondSpinner shortBreakFields = new MinuteSecondSpinner(pomodoroShortBreakMinsSpnr, pomodoroShortBreakSecsSpnr);
        MinuteSecondSpinner longBreakFields = new MinuteSecondSpinner(pomodoroLongBreakMinsSpnr, pomodoroLongBreakSecsSpnr);
    }

    public void init() {
        pomodoroIntervalsSpnr.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> obv, Integer oldvalue, Integer newValue) {
                ptm.
                //bt.setIntervalsUntilLongBreak(newValue);
            }
        });
    }

    public void setSettings(Stage stage, CountdownTimer bt) {
        this.stage = stage;
        this.bt = bt;
        this.init();
    }
}
