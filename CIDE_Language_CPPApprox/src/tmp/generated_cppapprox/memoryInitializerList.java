package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class memoryInitializerList extends GenASTNode {
  public memoryInitializerList(ArrayList<memoryInitializer> memoryInitializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<memoryInitializer>("memoryInitializer", memoryInitializer)
    }, firstToken, lastToken);
  }
  public memoryInitializerList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new memoryInitializerList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<memoryInitializer> getMemoryInitializer() {
    return ((PropertyList<memoryInitializer>)getProperty("memoryInitializer")).getValue();
  }
}
