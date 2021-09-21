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
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("NSCoding not supported")
    }
    
    init(back: String, front: String) {
        backTexture = SKTexture(imageNamed: back)
        frontTexture = SKTexture(imageNamed: front)
        
        super.init(texture: frontTexture, color: .clear, size: frontTexture.size())
    }
    
    func changeSize(to width: CGFloat) {
        let aspectRatio = size.width / size.height
        size = CGSize(width: width, height: width / aspectRatio)
    }
}
