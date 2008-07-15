package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RelationalExpressionInt3 extends RelationalExpressionInt {
  public RelationalExpressionInt3(RelationalExpression relationalExpression2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<RelationalExpression>("relationalExpression2", relationalExpression2)
    }, firstToken, lastToken);
  }
  public RelationalExpressionInt3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new RelationalExpressionInt3(cloneProperties(),firstToken,lastToken);
  }
  public RelationalExpression getRelationalExpression2() {
    return ((PropertyOne<RelationalExpression>)getProperty("relationalExpression2")).getValue();
  }
}
