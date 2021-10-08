package com.blackopalsolutions.goneviral.backend.model.domain;

public class Role {
    private int id;
    private String ability;
    private String title;
    private String backTexture;
    private String frontTexture;

    /**
     * Creates an empty Role object.
     */
    public Role() {}

    /**
     * Creates a Role object.
     * @param id the role's id.
     * @param ability the role's ability.
     * @param title the role's title.
     * @param backTexture the back texture for the role.
     * @param frontTexture the front texture for the role.
     */
    public Role(int id, String ability, String title, String backTexture, String frontTexture) {
        this.id = id;
        this.ability = ability;
        this.title = title;
        this.backTexture = backTexture;
        this.frontTexture = frontTexture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBackTexture(String backTexture) {
        this.backTexture = backTexture;
    }

    public void setFrontTexture(String frontTexture) {
        this.frontTexture = frontTexture;
    }

    public int getId() {
        return id;
    }

    public String getAbility() {
        return ability;
    }

    public String getTitle() {
        return title;
    }

    public String getBackTexture() {
        return backTexture;
    }

    public String getFrontTexture() {
        return frontTexture;
    }
}
