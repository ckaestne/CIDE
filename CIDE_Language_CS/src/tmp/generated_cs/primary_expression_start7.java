package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_start7 extends primary_expression_start {
  public primary_expression_start7(typeof_expression typeof_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<typeof_expression>("typeof_expression", typeof_expression)
    }, firstToken, lastToken);
  }
  public primary_expression_start7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_start7(cloneProperties(),firstToken,lastToken);
  }
  public typeof_expression getTypeof_expression() {
    return ((PropertyOne<typeof_expression>)getProperty("typeof_expression")).getValue();
  }
}
