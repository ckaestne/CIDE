package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class for_stmt extends GenASTNode {
  public for_stmt(exprlist exprlist, SmartTestList smartTestList, suite suite, suite suite1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<exprlist>("exprlist", exprlist),
      new PropertyOne<SmartTestList>("smartTestList", smartTestList),
      new PropertyOne<suite>("suite", suite),
      new PropertyZeroOrOne<suite>("suite1", suite1)
    }, firstToken, lastToken);
  }
  public for_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new for_stmt(cloneProperties(),firstToken,lastToken);
  }
  public exprlist getExprlist() {
    return ((PropertyOne<exprlist>)getProperty("exprlist")).getValue();
  }
  public SmartTestList getSmartTestList() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList")).getValue();
  }
  public suite getSuite() {
    return ((PropertyOne<suite>)getProperty("suite")).getValue();
  }
  public suite getSuite1() {
    return ((PropertyZeroOrOne<suite>)getProperty("suite1")).getValue();
  }
}
