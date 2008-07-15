package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StatementExpression1 extends StatementExpression {
  public StatementExpression1(PreIncrementExpression preIncrementExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PreIncrementExpression>("preIncrementExpression", preIncrementExpression)
    }, firstToken, lastToken);
  }
  public StatementExpression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StatementExpression1(cloneProperties(),firstToken,lastToken);
  }
  public PreIncrementExpression getPreIncrementExpression() {
    return ((PropertyOne<PreIncrementExpression>)getProperty("preIncrementExpression")).getValue();
  }
}
