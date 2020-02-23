package com.vorheim.faces.sample.lib.controllers;

/**
 *
 * @author Darken
 */
public interface MessagesDelegate {
    void info(String title, String msg);
    void warn(String title, String msg);
    void error(String title, String msg);
}
