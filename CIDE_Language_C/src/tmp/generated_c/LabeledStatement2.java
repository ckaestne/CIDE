package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LabeledStatement2 extends LabeledStatement {
  public LabeledStatement2(ASTStringNode case_kw, ConstantExpression constantExpression, Statement statement1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("case_kw", case_kw),
      new PropertyOne<ConstantExpression>("constantExpression", constantExpression),
      new PropertyOne<Statement>("statement1", statement1)
    }, firstToken, lastToken);
  }
  public LabeledStatement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LabeledStatement2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getCase_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("case_kw")).getValue();
  }
  public ConstantExpression getConstantExpression() {
    return ((PropertyOne<ConstantExpression>)getProperty("constantExpression")).getValue();
  }
  public Statement getStatement1() {
    return ((PropertyOne<Statement>)getProperty("statement1")).getValue();
  }
}
