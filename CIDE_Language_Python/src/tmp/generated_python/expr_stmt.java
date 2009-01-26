package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmt extends GenASTNode {
  public expr_stmt(SmartTestList smartTestList, expr_stmtEnd expr_stmtEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<SmartTestList>("smartTestList", smartTestList),
      new PropertyOne<expr_stmtEnd>("expr_stmtEnd", expr_stmtEnd)
    }, firstToken, lastToken);
  }
  public expr_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmt(cloneProperties(),firstToken,lastToken);
  }
  public SmartTestList getSmartTestList() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList")).getValue();
  }
  public expr_stmtEnd getExpr_stmtEnd() {
    return ((PropertyOne<expr_stmtEnd>)getProperty("expr_stmtEnd")).getValue();
  }
}
