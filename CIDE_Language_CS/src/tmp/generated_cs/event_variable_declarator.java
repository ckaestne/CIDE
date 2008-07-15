package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class event_variable_declarator extends GenASTNode {
  public event_variable_declarator(identifier identifier, variable_initializer variable_initializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyZeroOrOne<variable_initializer>("variable_initializer", variable_initializer)
    }, firstToken, lastToken);
  }
  public event_variable_declarator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new event_variable_declarator(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public variable_initializer getVariable_initializer() {
    return ((PropertyZeroOrOne<variable_initializer>)getProperty("variable_initializer")).getValue();
  }
}
