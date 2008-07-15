package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EqualityExpressionNoIn extends GenASTNode {
  public EqualityExpressionNoIn(RelationalExpressionNoIn relationalExpressionNoIn, ArrayList<EqualityExpressionNoInEnd> equalityExpressionNoInEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<RelationalExpressionNoIn>("relationalExpressionNoIn", relationalExpressionNoIn),
      new PropertyZeroOrMore<EqualityExpressionNoInEnd>("equalityExpressionNoInEnd", equalityExpressionNoInEnd)
    }, firstToken, lastToken);
  }
  public EqualityExpressionNoIn(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EqualityExpressionNoIn(cloneProperties(),firstToken,lastToken);
  }
  public RelationalExpressionNoIn getRelationalExpressionNoIn() {
    return ((PropertyOne<RelationalExpressionNoIn>)getProperty("relationalExpressionNoIn")).getValue();
  }
  public ArrayList<EqualityExpressionNoInEnd> getEqualityExpressionNoInEnd() {
    return ((PropertyZeroOrMore<EqualityExpressionNoInEnd>)getProperty("equalityExpressionNoInEnd")).getValue();
  }
}
