/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vorheim.faces.sample;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
 *
 * @author Darken
 */
@Named("userController")
@ConversationScoped
public class UserController implements Serializable {

    private User current;
    private int selectedItemIndex;

    @PostConstruct
    public void init() {
        current = new User();
    }
    
    public void save() {
        System.out.println(current.getName());
    }

    public User getSelected() {
        if (current == null) {
            current = new User();
            selectedItemIndex = -1;
        }
        return current;
    }
}
