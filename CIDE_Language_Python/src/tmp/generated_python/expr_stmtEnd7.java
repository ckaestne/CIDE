package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmtEnd7 extends expr_stmtEnd {
  public expr_stmtEnd7(ASTStringNode andeq, SmartTestList smartTestList6, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("andeq", andeq),
      new PropertyOne<SmartTestList>("smartTestList6", smartTestList6)
    }, firstToken, lastToken);
  }
  public expr_stmtEnd7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmtEnd7(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAndeq() {
    return ((PropertyOne<ASTStringNode>)getProperty("andeq")).getValue();
  }
  public SmartTestList getSmartTestList6() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList6")).getValue();
  }
}
