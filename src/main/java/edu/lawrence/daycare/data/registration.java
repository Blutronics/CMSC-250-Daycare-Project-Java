/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.daycare.data;

import java.sql.Date;

/**
 *
 * @author Khanh Toan
 */
public class registration {

    private int id;
    private int parentID;
    private int childID;
    private int providerID;
    private Date start;
    private Date end;
    private int status;

    public registration() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChildID() {
        return childID;
    }

    public void setChildID(int childID) {
        this.childID = childID;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public int getProviderID() {
        return providerID;
    }

    public void setProviderID(int providerID) {
        this.providerID = providerID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date Start) {
        this.start = Start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
