package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BitwiseANDExpressionNoIn extends GenASTNode {
  public BitwiseANDExpressionNoIn(EqualityExpressionNoIn equalityExpressionNoIn, ArrayList<EqualityExpressionNoIn> equalityExpressionNoIn1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EqualityExpressionNoIn>("equalityExpressionNoIn", equalityExpressionNoIn),
      new PropertyZeroOrMore<EqualityExpressionNoIn>("equalityExpressionNoIn1", equalityExpressionNoIn1)
    }, firstToken, lastToken);
  }
  public BitwiseANDExpressionNoIn(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BitwiseANDExpressionNoIn(cloneProperties(),firstToken,lastToken);
  }
  public EqualityExpressionNoIn getEqualityExpressionNoIn() {
    return ((PropertyOne<EqualityExpressionNoIn>)getProperty("equalityExpressionNoIn")).getValue();
  }
  public ArrayList<EqualityExpressionNoIn> getEqualityExpressionNoIn1() {
    return ((PropertyZeroOrMore<EqualityExpressionNoIn>)getProperty("equalityExpressionNoIn1")).getValue();
  }
}
