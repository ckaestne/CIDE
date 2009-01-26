package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmtEnd6 extends expr_stmtEnd {
  public expr_stmtEnd6(ASTStringNode moduloeq, SmartTestList smartTestList5, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("moduloeq", moduloeq),
      new PropertyOne<SmartTestList>("smartTestList5", smartTestList5)
    }, firstToken, lastToken);
  }
  public expr_stmtEnd6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmtEnd6(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getModuloeq() {
    return ((PropertyOne<ASTStringNode>)getProperty("moduloeq")).getValue();
  }
  public SmartTestList getSmartTestList5() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList5")).getValue();
  }
}
