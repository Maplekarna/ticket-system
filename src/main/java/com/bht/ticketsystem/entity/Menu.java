package com.bht.ticketsystem.entity;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static final String MENU1 = "1. Show available slots and remaining tickets";
    private static final String MENU2 = "2. Reserve a seat";
    private static final String MENU3 = "3. Show ticket history";
    private static final String MENU4 = "4. Show statistics";
    private static final String MENU5 = "5. Save the history";
    private static final String MENU6 = "6. Load the history";
    private static final String MENU7 = "7. Quit";

    public List<String> menuList;

    public Menu() {
        menuList = new ArrayList<>();
        menuList.add(MENU1);
        menuList.add(MENU2);
        menuList.add(MENU3);
        menuList.add(MENU4);
        menuList.add(MENU5);
        menuList.add(MENU6);
        menuList.add(MENU7);
    }


}
