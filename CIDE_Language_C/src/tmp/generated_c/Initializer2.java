package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Initializer2 extends Initializer {
  public Initializer2(InitializerList initializerList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<InitializerList>("initializerList", initializerList)
    }, firstToken, lastToken);
  }
  public Initializer2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Initializer2(cloneProperties(),firstToken,lastToken);
  }
  public InitializerList getInitializerList() {
    return ((PropertyOne<InitializerList>)getProperty("initializerList")).getValue();
  }
}
