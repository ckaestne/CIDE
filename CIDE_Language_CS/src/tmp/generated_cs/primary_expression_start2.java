package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_start2 extends primary_expression_start {
  public primary_expression_start2(identifier identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public primary_expression_start2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_start2(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
}
