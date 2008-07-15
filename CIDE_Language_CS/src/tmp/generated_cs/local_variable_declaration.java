package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class local_variable_declaration extends GenASTNode {
  public local_variable_declaration(type type, local_variable_declarators local_variable_declarators, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type),
      new PropertyOne<local_variable_declarators>("local_variable_declarators", local_variable_declarators)
    }, firstToken, lastToken);
  }
  public local_variable_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new local_variable_declaration(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public local_variable_declarators getLocal_variable_declarators() {
    return ((PropertyOne<local_variable_declarators>)getProperty("local_variable_declarators")).getValue();
  }
}
