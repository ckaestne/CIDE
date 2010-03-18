package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StatementExpression3 extends StatementExpression {
  public StatementExpression3(PrimaryExpression primaryExpression, StatementExpressionAssignment statementExpressionAssignment, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PrimaryExpression>("primaryExpression", primaryExpression),
      new PropertyZeroOrOne<StatementExpressionAssignment>("statementExpressionAssignment", statementExpressionAssignment)
    }, firstToken, lastToken);
  }
  public StatementExpression3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StatementExpression3(cloneProperties(),firstToken,lastToken);
  }
  public PrimaryExpression getPrimaryExpression() {
    return ((PropertyOne<PrimaryExpression>)getProperty("primaryExpression")).getValue();
  }
  public StatementExpressionAssignment getStatementExpressionAssignment() {
    return ((PropertyZeroOrOne<StatementExpressionAssignment>)getProperty("statementExpressionAssignment")).getValue();
  }
}
