package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class unary_operator_declaration extends GenASTNode {
  public unary_operator_declaration(overloadable_unary_operator overloadable_unary_operator, type type, identifier identifier, body body, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<overloadable_unary_operator>("overloadable_unary_operator", overloadable_unary_operator),
      new PropertyOne<type>("type", type),
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyOne<body>("body", body)
    }, firstToken, lastToken);
  }
  public unary_operator_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new unary_operator_declaration(cloneProperties(),firstToken,lastToken);
  }
  public overloadable_unary_operator getOverloadable_unary_operator() {
    return ((PropertyOne<overloadable_unary_operator>)getProperty("overloadable_unary_operator")).getValue();
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public body getBody() {
    return ((PropertyOne<body>)getProperty("body")).getValue();
  }
}
