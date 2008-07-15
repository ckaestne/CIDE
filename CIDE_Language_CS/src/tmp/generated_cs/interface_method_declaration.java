package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_method_declaration extends GenASTNode {
  public interface_method_declaration(formal_parameter_list formal_parameter_list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<formal_parameter_list>("formal_parameter_list", formal_parameter_list)
    }, firstToken, lastToken);
  }
  public interface_method_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_method_declaration(cloneProperties(),firstToken,lastToken);
  }
  public formal_parameter_list getFormal_parameter_list() {
    return ((PropertyZeroOrOne<formal_parameter_list>)getProperty("formal_parameter_list")).getValue();
  }
}
