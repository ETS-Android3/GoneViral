package com.blackopalsolutions.goneviral.model.domain;

public class Card {
    private String title;
    private String type;
    private String backTexture;
    private String description;
    private String effect;
    private String frontTexture;
    private String playTime;

    public Card() {}

    /**
     * Creates a card model.
     * @param title the title of the card.
     * @param type the card type (i.e. Action, Conspiracy, etc.).
     * @param backTexture the filename for the texture on the back of the card.
     * @param description the card's description.
     * @param effect the card's effect.
     * @param frontTexture the filename for the texture on the front of the card.
     * @param playTime when the card should be played
     */
    public Card(String title, String type, String backTexture, String description, String effect,
                String frontTexture, String playTime) {
        this.title = title;
        this.type = type;
        this.backTexture = backTexture;
        this.description = description;
        this.effect = effect;
        this.frontTexture = frontTexture;
        this.playTime = playTime;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getBackTexture() {
        return backTexture;
    }

    public String getDescription() {
        return description;
    }

    public String getEffect() {
        return effect;
    }

    public String getFrontTexture() {
        return frontTexture;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBackTexture(String backTexture) {
        this.backTexture = backTexture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public void setFrontTexture(String frontTexture) {
        this.frontTexture = frontTexture;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }
}
