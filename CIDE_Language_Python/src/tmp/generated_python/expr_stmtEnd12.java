package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmtEnd12 extends expr_stmtEnd {
  public expr_stmtEnd12(ASTStringNode powereq, SmartTestList smartTestList11, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("powereq", powereq),
      new PropertyOne<SmartTestList>("smartTestList11", smartTestList11)
    }, firstToken, lastToken);
  }
  public expr_stmtEnd12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmtEnd12(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPowereq() {
    return ((PropertyOne<ASTStringNode>)getProperty("powereq")).getValue();
  }
  public SmartTestList getSmartTestList11() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList11")).getValue();
  }
}
