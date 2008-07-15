package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpression1 extends UnaryExpression {
  public UnaryExpression1(AdditiveOp additiveOp, UnaryExpression unaryExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AdditiveOp>("additiveOp", additiveOp),
      new PropertyOne<UnaryExpression>("unaryExpression", unaryExpression)
    }, firstToken, lastToken);
  }
  public UnaryExpression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpression1(cloneProperties(),firstToken,lastToken);
  }
  public AdditiveOp getAdditiveOp() {
    return ((PropertyOne<AdditiveOp>)getProperty("additiveOp")).getValue();
  }
  public UnaryExpression getUnaryExpression() {
    return ((PropertyOne<UnaryExpression>)getProperty("unaryExpression")).getValue();
  }
}
