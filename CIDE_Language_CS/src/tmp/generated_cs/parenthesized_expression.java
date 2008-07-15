package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class parenthesized_expression extends GenASTNode {
  public parenthesized_expression(expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public parenthesized_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new parenthesized_expression(cloneProperties(),firstToken,lastToken);
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
}
