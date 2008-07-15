package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SwitchStatement extends GenASTNode {
  public SwitchStatement(Expression expression, CaseBlock caseBlock, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyOne<CaseBlock>("caseBlock", caseBlock)
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
  public CaseBlock getCaseBlock() {
    return ((PropertyOne<CaseBlock>)getProperty("caseBlock")).getValue();
  }
}
