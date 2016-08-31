package com.culshoefer.tomatotracker.views.settings;


import com.culshoefer.tomatotracker.pomodorobase.PomodoroIntervalStateManager;
import com.culshoefer.tomatotracker.pomodorobase.PomodoroState;
import com.culshoefer.tomatotracker.pomodorobase.PomodoroTimeManager;
import com.culshoefer.tomatotracker.spinnerfield.IntegerSpinnerAutoCommit;
import com.culshoefer.tomatotracker.spinnerfield.MinuteSecondSpinner;
import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import static com.culshoefer.NullAsserterLogger.assertNotNull;

/**
 * @author Christoph Ulshoefer <christophsulshoefer@gmail.com> 18/07/16.
 * TODO backup current interval times to disk
 * TODO fix CSS
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

    private MinuteSecondSpinner workFields, shortBreakFields, longBreakFields;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        verifyInjection();
        createCombinedSpinners();
        installListeners();
        setTimes();
    }

    private void setTimes() {
        workFields.setValue(ptm.getTimeForInterval(PomodoroState.WORK));
        shortBreakFields.setValue(ptm.getTimeForInterval(PomodoroState.SHORTBREAK));
        longBreakFields.setValue(ptm.getTimeForInterval(PomodoroState.LONGBREAK));
        pomodoroIntervalsSpnr.setValues(0, 10, ptm.getIntervalsUntilLongBreak());
    }

    private void verifyInjection() {
        assert pomodoroWorkMinsSpnr != null : "fx:id\"pomodoroMinsSpnr\" was not injected. check FXML mainscreen.fxml";
        assert pomodoroWorkSecsSpnr != null : "fx:id\"pomodoroSecsSpnr\" was not injected. check FXML mainscreen.fxml";
        assert pomodoroShortBreakMinsSpnr != null : "fx:id\"pomodoroMinsSpnr\" was not injected. check FXML mainscreen.fxml";
        assert pomodoroShortBreakSecsSpnr != null : "fx:id\"pomodoroSecsSpnr\" was not injected. check FXML mainscreen.fxml";
        assertNotNull(workFields, "workFields");
        assertNotNull(shortBreakFields, "shortBreakFields");
        assertNotNull(longBreakFields, "longBreakFields");
        assertNotNull(ptm, "ptm");
    }

    private void createCombinedSpinners() {
        workFields = new MinuteSecondSpinner(pomodoroWorkMinsSpnr, pomodoroWorkSecsSpnr);
        shortBreakFields = new MinuteSecondSpinner(pomodoroShortBreakMinsSpnr, pomodoroShortBreakSecsSpnr);
        longBreakFields = new MinuteSecondSpinner(pomodoroLongBreakMinsSpnr, pomodoroLongBreakSecsSpnr);
    }

    private  void installListeners() {
        pomodoroIntervalsSpnr.valueProperty().addListener((obv, oldvalue, newValue) -> ptm.setIntervalsUntilLongBreak(newValue));
        workFields.addListener((observable, oldValue, newValue) -> {
            ptm.setInitialTimeForState(PomodoroState.WORK, newValue);
        });
        shortBreakFields.addListener((observable, oldValue, newValue) -> {
            ptm.setInitialTimeForState(PomodoroState.SHORTBREAK, newValue);
        });
        longBreakFields.addListener((observable, oldValue, newValue) -> {
            ptm.setInitialTimeForState(PomodoroState.LONGBREAK, newValue);
        });
    }
}
