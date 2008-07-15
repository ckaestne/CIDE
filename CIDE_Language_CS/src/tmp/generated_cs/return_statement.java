package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class return_statement extends GenASTNode {
  public return_statement(expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public return_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new return_statement(cloneProperties(),firstToken,lastToken);
  }
  public expression getExpression() {
    return ((PropertyZeroOrOne<expression>)getProperty("expression")).getValue();
  }
}
