package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ANDExpression extends GenASTNode {
  public ANDExpression(EqualityExpression equalityExpression, ANDExpression aNDExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EqualityExpression>("equalityExpression", equalityExpression),
      new PropertyZeroOrOne<ANDExpression>("aNDExpression", aNDExpression)
    }, firstToken, lastToken);
  }
  public ANDExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ANDExpression(cloneProperties(),firstToken,lastToken);
  }
  public EqualityExpression getEqualityExpression() {
    return ((PropertyOne<EqualityExpression>)getProperty("equalityExpression")).getValue();
  }
  public ANDExpression getANDExpression() {
    return ((PropertyZeroOrOne<ANDExpression>)getProperty("aNDExpression")).getValue();
  }
}
