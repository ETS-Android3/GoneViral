//
//  CardSprite.swift
//  Gone Viral
//
//  Created by William Edson Kilgore IV on 9/21/21.
//  Copyright © 2021 Black Opal Solutions. All rights reserved.
//

import Foundation
import SpriteKit

class CardSprite: SKSpriteNode {
    let frontTexture: SKTexture
    let backTexture: SKTexture
    var faceUp = true
    
    var enlarged = false
    var savedPosition = CGPoint.zero
    var savedSize = CGSize.zero
    var savedRotation = CGFloat.zero
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("NSCoding not supported")
    }
    
    init(back: String, front: String) {
        backTexture = SKTexture(imageNamed: back)
        frontTexture = SKTexture(imageNamed: front)
        super.init(texture: frontTexture, color: .clear, size: frontTexture.size())
    }
    
    /// Change the width while maintaining aspect ratio
    /// - Parameters:
    ///   - width: the width to change to
    func changeWidth(to width: CGFloat) {
        let aspectRatio = size.width / size.height
        size = CGSize(width: width, height: width / aspectRatio)
    }
    
    /// Change the height while maintaining aspect ratio
    /// - Parameters:
    ///   - height: the height to change to
    func changeHeight(to height: CGFloat) {
        let aspectRatio = size.height / size.width
        size = CGSize(width: height / aspectRatio, height: height)
    }
    
    /// Flip the card from front to back and animates the flip
    func flip() {
        let firstHalfFlip = SKAction.scaleX(to: 0.0, duration: 0.3)
        let secondHalfFlip = SKAction.scaleX(to: 1.0, duration: 0.3)
        
        setScale(1.0)
        
        run(firstHalfFlip) {
            if self.faceUp {
                self.texture = self.backTexture
            } else {
                self.texture = self.frontTexture
            }
            self.run(secondHalfFlip)
        }
        
        faceUp = !faceUp
    }
    
    /// Enlarge the card and display various card info
    /// - Parameters:
    ///   - screenSize: the size of the screen
    func enlarge(screenSize: CGSize) {
        removeAllActions()
        
        if enlarged {
            enlarged = false
            zPosition = CardLevel.board.rawValue
            position = savedPosition
            size = savedSize
            zRotation = savedRotation
        } else {
            enlarged = true
            zPosition = CardLevel.enlarged.rawValue
            savedPosition = position
            savedSize = size
            savedRotation = zRotation
            
            if let parent = parent {
                position = CGPoint(x: parent.frame.midX, y: parent.frame.midY)
            }
            
            changeHeight(to: screenSize.height - 20)
            zRotation = 0
        }
    }
}
