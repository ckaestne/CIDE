package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class base_interfaces extends GenASTNode {
  public base_interfaces(interface_type_list interface_type_list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<interface_type_list>("interface_type_list", interface_type_list)
    }, firstToken, lastToken);
  }
  public base_interfaces(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new base_interfaces(cloneProperties(),firstToken,lastToken);
  }
  public interface_type_list getInterface_type_list() {
    return ((PropertyOne<interface_type_list>)getProperty("interface_type_list")).getValue();
  }
}
