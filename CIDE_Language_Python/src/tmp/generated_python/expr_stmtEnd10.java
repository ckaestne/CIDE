package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmtEnd10 extends expr_stmtEnd {
  public expr_stmtEnd10(ASTStringNode lshifteq, SmartTestList smartTestList9, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("lshifteq", lshifteq),
      new PropertyOne<SmartTestList>("smartTestList9", smartTestList9)
    }, firstToken, lastToken);
  }
  public expr_stmtEnd10(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmtEnd10(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getLshifteq() {
    return ((PropertyOne<ASTStringNode>)getProperty("lshifteq")).getValue();
  }
  public SmartTestList getSmartTestList9() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList9")).getValue();
  }
}
