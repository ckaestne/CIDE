package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_indexer_declaration extends GenASTNode {
  public interface_indexer_declaration(formal_parameter_list formal_parameter_list, interface_accessors interface_accessors, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<formal_parameter_list>("formal_parameter_list", formal_parameter_list),
      new PropertyOne<interface_accessors>("interface_accessors", interface_accessors)
    }, firstToken, lastToken);
  }
  public interface_indexer_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_indexer_declaration(cloneProperties(),firstToken,lastToken);
  }
  public formal_parameter_list getFormal_parameter_list() {
    return ((PropertyOne<formal_parameter_list>)getProperty("formal_parameter_list")).getValue();
  }
  public interface_accessors getInterface_accessors() {
    return ((PropertyOne<interface_accessors>)getProperty("interface_accessors")).getValue();
  }
}
