package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class valueDeclaration extends decl {
  public valueDeclaration(valdef valdef, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<valdef>("valdef", valdef)
    }, firstToken, lastToken);
  }
  public valueDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new valueDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public valdef getValdef() {
    return ((PropertyOne<valdef>)getProperty("valdef")).getValue();
  }
}
