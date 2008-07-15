package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AndExpression extends GenASTNode {
  public AndExpression(EqualityExpression equalityExpression, ArrayList<EqualityExpression> equalityExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EqualityExpression>("equalityExpression", equalityExpression),
      new PropertyZeroOrMore<EqualityExpression>("equalityExpression1", equalityExpression1)
    }, firstToken, lastToken);
  }
  public AndExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AndExpression(cloneProperties(),firstToken,lastToken);
  }
  public EqualityExpression getEqualityExpression() {
    return ((PropertyOne<EqualityExpression>)getProperty("equalityExpression")).getValue();
  }
  public ArrayList<EqualityExpression> getEqualityExpression1() {
    return ((PropertyZeroOrMore<EqualityExpression>)getProperty("equalityExpression1")).getValue();
  }
}
