import SwiftUI
import shared

struct ContentView: View {
    @State private var showingGame = false

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
                EmptyView()
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
