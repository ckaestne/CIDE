package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class and_expression extends GenASTNode {
  public and_expression(equality_expression equality_expression, and_expression and_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<equality_expression>("equality_expression", equality_expression),
      new PropertyZeroOrOne<and_expression>("and_expression", and_expression)
    }, firstToken, lastToken);
  }
  public and_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new and_expression(cloneProperties(),firstToken,lastToken);
  }
  public equality_expression getEquality_expression() {
    return ((PropertyOne<equality_expression>)getProperty("equality_expression")).getValue();
  }
  public and_expression getAnd_expression() {
    return ((PropertyZeroOrOne<and_expression>)getProperty("and_expression")).getValue();
  }
}
