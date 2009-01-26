package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmtEnd3 extends expr_stmtEnd {
  public expr_stmtEnd3(ASTStringNode multiplyeq, SmartTestList smartTestList2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("multiplyeq", multiplyeq),
      new PropertyOne<SmartTestList>("smartTestList2", smartTestList2)
    }, firstToken, lastToken);
  }
  public expr_stmtEnd3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmtEnd3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getMultiplyeq() {
    return ((PropertyOne<ASTStringNode>)getProperty("multiplyeq")).getValue();
  }
  public SmartTestList getSmartTestList2() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList2")).getValue();
  }
}
