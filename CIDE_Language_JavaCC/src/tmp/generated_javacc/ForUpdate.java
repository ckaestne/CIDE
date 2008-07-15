package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ForUpdate extends GenASTNode {
  public ForUpdate(StatementExpressionList statementExpressionList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<StatementExpressionList>("statementExpressionList", statementExpressionList)
    }, firstToken, lastToken);
  }
  public ForUpdate(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ForUpdate(cloneProperties(),firstToken,lastToken);
  }
  public StatementExpressionList getStatementExpressionList() {
    return ((PropertyOne<StatementExpressionList>)getProperty("statementExpressionList")).getValue();
  }
}
