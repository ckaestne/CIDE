package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class local_variable_declarator extends GenASTNode {
  public local_variable_declarator(identifier identifier, local_variable_assignment local_variable_assignment, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyZeroOrOne<local_variable_assignment>("local_variable_assignment", local_variable_assignment)
    }, firstToken, lastToken);
  }
  public local_variable_declarator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new local_variable_declarator(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public local_variable_assignment getLocal_variable_assignment() {
    return ((PropertyZeroOrOne<local_variable_assignment>)getProperty("local_variable_assignment")).getValue();
  }
}
