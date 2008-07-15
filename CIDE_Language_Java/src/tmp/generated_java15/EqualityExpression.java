package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EqualityExpression extends GenASTNode {
  public EqualityExpression(InstanceOfExpression instanceOfExpression, ArrayList<EqualityExpressionIntern> equalityExpressionIntern, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<InstanceOfExpression>("instanceOfExpression", instanceOfExpression),
      new PropertyZeroOrMore<EqualityExpressionIntern>("equalityExpressionIntern", equalityExpressionIntern)
    }, firstToken, lastToken);
  }
  public EqualityExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EqualityExpression(cloneProperties(),firstToken,lastToken);
  }
  public InstanceOfExpression getInstanceOfExpression() {
    return ((PropertyOne<InstanceOfExpression>)getProperty("instanceOfExpression")).getValue();
  }
  public ArrayList<EqualityExpressionIntern> getEqualityExpressionIntern() {
    return ((PropertyZeroOrMore<EqualityExpressionIntern>)getProperty("equalityExpressionIntern")).getValue();
  }
}
