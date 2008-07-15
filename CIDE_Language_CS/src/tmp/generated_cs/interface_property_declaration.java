package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_property_declaration extends GenASTNode {
  public interface_property_declaration(interface_accessors interface_accessors, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<interface_accessors>("interface_accessors", interface_accessors)
    }, firstToken, lastToken);
  }
  public interface_property_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_property_declaration(cloneProperties(),firstToken,lastToken);
  }
  public interface_accessors getInterface_accessors() {
    return ((PropertyOne<interface_accessors>)getProperty("interface_accessors")).getValue();
  }
}
