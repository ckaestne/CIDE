package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EqualityExpressionNoInEnd extends GenASTNode {
  public EqualityExpressionNoInEnd(EqualityOperator equalityOperator, RelationalExpressionNoIn relationalExpressionNoIn, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EqualityOperator>("equalityOperator", equalityOperator),
      new PropertyOne<RelationalExpressionNoIn>("relationalExpressionNoIn", relationalExpressionNoIn)
    }, firstToken, lastToken);
  }
  public EqualityExpressionNoInEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EqualityExpressionNoInEnd(cloneProperties(),firstToken,lastToken);
  }
  public EqualityOperator getEqualityOperator() {
    return ((PropertyOne<EqualityOperator>)getProperty("equalityOperator")).getValue();
  }
  public RelationalExpressionNoIn getRelationalExpressionNoIn() {
    return ((PropertyOne<RelationalExpressionNoIn>)getProperty("relationalExpressionNoIn")).getValue();
  }
}
