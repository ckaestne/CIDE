package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_start5 extends primary_expression_start {
  public primary_expression_start5(this_access this_access, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<this_access>("this_access", this_access)
    }, firstToken, lastToken);
  }
  public primary_expression_start5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_start5(cloneProperties(),firstToken,lastToken);
  }
  public this_access getThis_access() {
    return ((PropertyOne<this_access>)getProperty("this_access")).getValue();
  }
}
