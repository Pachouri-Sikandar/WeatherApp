package com.weatherapp.api.objects;

import com.google.gson.annotations.Expose;

public class Weather {

@Expose
private int id;
@Expose
private String main;
@Expose
private String description;
@Expose
private String icon;

/**
*
* @return
* The id
*/
public int getId() {
return id;
}

/**
*
* @param id
* The id
*/
public void setId(int id) {
this.id = id;
}

/**
*
* @return
* The main
*/
public String getMain() {
return main;
}

/**
*
* @param main
* The main
*/
public void setMain(String main) {
this.main = main;
}

/**
*
* @return
* The description
*/
public String getDescription() {
return description;
}

/**
*
* @param description
* The description
*/
public void setDescription(String description) {
this.description = description;
}

/**
*
* @return
* The icon
*/
public String getIcon() {
return icon;
}

/**
*
* @param icon
* The icon
*/
public void setIcon(String icon) {
this.icon = icon;
}

}