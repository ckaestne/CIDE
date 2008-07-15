package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class op2 extends op {
  public op2(conop conop, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<conop>("conop", conop)
    }, firstToken, lastToken);
  }
  public op2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new op2(cloneProperties(),firstToken,lastToken);
  }
  public conop getConop() {
    return ((PropertyOne<conop>)getProperty("conop")).getValue();
  }
}
