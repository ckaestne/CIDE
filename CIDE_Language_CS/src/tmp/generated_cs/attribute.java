package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class attribute extends GenASTNode {
  public attribute(type_name type_name, attribute_arguments attribute_arguments, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type_name>("type_name", type_name),
      new PropertyZeroOrOne<attribute_arguments>("attribute_arguments", attribute_arguments)
    }, firstToken, lastToken);
  }
  public attribute(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new attribute(cloneProperties(),firstToken,lastToken);
  }
  public type_name getType_name() {
    return ((PropertyOne<type_name>)getProperty("type_name")).getValue();
  }
  public attribute_arguments getAttribute_arguments() {
    return ((PropertyZeroOrOne<attribute_arguments>)getProperty("attribute_arguments")).getValue();
  }
}
