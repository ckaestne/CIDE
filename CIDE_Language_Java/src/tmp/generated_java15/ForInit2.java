package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ForInit2 extends ForInit {
  public ForInit2(StatementExpressionList statementExpressionList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<StatementExpressionList>("statementExpressionList", statementExpressionList)
    }, firstToken, lastToken);
  }
  public ForInit2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ForInit2(cloneProperties(),firstToken,lastToken);
  }
  public StatementExpressionList getStatementExpressionList() {
    return ((PropertyOne<StatementExpressionList>)getProperty("statementExpressionList")).getValue();
  }
}
