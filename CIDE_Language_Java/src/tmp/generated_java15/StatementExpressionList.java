package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StatementExpressionList extends GenASTNode {
  public StatementExpressionList(StatementExpression statementExpression, ArrayList<StatementExpression> statementExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<StatementExpression>("statementExpression", statementExpression),
      new PropertyZeroOrMore<StatementExpression>("statementExpression1", statementExpression1)
    }, firstToken, lastToken);
  }
  public StatementExpressionList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StatementExpressionList(cloneProperties(),firstToken,lastToken);
  }
  public StatementExpression getStatementExpression() {
    return ((PropertyOne<StatementExpression>)getProperty("statementExpression")).getValue();
  }
  public ArrayList<StatementExpression> getStatementExpression1() {
    return ((PropertyZeroOrMore<StatementExpression>)getProperty("statementExpression1")).getValue();
  }
}
