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
    
    /// Initialize scene when it appears
    override func didMove(to view: SKView) {
        let hand = HandView(with: self, x: 100, y: 200, cardWidth: size.width / 10)
        hand.addCard(backTexture: "Base Back", frontTexture: "China Con")
        hand.addCard(backTexture: "Base Back", frontTexture: "Donut Shop")
        hand.addCard(backTexture: "Base Back", frontTexture: "Manufactured")
        hand.addCard(backTexture: "Role Back", frontTexture: "Covidiot Role")
    }
    
    /// Perform actions when touch is moved across screen
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
        for touch in touches {
            let location = touch.location(in: self)
            if let card = atPoint(location) as? CardSprite {
                if card.enlarged { return }
                
                // drag card to location user touched
                card.position = location
            }
        }
    }

    /// Perform actions when screen is first touched
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        for touch in touches {
            let location = touch.location(in: self)
            if let card = atPoint(location) as? CardSprite {
                if touch.tapCount > 1 {
                    card.enlarge(screenSize: size)
                    return
                }
                if card.enlarged { return }
                
                card.zPosition = CardLevel.moving.rawValue
                
                // animate movement
                card.removeAction(forKey: "drop")
                card.run(SKAction.scale(to: 1.3, duration: 0.25), withKey: "pickup")
            }
        }
    }

    /// Perform actions when screen is no longer touched
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        for touch in touches {
            let location = touch.location(in: self)
            if let card = atPoint(location) as? CardSprite {
                if card.enlarged { return }
                
                card.zPosition = CardLevel.board.rawValue
                
                // animate movement
                card.removeAction(forKey: "pickup")
                card.run(SKAction.scale(to: 1, duration: 0.25), withKey: "drop")
                
                // make sure cards are layered in correct order
                card.removeFromParent()
                addChild(card)
            }
        }
    }
}
