package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RelationalExpressionInt1 extends RelationalExpressionInt {
  public RelationalExpressionInt1(RelationalExpression relationalExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<RelationalExpression>("relationalExpression", relationalExpression)
    }, firstToken, lastToken);
  }
  public RelationalExpressionInt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new RelationalExpressionInt1(cloneProperties(),firstToken,lastToken);
  }
  public RelationalExpression getRelationalExpression() {
    return ((PropertyOne<RelationalExpression>)getProperty("relationalExpression")).getValue();
  }
}
