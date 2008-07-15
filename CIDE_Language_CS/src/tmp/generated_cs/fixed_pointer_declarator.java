package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class fixed_pointer_declarator extends GenASTNode {
  public fixed_pointer_declarator(identifier identifier, fixed_pointer_initializer fixed_pointer_initializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyOne<fixed_pointer_initializer>("fixed_pointer_initializer", fixed_pointer_initializer)
    }, firstToken, lastToken);
  }
  public fixed_pointer_declarator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new fixed_pointer_declarator(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public fixed_pointer_initializer getFixed_pointer_initializer() {
    return ((PropertyOne<fixed_pointer_initializer>)getProperty("fixed_pointer_initializer")).getValue();
  }
}
