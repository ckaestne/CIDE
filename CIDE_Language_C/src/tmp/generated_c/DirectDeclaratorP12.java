package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DirectDeclaratorP12 extends DirectDeclaratorP1 {
  public DirectDeclaratorP12(Declarator declarator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Declarator>("declarator", declarator)
    }, firstToken, lastToken);
  }
  public DirectDeclaratorP12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DirectDeclaratorP12(cloneProperties(),firstToken,lastToken);
  }
  public Declarator getDeclarator() {
    return ((PropertyOne<Declarator>)getProperty("declarator")).getValue();
  }
}
