package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StructDeclarator1 extends StructDeclarator {
  public StructDeclarator1(Declarator declarator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Declarator>("declarator", declarator)
    }, firstToken, lastToken);
  }
  public StructDeclarator1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StructDeclarator1(cloneProperties(),firstToken,lastToken);
  }
  public Declarator getDeclarator() {
    return ((PropertyOne<Declarator>)getProperty("declarator")).getValue();
  }
}
