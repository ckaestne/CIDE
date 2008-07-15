package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DefaultClause extends GenASTNode {
  public DefaultClause(StatementList statementList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<StatementList>("statementList", statementList)
    }, firstToken, lastToken);
  }
  public DefaultClause(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DefaultClause(cloneProperties(),firstToken,lastToken);
  }
  public StatementList getStatementList() {
    return ((PropertyZeroOrOne<StatementList>)getProperty("statementList")).getValue();
  }
}
