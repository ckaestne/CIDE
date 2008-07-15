package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class conversion_operator_declaration extends GenASTNode {
  public conversion_operator_declaration(conversion_operator conversion_operator, type type, type type1, identifier identifier, body body, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<conversion_operator>("conversion_operator", conversion_operator),
      new PropertyOne<type>("type", type),
      new PropertyOne<type>("type1", type1),
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyOne<body>("body", body)
    }, firstToken, lastToken);
  }
  public conversion_operator_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new conversion_operator_declaration(cloneProperties(),firstToken,lastToken);
  }
  public conversion_operator getConversion_operator() {
    return ((PropertyOne<conversion_operator>)getProperty("conversion_operator")).getValue();
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public type getType1() {
    return ((PropertyOne<type>)getProperty("type1")).getValue();
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public body getBody() {
    return ((PropertyOne<body>)getProperty("body")).getValue();
  }
}
