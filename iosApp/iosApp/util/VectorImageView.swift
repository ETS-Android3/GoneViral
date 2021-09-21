//
//  VectorImageView.swift
//  Gone Viral
//
//  Created by William Edson Kilgore IV on 9/20/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SVGKit

struct VectorImageView: UIViewRepresentable {
    var url: URL
    
    func makeUIView(context: Context) -> SVGKFastImageView {
        let svgImage = SVGKImage(contentsOf: url)
        return SVGKFastImageView(svgkImage: svgImage ?? SVGKImage())
    }
    
    func updateUIView(_ uiView: SVGKFastImageView, context: Context) {
        // do nothing rn
    }
}
