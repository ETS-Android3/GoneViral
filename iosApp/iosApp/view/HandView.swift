//
//  HandView.swift
//  Gone Viral
//
//  Created by William Edson Kilgore IV on 9/20/21.
//  Copyright © 2021 Black Opal Solutions. All rights reserved.
//

import Foundation
import SwiftUI
import ImageIO

struct HandView: View {
    @State private var cards = ["Base Back", "Base Back", "Base Back", "Base Back"]
    
    var body: some View {
        ScrollView(.horizontal ) {
            HStack {
                ForEach(0 ..< cards.count, id: \.self) {
                    Image(cards[$0])
                }
            }
        }
    }
}

struct HandView_Previews: PreviewProvider {
    static var previews: some View {
        HandView()
            .previewDevice("iPhone 8")
            .landscape()
        
        HandView()
            .previewDevice("iPhone 11")
            .landscape()
        
        HandView()
            .previewDevice("iPhone 12")
            .landscape()
    }
}
