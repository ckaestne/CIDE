package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmtEnd5 extends expr_stmtEnd {
  public expr_stmtEnd5(ASTStringNode floordivideeq, SmartTestList smartTestList4, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("floordivideeq", floordivideeq),
      new PropertyOne<SmartTestList>("smartTestList4", smartTestList4)
    }, firstToken, lastToken);
  }
  public expr_stmtEnd5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmtEnd5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFloordivideeq() {
    return ((PropertyOne<ASTStringNode>)getProperty("floordivideeq")).getValue();
  }
  public SmartTestList getSmartTestList4() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList4")).getValue();
  }
}
