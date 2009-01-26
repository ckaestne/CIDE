package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class list_for extends GenASTNode {
  public list_for(exprlist exprlist, SmartTestList smartTestList, ArrayList<list_if> list_if, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<exprlist>("exprlist", exprlist),
      new PropertyOne<SmartTestList>("smartTestList", smartTestList),
      new PropertyZeroOrMore<list_if>("list_if", list_if)
    }, firstToken, lastToken);
  }
  public list_for(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new list_for(cloneProperties(),firstToken,lastToken);
  }
  public exprlist getExprlist() {
    return ((PropertyOne<exprlist>)getProperty("exprlist")).getValue();
  }
  public SmartTestList getSmartTestList() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList")).getValue();
  }
  public ArrayList<list_if> getList_if() {
    return ((PropertyZeroOrMore<list_if>)getProperty("list_if")).getValue();
  }
}
