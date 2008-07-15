package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement6 extends Statement {
  public Statement6(ExpressionStatement expressionStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ExpressionStatement>("expressionStatement", expressionStatement)
    }, firstToken, lastToken);
  }
  public Statement6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement6(cloneProperties(),firstToken,lastToken);
  }
  public ExpressionStatement getExpressionStatement() {
    return ((PropertyOne<ExpressionStatement>)getProperty("expressionStatement")).getValue();
  }
}
