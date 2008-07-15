package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MultiplicativeExpression extends GenASTNode {
  public MultiplicativeExpression(UnaryExpression unaryExpression, ArrayList<MultiplicativeExpressionIntern> multiplicativeExpressionIntern, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<UnaryExpression>("unaryExpression", unaryExpression),
      new PropertyZeroOrMore<MultiplicativeExpressionIntern>("multiplicativeExpressionIntern", multiplicativeExpressionIntern)
    }, firstToken, lastToken);
  }
  public MultiplicativeExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MultiplicativeExpression(cloneProperties(),firstToken,lastToken);
  }
  public UnaryExpression getUnaryExpression() {
    return ((PropertyOne<UnaryExpression>)getProperty("unaryExpression")).getValue();
  }
  public ArrayList<MultiplicativeExpressionIntern> getMultiplicativeExpressionIntern() {
    return ((PropertyZeroOrMore<MultiplicativeExpressionIntern>)getProperty("multiplicativeExpressionIntern")).getValue();
  }
}
