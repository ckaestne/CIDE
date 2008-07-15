package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class local_constant_declarator extends GenASTNode {
  public local_constant_declarator(identifier identifier, local_variable_initializer local_variable_initializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyOne<local_variable_initializer>("local_variable_initializer", local_variable_initializer)
    }, firstToken, lastToken);
  }
  public local_constant_declarator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new local_constant_declarator(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public local_variable_initializer getLocal_variable_initializer() {
    return ((PropertyOne<local_variable_initializer>)getProperty("local_variable_initializer")).getValue();
  }
}
