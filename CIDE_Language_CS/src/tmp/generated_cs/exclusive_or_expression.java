package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exclusive_or_expression extends GenASTNode {
  public exclusive_or_expression(and_expression and_expression, exclusive_or_expression exclusive_or_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<and_expression>("and_expression", and_expression),
      new PropertyZeroOrOne<exclusive_or_expression>("exclusive_or_expression", exclusive_or_expression)
    }, firstToken, lastToken);
  }
  public exclusive_or_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exclusive_or_expression(cloneProperties(),firstToken,lastToken);
  }
  public and_expression getAnd_expression() {
    return ((PropertyOne<and_expression>)getProperty("and_expression")).getValue();
  }
  public exclusive_or_expression getExclusive_or_expression() {
    return ((PropertyZeroOrOne<exclusive_or_expression>)getProperty("exclusive_or_expression")).getValue();
  }
}
