package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ThrowStatement extends GenASTNode {
  public ThrowStatement(Expression expression, ASTTextNode text337, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyZeroOrOne<ASTTextNode>("text337", text337)
    }, firstToken, lastToken);
  }
  public ThrowStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ThrowStatement(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public ASTTextNode getText337() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text337")).getValue();
  }
}
