package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmtEnd4 extends expr_stmtEnd {
  public expr_stmtEnd4(ASTStringNode divideeq, SmartTestList smartTestList3, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("divideeq", divideeq),
      new PropertyOne<SmartTestList>("smartTestList3", smartTestList3)
    }, firstToken, lastToken);
  }
  public expr_stmtEnd4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmtEnd4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDivideeq() {
    return ((PropertyOne<ASTStringNode>)getProperty("divideeq")).getValue();
  }
  public SmartTestList getSmartTestList3() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList3")).getValue();
  }
}
