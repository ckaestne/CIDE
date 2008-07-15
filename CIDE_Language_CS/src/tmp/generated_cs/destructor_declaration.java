package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class destructor_declaration extends GenASTNode {
  public destructor_declaration(identifier identifier, body body, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyOne<body>("body", body)
    }, firstToken, lastToken);
  }
  public destructor_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new destructor_declaration(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public body getBody() {
    return ((PropertyOne<body>)getProperty("body")).getValue();
  }
}
