package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class declaration extends topdecl {
  public declaration(decl decl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<decl>("decl", decl)
    }, firstToken, lastToken);
  }
  public declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new declaration(cloneProperties(),firstToken,lastToken);
  }
  public decl getDecl() {
    return ((PropertyOne<decl>)getProperty("decl")).getValue();
  }
}
