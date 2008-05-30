package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class inst5 extends inst {
  public inst5(var var4, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var4", var4)
    }, firstToken, lastToken);
  }
  public inst5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new inst5(cloneProperties(),firstToken,lastToken);
  }
  public var getVar4() {
    return ((PropertyOne<var>)getProperty("var4")).getValue();
  }
}
