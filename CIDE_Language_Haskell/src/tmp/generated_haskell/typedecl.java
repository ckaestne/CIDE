package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class typedecl extends definition {
  public typedecl(simpletype simpletype, functiontype functiontype, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<simpletype>("simpletype", simpletype),
      new PropertyOne<functiontype>("functiontype", functiontype)
    }, firstToken, lastToken);
  }
  public typedecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new typedecl(cloneProperties(),firstToken,lastToken);
  }
  public simpletype getSimpletype() {
    return ((PropertyOne<simpletype>)getProperty("simpletype")).getValue();
  }
  public functiontype getFunctiontype() {
    return ((PropertyOne<functiontype>)getProperty("functiontype")).getValue();
  }
}
