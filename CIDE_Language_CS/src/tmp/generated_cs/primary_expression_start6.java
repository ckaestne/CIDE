package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_start6 extends primary_expression_start {
  public primary_expression_start6(base_access base_access, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<base_access>("base_access", base_access)
    }, firstToken, lastToken);
  }
  public primary_expression_start6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_start6(cloneProperties(),firstToken,lastToken);
  }
  public base_access getBase_access() {
    return ((PropertyOne<base_access>)getProperty("base_access")).getValue();
  }
}
