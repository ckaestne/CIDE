package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AdditiveExpression extends GenASTNode {
  public AdditiveExpression(MultiplicativeExpression multiplicativeExpression, ArrayList<AdditiveExpressionEnd> additiveExpressionEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MultiplicativeExpression>("multiplicativeExpression", multiplicativeExpression),
      new PropertyZeroOrMore<AdditiveExpressionEnd>("additiveExpressionEnd", additiveExpressionEnd)
    }, firstToken, lastToken);
  }
  public AdditiveExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AdditiveExpression(cloneProperties(),firstToken,lastToken);
  }
  public MultiplicativeExpression getMultiplicativeExpression() {
    return ((PropertyOne<MultiplicativeExpression>)getProperty("multiplicativeExpression")).getValue();
  }
  public ArrayList<AdditiveExpressionEnd> getAdditiveExpressionEnd() {
    return ((PropertyZeroOrMore<AdditiveExpressionEnd>)getProperty("additiveExpressionEnd")).getValue();
  }
}
