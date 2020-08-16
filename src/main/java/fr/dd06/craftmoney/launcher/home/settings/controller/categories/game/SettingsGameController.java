package fr.dd06.craftmoney.launcher.home.settings.controller.categories.game;

import com.jfoenix.controls.JFXSlider;
import fr.dd06.apis.javautils.java.util.system.Memory;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.home.settings.SettingsStage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SettingsGameController {

    @FXML
    private Label ramLabel;
    @FXML
    private GridPane ramGridPane;
    private SettingsStage settingsStage;
    private CraftMoneyLauncher main;
    private JFXSlider ramSlider;

    public void init(SettingsStage settingsStage, CraftMoneyLauncher main) {
        this.settingsStage = settingsStage;
        this.main = main;




        initComponents();
        updateRam();
        initEvents();
    }

    private void initEvents() {
        ramSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                double ramValue = (double)Math.round((double) newValue * 100d) / 100d;
                main.getLauncherSettingsConfig().reloadConfiguration();
                if(main.getLauncherSettingsConfig().getConfiguration().get("ram") != null) {
                    main.getLauncherSettingsConfig().getConfiguration().put("ram", ramValue);
                }
                else {
                    main.getLauncherSettingsConfig().getConfiguration().put("ram", 2.5);
                }
                main.getLauncherSettingsConfig().saveConfiguration();
                updateRam();

            }
        });
    }

    private void initComponents() {


        ramSlider = new JFXSlider();
        ramSlider.setMin(1);

        ramSlider.setMax(Memory.getInstalledMemoryInGb() - 2);

        ramSlider.setMaxWidth(550);

        ramGridPane.add(ramSlider, 0, 1);


    }

    private void updateRam() {
        main.getLauncherSettingsConfig().reloadConfiguration();

        if(main.getLauncherSettingsConfig().getConfiguration().get("ram") == null) {
            if(Memory.getInstalledMemoryInGb() < 4) {

                main.getLauncherSettingsConfig().getConfiguration().put("ram", 2);

            }
            else if (Memory.getInstalledMemoryInGb() < 8) {

                main.getLauncherSettingsConfig().getConfiguration().put("ram", 3.5);

            }
            else {

                main.getLauncherSettingsConfig().getConfiguration().put("ram", 5);

            }

        }
        ramSlider.setValue(Double.parseDouble(main.getLauncherSettingsConfig().getConfiguration().get("ram").toString()));
        main.getLauncherSettingsConfig().saveConfiguration();

        ramLabel.setText("RAM: " + main.getLauncherSettingsConfig().getConfiguration().get("ram") + "Gb");
    }
}
