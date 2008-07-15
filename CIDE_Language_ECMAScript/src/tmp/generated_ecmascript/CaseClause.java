package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CaseClause extends GenASTNode {
  public CaseClause(Expression expression, StatementList statementList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyZeroOrOne<StatementList>("statementList", statementList)
    }, firstToken, lastToken);
  }
  public CaseClause(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CaseClause(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public StatementList getStatementList() {
    return ((PropertyZeroOrOne<StatementList>)getProperty("statementList")).getValue();
  }
}
