package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MultiplicativeExpression extends GenASTNode {
  public MultiplicativeExpression(UnaryExpression unaryExpression, ArrayList<MultiplicativeExpressionEnd> multiplicativeExpressionEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<UnaryExpression>("unaryExpression", unaryExpression),
      new PropertyZeroOrMore<MultiplicativeExpressionEnd>("multiplicativeExpressionEnd", multiplicativeExpressionEnd)
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
  public ArrayList<MultiplicativeExpressionEnd> getMultiplicativeExpressionEnd() {
    return ((PropertyZeroOrMore<MultiplicativeExpressionEnd>)getProperty("multiplicativeExpressionEnd")).getValue();
  }
}
