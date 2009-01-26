package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmtEnd9 extends expr_stmtEnd {
  public expr_stmtEnd9(ASTStringNode xoreq, SmartTestList smartTestList8, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("xoreq", xoreq),
      new PropertyOne<SmartTestList>("smartTestList8", smartTestList8)
    }, firstToken, lastToken);
  }
  public expr_stmtEnd9(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmtEnd9(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getXoreq() {
    return ((PropertyOne<ASTStringNode>)getProperty("xoreq")).getValue();
  }
  public SmartTestList getSmartTestList8() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList8")).getValue();
  }
}
