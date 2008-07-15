package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BitwiseANDExpression extends GenASTNode {
  public BitwiseANDExpression(EqualityExpression equalityExpression, ArrayList<EqualityExpression> equalityExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EqualityExpression>("equalityExpression", equalityExpression),
      new PropertyZeroOrMore<EqualityExpression>("equalityExpression1", equalityExpression1)
    }, firstToken, lastToken);
  }
  public BitwiseANDExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BitwiseANDExpression(cloneProperties(),firstToken,lastToken);
  }
  public EqualityExpression getEqualityExpression() {
    return ((PropertyOne<EqualityExpression>)getProperty("equalityExpression")).getValue();
  }
  public ArrayList<EqualityExpression> getEqualityExpression1() {
    return ((PropertyZeroOrMore<EqualityExpression>)getProperty("equalityExpression1")).getValue();
  }
}
