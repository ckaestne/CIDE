package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class newtypeParam1 extends newtypeParam {
  public newtypeParam1(var var, functiontype functiontype, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var", var),
      new PropertyOne<functiontype>("functiontype", functiontype)
    }, firstToken, lastToken);
  }
  public newtypeParam1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new newtypeParam1(cloneProperties(),firstToken,lastToken);
  }
  public var getVar() {
    return ((PropertyOne<var>)getProperty("var")).getValue();
  }
  public functiontype getFunctiontype() {
    return ((PropertyOne<functiontype>)getProperty("functiontype")).getValue();
  }
}
