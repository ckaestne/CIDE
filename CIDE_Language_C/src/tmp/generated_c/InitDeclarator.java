package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class InitDeclarator extends GenASTNode {
  public InitDeclarator(Declarator declarator, Initializer initializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Declarator>("declarator", declarator),
      new PropertyZeroOrOne<Initializer>("initializer", initializer)
    }, firstToken, lastToken);
  }
  public InitDeclarator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new InitDeclarator(cloneProperties(),firstToken,lastToken);
  }
  public Declarator getDeclarator() {
    return ((PropertyOne<Declarator>)getProperty("declarator")).getValue();
  }
  public Initializer getInitializer() {
    return ((PropertyZeroOrOne<Initializer>)getProperty("initializer")).getValue();
  }
}
