package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RelationalExpressionInt2 extends RelationalExpressionInt {
  public RelationalExpressionInt2(RelationalExpression relationalExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<RelationalExpression>("relationalExpression1", relationalExpression1)
    }, firstToken, lastToken);
  }
  public RelationalExpressionInt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new RelationalExpressionInt2(cloneProperties(),firstToken,lastToken);
  }
  public RelationalExpression getRelationalExpression1() {
    return ((PropertyOne<RelationalExpression>)getProperty("relationalExpression1")).getValue();
  }
}
