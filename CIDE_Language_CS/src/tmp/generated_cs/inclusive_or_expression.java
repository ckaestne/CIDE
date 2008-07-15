package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class inclusive_or_expression extends GenASTNode {
  public inclusive_or_expression(exclusive_or_expression exclusive_or_expression, inclusive_or_expression inclusive_or_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<exclusive_or_expression>("exclusive_or_expression", exclusive_or_expression),
      new PropertyZeroOrOne<inclusive_or_expression>("inclusive_or_expression", inclusive_or_expression)
    }, firstToken, lastToken);
  }
  public inclusive_or_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new inclusive_or_expression(cloneProperties(),firstToken,lastToken);
  }
  public exclusive_or_expression getExclusive_or_expression() {
    return ((PropertyOne<exclusive_or_expression>)getProperty("exclusive_or_expression")).getValue();
  }
  public inclusive_or_expression getInclusive_or_expression() {
    return ((PropertyZeroOrOne<inclusive_or_expression>)getProperty("inclusive_or_expression")).getValue();
  }
}
