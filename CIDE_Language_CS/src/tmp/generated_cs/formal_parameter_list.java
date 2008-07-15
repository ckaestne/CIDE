package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class formal_parameter_list extends GenASTNode {
  public formal_parameter_list(attributes attributes, formal_parameter_listEnd formal_parameter_listEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<attributes>("attributes", attributes),
      new PropertyOne<formal_parameter_listEnd>("formal_parameter_listEnd", formal_parameter_listEnd)
    }, firstToken, lastToken);
  }
  public formal_parameter_list(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new formal_parameter_list(cloneProperties(),firstToken,lastToken);
  }
  public attributes getAttributes() {
    return ((PropertyZeroOrOne<attributes>)getProperty("attributes")).getValue();
  }
  public formal_parameter_listEnd getFormal_parameter_listEnd() {
    return ((PropertyOne<formal_parameter_listEnd>)getProperty("formal_parameter_listEnd")).getValue();
  }
}
