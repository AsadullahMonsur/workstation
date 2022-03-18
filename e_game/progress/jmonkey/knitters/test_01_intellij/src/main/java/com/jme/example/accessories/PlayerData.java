package com.jme.example.accessories;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;

@Serializable
public class PlayerData {
    private Vector3f location = new Vector3f(Vector3f.ZERO);
    private Quaternion rotation = new Quaternion(Quaternion.ZERO);
    private Vector3f walkDirection = new Vector3f(Vector3f.ZERO);

    private int id;
    private String name = "";

    public PlayerData() {
    }

    public PlayerData(int id) {
        this.id = id;
    }

    public PlayerData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Vector3f getLocation() {
        return location;
    }

    public void setLocation(Vector3f location) {
        this.location = location;
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public void setRotation(Quaternion rotation) {
        this.rotation = rotation;
    }

    public Vector3f getWalkDirection() {
        return walkDirection;
    }

    public void setWalkDirection(Vector3f walkDirection) {
        this.walkDirection = walkDirection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
