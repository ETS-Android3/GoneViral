//
//  HandView.swift
//  Gone Viral
//
//  Created by William Edson Kilgore IV on 9/21/21.
//  Copyright © 2021 Black Opal Solutions. All rights reserved.
//

import Foundation
import SpriteKit

/// Represents a hand of cards
class HandView {
    
    /// the padding to put between each card in the hand (x)
    private let cardPaddingX: CGFloat = 15
    /// the padding to put between each card in the hand (y)
    private let cardPaddingY: CGFloat = 2
    /// the angle to rotate the cards in the hand
    private let cardAngle: Float = 5
    
    /// the scene to add the hand to
    private weak var scene: SKScene?
    /// the x coordinate of the leftmost card
    private var x: CGFloat
    /// the y coordinate of the leftmost card
    private var y: CGFloat
    /// the width of each card
    private let cardWidth: CGFloat
    /// the cards in the player's hand
    private var cards: [CardSprite] = []
    
    /// Create the hand on a given scene, maybe giving it initial cards
    /// - Parameters:
    ///   - scene: the scene to add the hand to
    ///   - x: the x position for the center of the hand
    ///   - y: the y position for the bottom of the hand
    ///   - width: the width of each card
    ///   - cards: the textures for each of the initial cards, if any
    init(with scene: SKScene, x: CGFloat = 0, y: CGFloat = 0, cardWidth width: CGFloat, cards: (backTexture: String, frontTexture: String)...) {
        self.scene = scene
        self.x = x
        self.y = y
        cardWidth = width
        
        for card in cards {
            addCard(backTexture: card.backTexture, frontTexture: card.frontTexture)
        }
    }
    
    /// Add a card to the hand
    /// - Parameters:
    ///   - backTexture: the texture name for the back of the card
    ///   - frontTexture: the texture name for the front of the card
    func addCard(backTexture: String, frontTexture: String) {
        let sprite = CardSprite(back: backTexture, front: frontTexture)
        sprite.changeWidth(to: cardWidth)
        sprite.zPosition = CardLevel.board.rawValue
        scene?.addChild(sprite)
        cards.append(sprite)
        repositionCards()
    }
    
    /// Remove the given card from the hand
    /// - Parameters:
    ///   - index: the index of the card to remove
    func removeCard(at index: Int) {
        cards[index].removeFromParent()
        cards.remove(at: index)
        repositionCards()
    }
    
    /// Move the hand and all cards in it to a specific point
    /// - Parameters:
    ///   - point: the point to move the hand to, the x is the center of the hand, the y is the bottom of the hand
    func moveHand(to point: CGPoint) {
        x = point.x
        y = point.y
        repositionCards()
    }
    
    /// Readd the cards so they appear in proper order
    func readdCards() {
        for card in cards {
            card.removeFromParent()
            scene?.addChild(card)
        }
    }
    
    /// Reposition the cards according to placement in hand
    private func repositionCards() {
        let oddCount = countOdd(from: 0, to: cards.count - 1)
        var rotation = cardAngle * Float(oddCount)
        var xpos = x - CGFloat(oddCount) * cardPaddingX
        var ypos = y
        
        // the y needs to be offset by half the height of the card to
        // make the hand's y value seem to be at the bottom of the cards
        if cards.count > 0 {
            ypos += cards[0].size.height / 2
        }
        
        for card in cards {
            card.position = CGPoint(x: xpos, y: ypos)
            card.zRotation = CGFloat(GLKMathDegreesToRadians(rotation))
            
            rotation -= cardAngle
            xpos += cardPaddingX
            ypos -= cardPaddingY
        }
    }
    
    /// Cound the number of odd numbers in a range
    /// - Parameters:
    ///   - l: the lowest number in the range (inclusive)
    ///   - r: the highest number in the range (inclusive)
    /// - Returns: the count of odd numbers in the range
    private func countOdd(from l: Int, to r: Int) -> Int {
        var n = (r - l) / 2
        if r % 2 != 0 || l % 2 != 0 {
            n += 1
        }
        return n
    }
}
