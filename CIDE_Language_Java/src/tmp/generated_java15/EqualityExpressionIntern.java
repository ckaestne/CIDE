package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EqualityExpressionIntern extends GenASTNode {
  public EqualityExpressionIntern(EqualityOp equalityOp, InstanceOfExpression instanceOfExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EqualityOp>("equalityOp", equalityOp),
      new PropertyOne<InstanceOfExpression>("instanceOfExpression", instanceOfExpression)
    }, firstToken, lastToken);
  }
  public EqualityExpressionIntern(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EqualityExpressionIntern(cloneProperties(),firstToken,lastToken);
  }
  public EqualityOp getEqualityOp() {
    return ((PropertyOne<EqualityOp>)getProperty("equalityOp")).getValue();
  }
  public InstanceOfExpression getInstanceOfExpression() {
    return ((PropertyOne<InstanceOfExpression>)getProperty("instanceOfExpression")).getValue();
  }
}
