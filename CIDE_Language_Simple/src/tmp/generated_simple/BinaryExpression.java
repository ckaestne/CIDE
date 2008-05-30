package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BinaryExpression extends GenASTNode {
  public BinaryExpression(UnaryExpression unaryExpression, Expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOptionalWithDefault<UnaryExpression>("unaryExpression", unaryExpression, "0"),
      new PropertyOptionalWithDefault<Expression>("expression", expression, "0")
    }, firstToken, lastToken);
  }
  public BinaryExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new BinaryExpression(cloneProperties(),firstToken,lastToken);
  }
  public UnaryExpression getUnaryExpression() {
    return ((PropertyOptionalWithDefault<UnaryExpression>)getProperty("unaryExpression")).getValue();
  }
  public Expression getExpression() {
    return ((PropertyOptionalWithDefault<Expression>)getProperty("expression")).getValue();
  }
}
