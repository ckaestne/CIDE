package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConstructorInitializer extends GenASTNode {
  public ConstructorInitializer(memoryInitializerList memoryInitializerList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<memoryInitializerList>("memoryInitializerList", memoryInitializerList)
    }, firstToken, lastToken);
  }
  public ConstructorInitializer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConstructorInitializer(cloneProperties(),firstToken,lastToken);
  }
  public memoryInitializerList getMemoryInitializerList() {
    return ((PropertyOne<memoryInitializerList>)getProperty("memoryInitializerList")).getValue();
  }
}
