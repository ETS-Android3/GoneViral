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
        let chinaCon = CardSprite(back: "Base Back", front: "China Con")
        chinaCon.position = CGPoint(x: 100, y: 200)
        chinaCon.changeSize(to: size.width / 10)
        addChild(chinaCon)
        
        let covidiot = CardSprite(back: "Role Back", front: "Covidiot Role")
        covidiot.position = CGPoint(x: 300, y: 200)
        covidiot.changeSize(to: size.width / 10)
        addChild(covidiot)
    }
    
    /// Perform actions when touch is moved across screen
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
        for touch in touches {
            let location = touch.location(in: self)
            if let card = atPoint(location) as? CardSprite {
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
                // increase z-position of card from board to moving
                card.zPosition = CardLevel.moving.rawValue
                
                // increase scale to visualize size
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
                // decrease z-position of card from moving to board
                card.zPosition = CardLevel.board.rawValue
                
                // decrease scale to visualize size
                card.removeAction(forKey: "pickup")
                card.run(SKAction.scale(to: 1, duration: 0.25), withKey: "drop")
                
                // make sure cards are layered in correct order
                card.removeFromParent()
                addChild(card)
            }
        }
    }
}
