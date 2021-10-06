//
//  GameScene.swift
//  Gone Viral
//
//  Created by William Edson Kilgore IV on 9/21/21.
//  Copyright © 2021 Black Opal Solutions. All rights reserved.
//

import Foundation
import SpriteKit

/// Determines the Z-position of cards on the game screen
enum CardLevel: CGFloat {
    case board = 10
    case moving = 100
    case enlarged = 200
}

/// The initial game scene
class GameScene: SKScene {
    
    /// The maximum number of cards the screen can fit horizontally
    private let numHorizontalCards = 10
    /// The minimum duration for a long press
    private let pressDuration = 0.5
    
    /// Whether or not a card is being displayed in enlarged form
    private var isDisplayingEnlarged = false
    /// The card that is being displayed in an enlarged format
    private weak var enlargedCard: CardSprite?
    /// The player's hand
    private lazy var hand: HandView = {
        let h = HandView(with: self, cardWidth: getCardWidth())
        h.addCard(backTexture: "Base Back", frontTexture: "China Con")
        h.addCard(backTexture: "Base Back", frontTexture: "Donut Shop")
        h.addCard(backTexture: "Base Back", frontTexture: "Manufactured")
        h.addCard(backTexture: "Role Back", frontTexture: "Covidiot Role")
        return h
    }()
    
    /// Initialize scene when it appears
    override func didMove(to view: SKView) {
        hand.moveHand(to: CGPoint(x: size.width / 2, y: 10))
        
        let pressed = UILongPressGestureRecognizer(target: self, action: #selector(longPress))
        pressed.minimumPressDuration = pressDuration
        view.addGestureRecognizer(pressed)
    }
    
    /// Perform actions when touch is held for a long period of time
    @objc private func longPress(sender: UILongPressGestureRecognizer) {
        let loc = sender.location(in: view)
        let location = CGPoint(x: loc.x, y: size.height - loc.y) // loc.y has zero at top while GameScene has 0 at bottom
        
        if sender.state == .began {
            if let card = atPoint(location) as? CardSprite {
                card.enlarge(screenSize: size)
                isDisplayingEnlarged = true
                enlargedCard = card
            }
        }
    }
    
    /// Perform actions when screen is first touched
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        if isDisplayingEnlarged {
            enlargedCard?.enlarge(screenSize: size)
            isDisplayingEnlarged = false
            hand.readdCards()
        }
    }
    
    /// Return the width of each card as allowable by the maximum number of cards that can fit on the
    /// screen horizontally
    /// - Returns: the card width
    private func getCardWidth() -> CGFloat {
        size.width / CGFloat(numHorizontalCards)
    }
    
//    /// Perform actions when touch is moved across screen
//    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
//        for touch in touches {
//            let location = touch.location(in: self)
//            if let card = atPoint(location) as? CardSprite {
//                if card.enlarged { return }
//
//                // drag card to location user touched
//                card.position = location
//            }
//        }
//    }
//
//    /// Perform actions when screen is first touched
//    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
//        for touch in touches {
//            let location = touch.location(in: self)
//            if let card = atPoint(location) as? CardSprite {
//                if touch.tapCount > 1 {
//                    card.enlarge(screenSize: size)
//                    return
//                }
//                if card.enlarged { return }
//
//                card.zPosition = CardLevel.moving.rawValue
//
//                // animate movement
//                card.removeAction(forKey: "drop")
//                card.run(SKAction.scale(to: 1.3, duration: 0.25), withKey: "pickup")
//            }
//        }
//    }
//
//    /// Perform actions when screen is no longer touched
//    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
//        for touch in touches {
//            let location = touch.location(in: self)
//            if let card = atPoint(location) as? CardSprite {
//                if card.enlarged { return }
//
//                card.zPosition = CardLevel.board.rawValue
//
//                // animate movement
//                card.removeAction(forKey: "pickup")
//                card.run(SKAction.scale(to: 1, duration: 0.25), withKey: "drop")
//
//                // make sure cards are layered in correct order
//                card.removeFromParent()
//                addChild(card)
//            }
//        }
//    }
}
