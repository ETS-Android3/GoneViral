package com.blackopalsolutions.goneviral.model.domain;

public class Card {
    private int cardId;
    private String type;
    private int cost;
    private String description;
    private String effect;
    private String title;
    private int value;
    private String backTexture;
    private String frontTexture;

    public Card() {}

    /**
     * Creates a card model.
     * @param cardId the id of the card.
     * @param type the card type (i.e. Action, Conspiracy, etc.).
     * @param cost the card's cost.
     * @param description the card's description.
     * @param effect the card's effect.
     * @param title the title of the card.
     * @param value the value of the card.
     * @param backTexture the filename for the texture on the back of the card.
     * @param frontTexture the filename for the texture on the front of the card.
     */
    public Card(int cardId, String type, int cost, String description, String effect,
                String title, int value, String backTexture, String frontTexture) {
        this.cardId = cardId;
        this.type = type;
        this.cost = cost;
        this.description = description;
        this.effect = effect;
        this.title = title;
        this.value = value;
        this.backTexture = backTexture;
        this.frontTexture = frontTexture;
    }

    public int getCardId() {
        return cardId;
    }

    public String getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getEffect() {
        return effect;
    }

    public String getTitle() {
        return title;
    }

    public int getValue() {
        return value;
    }

    public String getBackTexture() {
        return backTexture;
    }

    public String getFrontTexture() {
        return frontTexture;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setBackTexture(String backTexture) {
        this.backTexture = backTexture;
    }

    public void setFrontTexture(String frontTexture) {
        this.frontTexture = frontTexture;
    }
}
