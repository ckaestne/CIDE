package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exec_stmt extends GenASTNode {
  public exec_stmt(expr expr, exec_stmt_end exec_stmt_end, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expr>("expr", expr),
      new PropertyZeroOrOne<exec_stmt_end>("exec_stmt_end", exec_stmt_end)
    }, firstToken, lastToken);
  }
  public exec_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exec_stmt(cloneProperties(),firstToken,lastToken);
  }
  public expr getExpr() {
    return ((PropertyOne<expr>)getProperty("expr")).getValue();
  }
  public exec_stmt_end getExec_stmt_end() {
    return ((PropertyZeroOrOne<exec_stmt_end>)getProperty("exec_stmt_end")).getValue();
  }
}
