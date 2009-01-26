package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmtEnd2 extends expr_stmtEnd {
  public expr_stmtEnd2(ASTStringNode minuseq, SmartTestList smartTestList1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("minuseq", minuseq),
      new PropertyOne<SmartTestList>("smartTestList1", smartTestList1)
    }, firstToken, lastToken);
  }
  public expr_stmtEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmtEnd2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getMinuseq() {
    return ((PropertyOne<ASTStringNode>)getProperty("minuseq")).getValue();
  }
  public SmartTestList getSmartTestList1() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList1")).getValue();
  }
}
