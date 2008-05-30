package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class klasseTypeVar2 extends klasseTypeVar {
  public klasseTypeVar2(var var1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var1", var1)
    }, firstToken, lastToken);
  }
  public klasseTypeVar2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new klasseTypeVar2(cloneProperties(),firstToken,lastToken);
  }
  public var getVar1() {
    return ((PropertyOne<var>)getProperty("var1")).getValue();
  }
}
