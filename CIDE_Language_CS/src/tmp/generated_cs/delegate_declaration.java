package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class delegate_declaration extends GenASTNode {
  public delegate_declaration(type type, identifier identifier, formal_parameter_list formal_parameter_list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type),
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyZeroOrOne<formal_parameter_list>("formal_parameter_list", formal_parameter_list)
    }, firstToken, lastToken);
  }
  public delegate_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new delegate_declaration(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public formal_parameter_list getFormal_parameter_list() {
    return ((PropertyZeroOrOne<formal_parameter_list>)getProperty("formal_parameter_list")).getValue();
  }
}
