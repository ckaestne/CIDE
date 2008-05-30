using System; 
 
// Derive a custom EventArgs class that holds the key. 
class KeyEventArgs : EventArgs { 
  public char ch; 
} 
 
// Declare a delegate for an event.  
delegate void KeyHandler(object source, KeyEventArgs arg); 
 
// Declare a key-press event class. 
class KeyEvent { 
  public event KeyHandler KeyPress; 
 
  // This is called when a key is pressed. 
  public void OnKeyPress(char key) { 
    KeyEventArgs k = new KeyEventArgs(); 
   
    if(KeyPress != null) { 
      k.ch = key; 
      KeyPress(this, k); 
    } 
  } 
} 