package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class atom2 extends atom {
  public atom2(SmartTestList smartTestList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<SmartTestList>("smartTestList", smartTestList)
    }, firstToken, lastToken);
  }
  public atom2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new atom2(cloneProperties(),firstToken,lastToken);
  }
  public SmartTestList getSmartTestList() {
    return ((PropertyZeroOrOne<SmartTestList>)getProperty("smartTestList")).getValue();
  }
}
