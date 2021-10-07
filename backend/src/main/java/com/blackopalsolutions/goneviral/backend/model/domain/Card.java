package com.blackopalsolutions.goneviral.backend.model.domain;

public class Card {
    private final String cardId;
    private final String type;
    private final int cost;
    private final String description;
    private final String effect;
    private final String title;
    private final int value;
    private final String backTexture;
    private final String frontTexture;

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
    public Card(String cardId, String type, int cost, String description, String effect,
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

    public String getCardId() {
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
}
