//
//  LandscapeModifier.swift
//  Gone Viral
//
//  Created by William Edson Kilgore IV on 9/20/21.
//  Copyright © 2021 Black Opal Solutions. All rights reserved.
//
//  https://sarunw.com/posts/how-to-preview-a-device-in-landscape-orientation-with-swiftui-previews/

import Foundation
import SwiftUI

struct LandscapeModifier: ViewModifier {
    let height = UIScreen.main.bounds.width
    let width = UIScreen.main.bounds.height
    
    var isPad: Bool {
        height >= 768
    }
    
    var isRegularWidth: Bool {
        height >= 414
    }
    
    func body(content: Content) -> some View {
        content
            .previewLayout(.fixed(width: width, height: height))
            .environment(\.horizontalSizeClass, isRegularWidth ? .regular : .compact)
            .environment(\.verticalSizeClass, isPad ? .regular: .compact)
    }
}

extension View {
    func landscape() -> some View {
        self.modifier(LandscapeModifier())
    }
}
