package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class namespace_declaration extends GenASTNode {
  public namespace_declaration(type_name type_name, namespace_body namespace_body, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type_name>("type_name", type_name),
      new PropertyOne<namespace_body>("namespace_body", namespace_body)
    }, firstToken, lastToken);
  }
  public namespace_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new namespace_declaration(cloneProperties(),firstToken,lastToken);
  }
  public type_name getType_name() {
    return ((PropertyOne<type_name>)getProperty("type_name")).getValue();
  }
  public namespace_body getNamespace_body() {
    return ((PropertyOne<namespace_body>)getProperty("namespace_body")).getValue();
  }
}
