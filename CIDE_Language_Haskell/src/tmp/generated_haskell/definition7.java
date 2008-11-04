package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class definition7 extends definition {
  public definition7(declaration declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<declaration>("declaration", declaration)
    }, firstToken, lastToken);
  }
  public definition7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new definition7(cloneProperties(),firstToken,lastToken);
  }
  public declaration getDeclaration() {
    return ((PropertyOne<declaration>)getProperty("declaration")).getValue();
  }
}
