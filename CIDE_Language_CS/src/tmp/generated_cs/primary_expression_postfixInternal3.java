package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_postfixInternal3 extends primary_expression_postfixInternal {
  public primary_expression_postfixInternal3(element_access element_access, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<element_access>("element_access", element_access)
    }, firstToken, lastToken);
  }
  public primary_expression_postfixInternal3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_postfixInternal3(cloneProperties(),firstToken,lastToken);
  }
  public element_access getElement_access() {
    return ((PropertyOne<element_access>)getProperty("element_access")).getValue();
  }
}
