package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_declaration extends GenASTNode {
  public interface_declaration(identifier identifier, base_interfaces base_interfaces, interface_body interface_body, ASTTextNode text269, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyZeroOrOne<base_interfaces>("base_interfaces", base_interfaces),
      new PropertyOne<interface_body>("interface_body", interface_body),
      new PropertyZeroOrOne<ASTTextNode>("text269", text269)
    }, firstToken, lastToken);
  }
  public interface_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_declaration(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public base_interfaces getBase_interfaces() {
    return ((PropertyZeroOrOne<base_interfaces>)getProperty("base_interfaces")).getValue();
  }
  public interface_body getInterface_body() {
    return ((PropertyOne<interface_body>)getProperty("interface_body")).getValue();
  }
  public ASTTextNode getText269() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text269")).getValue();
  }
}
