package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExpressionStatement extends GenASTNode {
  public ExpressionStatement(Expression expression, ASTTextNode text331, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyZeroOrOne<ASTTextNode>("text331", text331)
    }, firstToken, lastToken);
  }
  public ExpressionStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExpressionStatement(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public ASTTextNode getText331() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text331")).getValue();
  }
}
