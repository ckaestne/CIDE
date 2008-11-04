package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class fixityDeclaration extends decl {
  public fixityDeclaration(fixdecl fixdecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<fixdecl>("fixdecl", fixdecl)
    }, firstToken, lastToken);
  }
  public fixityDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new fixityDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public fixdecl getFixdecl() {
    return ((PropertyOne<fixdecl>)getProperty("fixdecl")).getValue();
  }
}
