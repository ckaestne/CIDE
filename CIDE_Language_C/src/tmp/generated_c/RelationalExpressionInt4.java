package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RelationalExpressionInt4 extends RelationalExpressionInt {
  public RelationalExpressionInt4(RelationalExpression relationalExpression3, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<RelationalExpression>("relationalExpression3", relationalExpression3)
    }, firstToken, lastToken);
  }
  public RelationalExpressionInt4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new RelationalExpressionInt4(cloneProperties(),firstToken,lastToken);
  }
  public RelationalExpression getRelationalExpression3() {
    return ((PropertyOne<RelationalExpression>)getProperty("relationalExpression3")).getValue();
  }
}
