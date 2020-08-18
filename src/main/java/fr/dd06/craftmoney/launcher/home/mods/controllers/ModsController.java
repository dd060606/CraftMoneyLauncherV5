package fr.dd06.craftmoney.launcher.home.mods.controllers;

import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.home.mods.ModsStage;

public class ModsController {
    private CraftMoneyLauncher main;
    private ModsStage modsStage;
    public void init(CraftMoneyLauncher main, ModsStage modsStage) {
        this.main = main;
        this.modsStage = modsStage;
    }
}
