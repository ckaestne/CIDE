package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class yield_stmt extends GenASTNode {
  public yield_stmt(ASTStringNode yield, SmartTestList smartTestList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("yield", yield),
      new PropertyOne<SmartTestList>("smartTestList", smartTestList)
    }, firstToken, lastToken);
  }
  public yield_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new yield_stmt(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getYield() {
    return ((PropertyOne<ASTStringNode>)getProperty("yield")).getValue();
  }
  public SmartTestList getSmartTestList() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList")).getValue();
  }
}
