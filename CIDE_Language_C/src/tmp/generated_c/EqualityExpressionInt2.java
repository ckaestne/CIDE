package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EqualityExpressionInt2 extends EqualityExpressionInt {
  public EqualityExpressionInt2(ASTStringNode noteq, EqualityExpression equalityExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("noteq", noteq),
      new PropertyOne<EqualityExpression>("equalityExpression1", equalityExpression1)
    }, firstToken, lastToken);
  }
  public EqualityExpressionInt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EqualityExpressionInt2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getNoteq() {
    return ((PropertyOne<ASTStringNode>)getProperty("noteq")).getValue();
  }
  public EqualityExpression getEqualityExpression1() {
    return ((PropertyOne<EqualityExpression>)getProperty("equalityExpression1")).getValue();
  }
}
