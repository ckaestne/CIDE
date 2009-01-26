package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr_stmtEnd13 extends expr_stmtEnd {
  public expr_stmtEnd13(ArrayList<SmartTestList> smartTestList12, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<SmartTestList>("smartTestList12", smartTestList12)
    }, firstToken, lastToken);
  }
  public expr_stmtEnd13(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr_stmtEnd13(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<SmartTestList> getSmartTestList12() {
    return ((PropertyZeroOrMore<SmartTestList>)getProperty("smartTestList12")).getValue();
  }
}
