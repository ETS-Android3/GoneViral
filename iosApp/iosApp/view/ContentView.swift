import SwiftUI
import shared

struct ContentView: View {

	var body: some View {
        Button(action: {
            // do nothing rn
        }) {
            Text("Play")
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
            .landscape()
	}
}
