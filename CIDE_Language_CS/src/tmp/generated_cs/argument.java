package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class argument extends GenASTNode {
  public argument(argumentPrefix argumentPrefix, expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<argumentPrefix>("argumentPrefix", argumentPrefix),
      new PropertyOne<expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public argument(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new argument(cloneProperties(),firstToken,lastToken);
  }
  public argumentPrefix getArgumentPrefix() {
    return ((PropertyZeroOrOne<argumentPrefix>)getProperty("argumentPrefix")).getValue();
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
}
