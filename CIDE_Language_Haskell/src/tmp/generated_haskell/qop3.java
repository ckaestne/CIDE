package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class qop3 extends qop {
  public qop3(ModuleNaamPrefix moduleNaamPrefix, otherOperators otherOperators, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ModuleNaamPrefix>("moduleNaamPrefix", moduleNaamPrefix),
      new PropertyOne<otherOperators>("otherOperators", otherOperators)
    }, firstToken, lastToken);
  }
  public qop3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qop3(cloneProperties(),firstToken,lastToken);
  }
  public ModuleNaamPrefix getModuleNaamPrefix() {
    return ((PropertyZeroOrOne<ModuleNaamPrefix>)getProperty("moduleNaamPrefix")).getValue();
  }
  public otherOperators getOtherOperators() {
    return ((PropertyOne<otherOperators>)getProperty("otherOperators")).getValue();
  }
}
