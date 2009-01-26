package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmtEnd1 extends expr_stmtEnd {
  public expr_stmtEnd1(ASTStringNode pluseq, SmartTestList smartTestList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("pluseq", pluseq),
      new PropertyOne<SmartTestList>("smartTestList", smartTestList)
    }, firstToken, lastToken);
  }
  public expr_stmtEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmtEnd1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPluseq() {
    return ((PropertyOne<ASTStringNode>)getProperty("pluseq")).getValue();
  }
  public SmartTestList getSmartTestList() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList")).getValue();
  }
}
