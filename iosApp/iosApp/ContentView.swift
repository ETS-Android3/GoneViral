import SwiftUI
import SpriteKit
import shared

struct ContentView: View {
    @State private var showingGame = false
    
    func sizedScene(size: CGSize) -> SKScene {
        let scene = GameScene(size: size)
        scene.scaleMode = .fill
        return scene
    }

	var body: some View {
        Group {
            if !showingGame {
                Button(action: {
                    self.showingGame = true
                }) {
                    Text("Play")
                        .font(.system(size: 45, weight: .bold, design: .default))
                }
            } else {
                GeometryReader { geometry in
                    SpriteView(scene: sizedScene(size: geometry.size))
                        .frame(width: geometry.size.width, height: geometry.size.height)
                }.edgesIgnoringSafeArea(.all)
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView()
            .previewDevice("iPhone 8")
            .landscape()
        
        ContentView()
            .previewDevice("iPhone 11")
            .landscape()
        
		ContentView()
            .previewDevice("iPhone 12")
            .landscape()
	}
}
