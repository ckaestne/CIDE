package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class struct_declaration extends GenASTNode {
  public struct_declaration(identifier identifier, base_interfaces base_interfaces, class_body class_body, ASTTextNode text266, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyZeroOrOne<base_interfaces>("base_interfaces", base_interfaces),
      new PropertyOne<class_body>("class_body", class_body),
      new PropertyZeroOrOne<ASTTextNode>("text266", text266)
    }, firstToken, lastToken);
  }
  public struct_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new struct_declaration(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public base_interfaces getBase_interfaces() {
    return ((PropertyZeroOrOne<base_interfaces>)getProperty("base_interfaces")).getValue();
  }
  public class_body getClass_body() {
    return ((PropertyOne<class_body>)getProperty("class_body")).getValue();
  }
  public ASTTextNode getText266() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text266")).getValue();
  }
}
