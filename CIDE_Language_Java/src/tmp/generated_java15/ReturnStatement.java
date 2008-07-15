package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ReturnStatement extends GenASTNode {
  public ReturnStatement(Expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public ReturnStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ReturnStatement(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyZeroOrOne<Expression>)getProperty("expression")).getValue();
  }
}
