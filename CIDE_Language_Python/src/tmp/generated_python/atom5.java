package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class atom5 extends atom {
  public atom5(SmartTestList smartTestList1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<SmartTestList>("smartTestList1", smartTestList1)
    }, firstToken, lastToken);
  }
  public atom5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new atom5(cloneProperties(),firstToken,lastToken);
  }
  public SmartTestList getSmartTestList1() {
    return ((PropertyOne<SmartTestList>)getProperty("smartTestList1")).getValue();
  }
}
