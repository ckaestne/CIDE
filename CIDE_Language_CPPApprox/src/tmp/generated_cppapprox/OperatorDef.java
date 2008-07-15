package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class OperatorDef extends CodeUnit_TopLevel {
  public OperatorDef(OperatorOverloading operatorOverloading, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<OperatorOverloading>("operatorOverloading", operatorOverloading)
    }, firstToken, lastToken);
  }
  public OperatorDef(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new OperatorDef(cloneProperties(),firstToken,lastToken);
  }
  public OperatorOverloading getOperatorOverloading() {
    return ((PropertyOne<OperatorOverloading>)getProperty("operatorOverloading")).getValue();
  }
}
