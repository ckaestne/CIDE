package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LabeledStatement3 extends LabeledStatement {
  public LabeledStatement3(ASTStringNode dflt, Statement statement2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("dflt", dflt),
      new PropertyOne<Statement>("statement2", statement2)
    }, firstToken, lastToken);
  }
  public LabeledStatement3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LabeledStatement3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDflt() {
    return ((PropertyOne<ASTStringNode>)getProperty("dflt")).getValue();
  }
  public Statement getStatement2() {
    return ((PropertyOne<Statement>)getProperty("statement2")).getValue();
  }
}
