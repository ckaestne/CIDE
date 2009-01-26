package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmtEnd8 extends expr_stmtEnd {
  public expr_stmtEnd8(ASTStringNode oreq, SmartTestList smartTestList7, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("oreq", oreq),
      new PropertyOne<SmartTestList>("smartTestList7", smartTestList7)
    }, firstToken, lastToken);
  }
  public expr_stmtEnd8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmtEnd8(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getOreq() {
    return ((PropertyOne<ASTStringNode>)getProperty("oreq")).getValue();
  }
  public SmartTestList getSmartTestList7() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList7")).getValue();
  }
}
