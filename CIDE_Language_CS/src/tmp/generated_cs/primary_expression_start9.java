package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_start9 extends primary_expression_start {
  public primary_expression_start9(checked_expression checked_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<checked_expression>("checked_expression", checked_expression)
    }, firstToken, lastToken);
  }
  public primary_expression_start9(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_start9(cloneProperties(),firstToken,lastToken);
  }
  public checked_expression getChecked_expression() {
    return ((PropertyOne<checked_expression>)getProperty("checked_expression")).getValue();
  }
}
