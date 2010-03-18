package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AdditiveExpressionIntern extends GenASTNode {
  public AdditiveExpressionIntern(AdditiveOp additiveOp, MultiplicativeExpression multiplicativeExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AdditiveOp>("additiveOp", additiveOp),
      new PropertyOne<MultiplicativeExpression>("multiplicativeExpression", multiplicativeExpression)
    }, firstToken, lastToken);
  }
  public AdditiveExpressionIntern(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AdditiveExpressionIntern(cloneProperties(),firstToken,lastToken);
  }
  public AdditiveOp getAdditiveOp() {
    return ((PropertyOne<AdditiveOp>)getProperty("additiveOp")).getValue();
  }
  public MultiplicativeExpression getMultiplicativeExpression() {
    return ((PropertyOne<MultiplicativeExpression>)getProperty("multiplicativeExpression")).getValue();
  }
}
