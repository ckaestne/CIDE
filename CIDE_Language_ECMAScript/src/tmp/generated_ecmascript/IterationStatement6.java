package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IterationStatement6 extends IterationStatement {
  public IterationStatement6(LeftHandSideExpressionForIn leftHandSideExpressionForIn, Expression expression7, Statement statement5, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LeftHandSideExpressionForIn>("leftHandSideExpressionForIn", leftHandSideExpressionForIn),
      new PropertyOne<Expression>("expression7", expression7),
      new PropertyOne<Statement>("statement5", statement5)
    }, firstToken, lastToken);
  }
  public IterationStatement6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IterationStatement6(cloneProperties(),firstToken,lastToken);
  }
  public LeftHandSideExpressionForIn getLeftHandSideExpressionForIn() {
    return ((PropertyOne<LeftHandSideExpressionForIn>)getProperty("leftHandSideExpressionForIn")).getValue();
  }
  public Expression getExpression7() {
    return ((PropertyOne<Expression>)getProperty("expression7")).getValue();
  }
  public Statement getStatement5() {
    return ((PropertyOne<Statement>)getProperty("statement5")).getValue();
  }
}
