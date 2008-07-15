package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class local_variable_declarators extends GenASTNode {
  public local_variable_declarators(local_variable_declarator local_variable_declarator, local_variable_declarators local_variable_declarators, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<local_variable_declarator>("local_variable_declarator", local_variable_declarator),
      new PropertyZeroOrOne<local_variable_declarators>("local_variable_declarators", local_variable_declarators)
    }, firstToken, lastToken);
  }
  public local_variable_declarators(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new local_variable_declarators(cloneProperties(),firstToken,lastToken);
  }
  public local_variable_declarator getLocal_variable_declarator() {
    return ((PropertyOne<local_variable_declarator>)getProperty("local_variable_declarator")).getValue();
  }
  public local_variable_declarators getLocal_variable_declarators() {
    return ((PropertyZeroOrOne<local_variable_declarators>)getProperty("local_variable_declarators")).getValue();
  }
}
