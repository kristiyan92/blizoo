package com.example.blizoo.blizoodiplom.models;

public class NavDrawerItem {

    private String mTitle;
    private int mIcon;
    private int mIconSelected;
    private boolean isSelected = false;

    public NavDrawerItem(String title, int icon, int iconSelected, boolean isSelected){
        this.mTitle = title;
        this.mIcon = icon;
        this.mIconSelected = iconSelected;
        this.isSelected = isSelected;
    }

    public void setTitle(String title){
        this.mTitle = title;
    }
    
    public String getTitle(){
        return this.mTitle;
    }
    
    public int getIcon(){
        return this.mIcon;
    }

    public void setIcon(int icon){
        this.mIcon = icon;
    }

    public int getIcon_selected() {
        return mIconSelected;
    }

    public void setIcon_selected(int icon_selected) {
        this.mIconSelected = icon_selected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }



}
