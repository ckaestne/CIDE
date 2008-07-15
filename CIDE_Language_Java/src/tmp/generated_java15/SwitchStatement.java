package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SwitchStatement extends GenASTNode {
  public SwitchStatement(Expression expression, ArrayList<SwitchStatementLabel> switchStatementLabel, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyZeroOrMore<SwitchStatementLabel>("switchStatementLabel", switchStatementLabel)
    }, firstToken, lastToken);
  }
  public SwitchStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SwitchStatement(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public ArrayList<SwitchStatementLabel> getSwitchStatementLabel() {
    return ((PropertyZeroOrMore<SwitchStatementLabel>)getProperty("switchStatementLabel")).getValue();
  }
}
