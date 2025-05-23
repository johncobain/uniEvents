package br.edu.ifba.inf0008.uniEvents.menu.submenu;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.menu.Menu;

public class BaseMenu extends Menu {
    private int response;

    public BaseMenu(String title, String color, ArrayList<String> options) {
        super(title, color);
        for (String option : options) {
            super.addOption(option);
        }
    }

    public BaseMenu(String title, ArrayList<String> options) {
        super(title);
        for (String option : options) {
            super.addOption(option);
        }
    }

    @Override
    public void show() {
        response = super.menuResponse();
    }

    public int getResponse() {
        this.show();
        return response;
    }
  
}
