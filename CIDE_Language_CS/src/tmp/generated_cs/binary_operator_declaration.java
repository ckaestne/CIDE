package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class binary_operator_declaration extends GenASTNode {
  public binary_operator_declaration(overloadable_binary_operator overloadable_binary_operator, type type, identifier identifier, type type1, identifier identifier1, body body, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<overloadable_binary_operator>("overloadable_binary_operator", overloadable_binary_operator),
      new PropertyOne<type>("type", type),
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyOne<type>("type1", type1),
      new PropertyOne<identifier>("identifier1", identifier1),
      new PropertyOne<body>("body", body)
    }, firstToken, lastToken);
  }
  public binary_operator_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new binary_operator_declaration(cloneProperties(),firstToken,lastToken);
  }
  public overloadable_binary_operator getOverloadable_binary_operator() {
    return ((PropertyOne<overloadable_binary_operator>)getProperty("overloadable_binary_operator")).getValue();
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public type getType1() {
    return ((PropertyOne<type>)getProperty("type1")).getValue();
  }
  public identifier getIdentifier1() {
    return ((PropertyOne<identifier>)getProperty("identifier1")).getValue();
  }
  public body getBody() {
    return ((PropertyOne<body>)getProperty("body")).getValue();
  }
}
