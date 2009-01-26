package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmtEnd11 extends expr_stmtEnd {
  public expr_stmtEnd11(ASTStringNode rshifteq, SmartTestList smartTestList10, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("rshifteq", rshifteq),
      new PropertyOne<SmartTestList>("smartTestList10", smartTestList10)
    }, firstToken, lastToken);
  }
  public expr_stmtEnd11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmtEnd11(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getRshifteq() {
    return ((PropertyOne<ASTStringNode>)getProperty("rshifteq")).getValue();
  }
  public SmartTestList getSmartTestList10() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList10")).getValue();
  }
}
